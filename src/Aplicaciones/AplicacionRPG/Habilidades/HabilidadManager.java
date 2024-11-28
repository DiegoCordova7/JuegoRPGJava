package Aplicaciones.AplicacionRPG.Habilidades;

import Aplicaciones.AplicacionRPG.Datos.SQLiteDB;
import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HabilidadManager {
		/**
			* Carga las habilidades desde la base de datos.
			*
			* @return Una lista de habilidades cargadas desde la base de datos.
			*/
		public static List<Habilidad> cargarHabilidadesDesdeDB() {
				List<Habilidad> habilidades = new ArrayList<>();
				String query = "SELECT h.nombre, h.descripcion, h.nivel_requerido, h.es_ofensiva, " +
								"h.dano, h.uso_maximo, h.tipo_habilidad, td.tipo AS tipoDano, " +
								"e.nombre AS efecto, h.turnos_de_efecto, GROUP_CONCAT(c.nombre) AS clasesPermitidas " +
								"FROM habilidades h " +
								"LEFT JOIN tipos_dano td ON h.id_tipo_dano = td.id " +
								"LEFT JOIN efectos e ON h.id_efecto = e.id " +
								"INNER JOIN habilidades_clases hc ON h.id = hc.id_habilidad " +
								"INNER JOIN clases c ON hc.id_clase = c.id " +
								"GROUP BY h.id;";

				try (Connection connection = SQLiteDB.getConnection();
				     Statement stmt = connection.createStatement();
				     ResultSet rs = stmt.executeQuery(query)) {

						while (rs.next()) {
								Habilidad habilidad = crearHabilidadDesdeRS(rs);
								habilidades.add(habilidad);
						}
				} catch (SQLException e) {
						System.err.printf("Error al acceder a la base de datos: %s%n", e.getMessage());
				}

				return habilidades;
		}

		/**
			* Crea una instancia de Habilidad a partir de un ResultSet.
			*
			* @param rs El ResultSet que contiene los datos de la habilidad.
			* @return Una instancia de Habilidad.
			* @throws SQLException Si ocurre un error al leer los datos.
			*/
		private static Habilidad crearHabilidadDesdeRS(ResultSet rs) throws SQLException {
				Habilidad habilidad = new Habilidad();
				habilidad.setNombre(rs.getString("nombre"));
				habilidad.setDescripcion(rs.getString("descripcion"));
				habilidad.setNivelRequerido(rs.getInt("nivel_requerido"));
				habilidad.setEsOfensiva(rs.getBoolean("es_ofensiva"));
				habilidad.setDano(rs.getInt("dano"));
				habilidad.setUsoMaximo(rs.getInt("uso_maximo"));
				habilidad.setTipoHabilidad(rs.getString("tipo_habilidad"));
				habilidad.setEfecto(Efecto.fromString(rs.getString("efecto")));
				habilidad.setTurnosDeEfecto(rs.getInt("turnos_de_efecto"));
				habilidad.setClasesPermitidas(parsearLista(rs.getString("clasesPermitidas")));
				habilidad.setTipoDano(TipoDeDano.fromString(rs.getString("tipoDano")));
				return habilidad;
		}

		/**
			* Parsea una lista de elementos separados por comas desde su representación en texto.
			*
			* @param valor El valor a parsear.
			* @return Una lista de cadenas representando los elementos.
			*/
		private static List<String> parsearLista(String valor) {
				if (valor == null || valor.isBlank()) {
						return new ArrayList<>();
				}
				return Arrays.stream(valor.split(",")).map(String::trim).toList();
		}
}