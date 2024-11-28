package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Datos.SQLiteDB;
import Aplicaciones.AplicacionRPG.Enums.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamientoManager {
		private static final String QUERY_TIPO_CORTE = armarQuery("tipo_corte", "espada");
		private static final String QUERY_TIPO_DANO = armarQuery("tipos_dano", "espada");
		private static final String QUERY_MATERIAL = armarQuery("material", "hacha");
		private static final String QUERY_GEMA_PRINCIPAL_CETRO = armarQuery("gema_principal", "cetro");
		private static final String QUERY_PODER_MAGICO_CETRO = armarQuery("poder_magico", "cetro");
		private static final String QUERY_REGENERACION_MANA_CETRO = armarQuery("regeneracion_mana", "cetro");
		private static final String QUERY_PODER_MAGICO_LIBRO_ENCANTAMIENTO = armarQuery("poder_magico_libro", "cetro");
		private static final String QUERY_REGENERACION_MANA_LIBRO_ENCANTAMIENTO = armarQuery("regeneracion_mana",
						"libro_encantamiento");
		private static final String QUERY_PODER_HABILIDAD_LIBRO_ENCANTAMIENTO = armarQuery("poder_magico_libro",
						"libro_encantamiento");
		private static final String  QUERY_REDUCCION_COSTO_MANA_LIBRO_ENCANTAMIENTO = armarQuery("reduccion_costo_mana",
						"libro_encantamiento");

		public static String armarQuery(String campo, String tabla) {
				return String.format("SELECT %s FROM %s WHERE equipamiento_id = ?", campo, tabla);
		}

		/**
			* Carga todos los equipamientos desde la base de datos.
			* @return Lista de objetos Equipamiento.
			*/
		public static List<Equipamiento> cargarEquipamientosDesdeBD() {
				List<Equipamiento> equipamientos = new ArrayList<>();
				String queryGeneral = "SELECT eq.id AS equipamiento_id, eq.nombre, eq.descripcion," +
								" ef.nombre AS efecto, eq.nivel_requerido, eq.poder FROM Equipamientos eq " +
								"JOIN Efectos ef ON eq.id_efecto = ef.id";

				try (Connection connection = SQLiteDB.getConnection();
				     Statement stmt = connection.createStatement();
				     ResultSet rsGeneral = stmt.executeQuery(queryGeneral)) {

						while (rsGeneral.next()) {
								Equipamiento equipamiento = crearEquipamientoDesdeRS(rsGeneral, connection);
								if (equipamiento != null) {
										equipamientos.add(equipamiento);
								}
						}
				} catch (SQLException e) {
						System.err.printf("Error al acceder a la base de datos: %s%n", e.getMessage());
				}

				return equipamientos;
		}

		private static Equipamiento crearEquipamientoDesdeRS(ResultSet rsGeneral, Connection connection) throws SQLException {
				int equipamientoId = rsGeneral.getInt("equipamiento_id");
				String nombre = rsGeneral.getString("nombre");
				String descripcion = rsGeneral.getString("descripcion");
				String tipo = rsGeneral.getString("tipo");
				int nivelRequerido = rsGeneral.getInt("nivel_requerido");
				int poder = rsGeneral.getInt("poder");
				Efecto efecto = Efecto.fromString(rsGeneral.getString("efecto"));

				return switch (tipo.toLowerCase()) {
						case "espada" -> {
								TipoDeCorte tipoCorte = obtenerTipoCorte(equipamientoId, connection);
								TipoDeDano tipoDeDano = obtenerTipoDeDano(equipamientoId, connection);
								yield new Espada(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, tipoCorte);
						}
						case "hacha" -> {
								Material material = obtenerMaterialHacha(equipamientoId, connection);
								TipoDeDano tipoDeDano = obtenerTipoDeDano(equipamientoId, connection);
								yield new Hacha(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, material);
						}
						case "arco" -> {
								int alcance = rsGeneral.getInt("alcance");
								TipoDeDano tipoDeDano = obtenerTipoDeDano(equipamientoId, connection);
								yield new Arco(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, alcance);
						}
						case "cetro" -> {
								TipoDeDano tipoDeDano = obtenerTipoDeDano(equipamientoId, connection);
								int poderMagico = obtenerValorEntero(QUERY_PODER_MAGICO_CETRO, equipamientoId, connection);
								int regeneracionMana = obtenerValorEntero(QUERY_REGENERACION_MANA_CETRO, equipamientoId, connection);
								Piedra gema = obtenerGema(equipamientoId, connection);
								yield new Cetro(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, poderMagico,
												regeneracionMana, gema);
						}
						case "libro_de_encantamientos" -> {
								TipoDeDano tipoDeDano = obtenerTipoDeDano(equipamientoId, connection);
								int poderMagico = obtenerValorEntero(QUERY_PODER_MAGICO_LIBRO_ENCANTAMIENTO, equipamientoId, connection);
								int regeneracionMana = obtenerValorEntero(QUERY_REGENERACION_MANA_LIBRO_ENCANTAMIENTO,
												equipamientoId, connection);
								int aumentoPoderHabilidad = obtenerValorEntero(QUERY_PODER_HABILIDAD_LIBRO_ENCANTAMIENTO,
												equipamientoId, connection);
								int reduccionCostoMana = obtenerValorEntero(QUERY_REDUCCION_COSTO_MANA_LIBRO_ENCANTAMIENTO,
												equipamientoId, connection);
								yield new LibroDeEncantamientos(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano,
												poderMagico, regeneracionMana, aumentoPoderHabilidad, reduccionCostoMana);
						}
						default -> {
								System.err.printf("Tipo de equipamiento desconocido: %s%n", tipo);
								yield null;
						}
				};
		}

		private static String obtenerValorCadena(String query, int equipamientoId, Connection connection) throws SQLException {
				try (PreparedStatement ps = connection.prepareStatement(query)) {
						ps.setInt(1, equipamientoId);
						try (ResultSet rs = ps.executeQuery()) {
								return rs.next() ? rs.getString(1) : null;
						}
				}
		}

		private static int obtenerValorEntero(String query, int equipamientoId, Connection connection) throws SQLException {
				try (PreparedStatement ps = connection.prepareStatement(query)) {
						ps.setInt(1, equipamientoId);
						try (ResultSet rs = ps.executeQuery()) {
								return rs.next() ? rs.getInt(1) : 0;
						}
				}
		}

		private static TipoDeCorte obtenerTipoCorte(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_TIPO_CORTE, equipamientoId, connection);
				return resultado != null ? TipoDeCorte.fromString(resultado) : null;
		}

		private static TipoDeDano obtenerTipoDeDano(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_TIPO_DANO, equipamientoId, connection);
				return resultado != null ? TipoDeDano.fromString(resultado) : null;
		}

		private static Material obtenerMaterialHacha(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_MATERIAL, equipamientoId, connection);
				return resultado != null ? Material.fromString(resultado) : null;
		}

		private static Piedra obtenerGema(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_GEMA_PRINCIPAL_CETRO, equipamientoId, connection);
				return resultado != null ? Piedra.fromString(resultado) : null;
		}
}