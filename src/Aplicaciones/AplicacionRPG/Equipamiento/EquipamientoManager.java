package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Datos.SQLiteDB;
import Aplicaciones.AplicacionRPG.Enums.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasCuerpoCuerpo.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasDistancia.*;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasMagicas.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamientoManager {
		private static final String QUERY_TIPO_DANO_ARMA = armarQuery("tipos_dano", "arma");

		private static final String QUERY_GOLPE_CRITICO_CUERPO_CUERPO = armarQuery("golpe_critico", "arma_cuerpo_cuerpo");
		private static final String QUERY_TIPO_CORTE_ESPADA = armarQuery("tipo_corte", "espada");
		private static final String QUERY_CONTRAATAQUE_ESPADA = armarQuery("contrataque_espada", "espada");
		private static final String QUERY_MATERIAL_HACHA = armarQuery("material", "hacha");

		private static final String QUERY_REGENERACION_MANA_ARMA_MAGICA = armarQuery("regeneracion_mana", "arma_magica");
		private static final String QUERY_PODER_MAGICO_ARMA_MAGICA = armarQuery("poder_magico", "arma_magica");
		private static final String QUERY_GEMA_PRINCIPAL_CETRO = armarQuery("gema_principal", "cetro");
		private static final String QUERY_PODER_HABILIDAD_LIBRO_ENCANTAMIENTO = armarQuery("poder_magico_libro",
						"libro_encantamiento");
		private static final String  QUERY_REDUCCION_COSTO_MANA_LIBRO_ENCANTAMIENTO = armarQuery("reduccion_costo_mana",
						"libro_encantamiento");

		private static final String QUERY_ALCANCE_ARMA_DISTANCIA = armarQuery("alcance", "arma_distancia");
		private static final String QUERY_PROBABILIDAD_DOBLE_DISPARO_BALLESTA =
						armarQuery("probabilidad_doble_disparo", "ballesta");
		private static final String QUERY_PENETRACION_ARMADURA_BALLESTA = armarQuery("penetracion_armadura", "ballesta");

		private static final String QUERY_DEFENSA_ARMADURA = armarQuery("defensa_armadura", "armadura");
		private static final String QUERY_FLEXIBLE_ARMADURA_LIGERA = armarQuery("flexible_armadura", "armadura_ligera");
		private static final String QUERY_IMPERMEABLE_ARMADURA_DE_PLACAS = armarQuery("impermeable_armadura",
						"armadura_de_placas");
		private static final String QUERY_DEFENSA_EXTRA_ARMADURA = armarQuery("defensa_extra", "armadura_pesada");

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
				String tipoTexto = rsGeneral.getString("tipo");
				String[] partes;
				partes = tipoTexto.split("-");
				int nivelRequerido = rsGeneral.getInt("nivel_requerido");
				int poder = rsGeneral.getInt("poder");
				Efecto efecto = Efecto.fromString(rsGeneral.getString("efecto"));
				return switch (partes[0].toLowerCase()) {
						case "arma" -> arma(partes, nombre, descripcion, nivelRequerido, poder, efecto,
										equipamientoId,  connection);
						case "armadura" -> armadura(partes[1], nombre, descripcion, nivelRequerido, poder, efecto,
										equipamientoId,  connection);
						case "accesorio" -> accesorio(partes[1], nombre, descripcion, nivelRequerido, poder, efecto,
										equipamientoId,  connection);
						default -> {
								System.err.printf("Tipo de equipamiento desconocido: %s%n", tipoTexto);
								yield null;
						}
				};
		}

		private static Equipamiento arma(String[] arma, String nombre, String descripcion, int nivelRequerido,
		int poder, Efecto efecto, int id,  Connection connection) throws SQLException {
				TipoDeDano tipoDeDano = obtenerTipoDeDano(id, connection);
				return switch (arma[1]) {
						case "arma_cuerpo_cuerpo" ->
										armaCuerpoCuerpo(arma[2].toLowerCase(), nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano,
														id, connection);
						case "arma_magica" ->
										armaMagica(arma[2].toLowerCase(), nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano,
														id, connection);
						case "arma_distancia" ->
										armaDistancia(arma[2].toLowerCase(), nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano,
												id, connection);
						default -> null;
				};
		}

		private static Equipamiento armaCuerpoCuerpo(String arma, String nombre, String descripcion, int nivelRequerido,
		                                             int poder, Efecto efecto, TipoDeDano tipoDeDano , int id,
		                                             Connection connection) throws SQLException {
				double golpeCritico = obtenerValorDecimal(QUERY_GOLPE_CRITICO_CUERPO_CUERPO, id, connection);
				return switch (arma) {
						case "espada" -> {
								TipoDeCorte tipoDeCorte = obtenerTipoCorte(id, connection);
								double chanceContraataque = obtenerValorDecimal(QUERY_CONTRAATAQUE_ESPADA, id, connection);
								yield new Espada(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, golpeCritico ,tipoDeCorte,
												chanceContraataque);
						}
						case "hacha" -> {
								Material material = obtenerMaterial(id, connection);
								yield new Hacha(nombre, descripcion, nivelRequerido, poder,efecto, tipoDeDano, golpeCritico, material);
						}
						default -> null;
				};

		}

		private static Equipamiento armaMagica(String arma, String nombre, String descripcion, int nivelRequerido,
		                                       int poder, Efecto efecto, TipoDeDano tipoDeDano, int id,
		                                       Connection connection) throws SQLException {
				int regeneracionMana = obtenerValorEntero(QUERY_REGENERACION_MANA_ARMA_MAGICA, id, connection);
				int poderMagico = obtenerValorEntero(QUERY_PODER_MAGICO_ARMA_MAGICA, id, connection);
				return switch (arma) {
						case "cetro" -> {
								Piedra piedra = obtenerGema(id, connection);
								yield new Cetro(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, regeneracionMana, poderMagico,
												piedra);
						}
						case "libro_de_encantamiento" -> {
								int aumentoPoderHabilidad =  obtenerValorEntero(QUERY_PODER_HABILIDAD_LIBRO_ENCANTAMIENTO, id, connection);
								int reduccionCostoMana = obtenerValorEntero(QUERY_REDUCCION_COSTO_MANA_LIBRO_ENCANTAMIENTO, id, connection);
								yield new LibroDeEncantamientos(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano,
												regeneracionMana, poderMagico, aumentoPoderHabilidad, reduccionCostoMana);
						}
						default -> null;
				};
		}

		private static Equipamiento armaDistancia(String arma, String nombre, String descripcion, int nivelRequerido,
		                                          int poder, Efecto efecto, TipoDeDano tipoDeDano, int id,
		                                          Connection connection) throws SQLException {
				int alcance = obtenerValorEntero(QUERY_ALCANCE_ARMA_DISTANCIA, id, connection);
				return switch (arma) {
						case "arco" -> {
								double probabilidadDobleDisparo = obtenerValorDecimal(QUERY_PROBABILIDAD_DOBLE_DISPARO_BALLESTA, id, connection);
								yield new Arco(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, alcance, probabilidadDobleDisparo);
						}
						case "ballesta" -> {
								int penetracionArmadura = obtenerValorEntero(QUERY_PENETRACION_ARMADURA_BALLESTA, id, connection);
								yield new Ballesta(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, alcance, penetracionArmadura);
						}
						default -> null;
				};
		}

		private static Equipamiento armadura(String armadura, String nombre, String descripcion, int nivelRequerido,
		                                      int poder, Efecto efecto, int id, Connection connection) throws SQLException {
				int defensa = obtenerValorEntero(QUERY_DEFENSA_ARMADURA, id, connection);
				return switch (armadura) {
						case "armadura_de_placas" -> {
								boolean impermeable = obtenerValorBooleano(QUERY_IMPERMEABLE_ARMADURA_DE_PLACAS, id, connection);
								yield new ArmaduraDePlacas(nombre, descripcion, nivelRequerido, poder, efecto, defensa, impermeable);
						}
						case "armadura_ligera" -> {
								boolean flexible = obtenerValorBooleano(QUERY_FLEXIBLE_ARMADURA_LIGERA, id, connection);
								yield new ArmaduraLigera(nombre, descripcion, nivelRequerido, poder, efecto, defensa, flexible);
						}
						case "armadura_pesada" -> {
								int resistenciaExtra = obtenerValorEntero(QUERY_DEFENSA_EXTRA_ARMADURA, id, connection);
								yield new ArmaduraPesada(nombre, descripcion, nivelRequerido, poder, efecto, defensa, resistenciaExtra);
						}
						default -> null;
				};
		}

		private static Equipamiento accesorio(String accesorio, String nombre, String descripcion, int nivelRequerido,
		                                      int poder, Efecto efecto, int id, Connection connection) throws SQLException {
				return switch (accesorio) {
						case "anillo" -> {
								Material material = obtenerMaterial(id, connection);
								yield new Anillo(nombre, descripcion, nivelRequerido, poder, efecto, material);
						}
						case "amuleto" -> {
								Piedra gema = obtenerGema(id, connection);
								yield new Amuleto(nombre, descripcion, nivelRequerido, poder, efecto, gema);
						}
						default -> null;
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

		private static double obtenerValorDecimal(String query, int equipamientoId, Connection connection) throws SQLException {
				try (PreparedStatement ps = connection.prepareStatement(query)) {
						ps.setInt(1, equipamientoId);
						try (ResultSet rs = ps.executeQuery()) {
								return rs.next() ? rs.getDouble(1) : 0.0;
						}
				}
		}

		private static boolean obtenerValorBooleano(String query, int equipamientoId, Connection connection) throws SQLException {
				try (PreparedStatement ps = connection.prepareStatement(query)) {
						ps.setInt(1, equipamientoId);
						try (ResultSet rs = ps.executeQuery()) {
								return rs.next() && rs.getInt(1) == 1;
						}
				}
		}

		private static TipoDeCorte obtenerTipoCorte(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_TIPO_CORTE_ESPADA, equipamientoId, connection);
				return resultado != null ? TipoDeCorte.fromString(resultado) : null;
		}

		private static TipoDeDano obtenerTipoDeDano(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_TIPO_DANO_ARMA, equipamientoId, connection);
				return resultado != null ? TipoDeDano.fromString(resultado) : null;
		}

		private static Material obtenerMaterial(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_MATERIAL_HACHA, equipamientoId, connection);
				return resultado != null ? Material.fromString(resultado) : null;
		}

		private static Piedra obtenerGema(int equipamientoId, Connection connection) throws SQLException {
				String resultado = obtenerValorCadena(QUERY_GEMA_PRINCIPAL_CETRO, equipamientoId, connection);
				return resultado != null ? Piedra.fromString(resultado) : null;
		}
}