package Aplicaciones.AplicacionRPG.Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static Utilidades.Impresora.imprimir;

public class SQLiteDB {

		private static final String DB_URL = "jdbc:sqlite:src/Aplicaciones/AplicacionRPG/Datos/rpg.db";

		public static Connection getConnection() throws SQLException {
				return DriverManager.getConnection(DB_URL);
		}

		public static void inicializarBaseDeDatos() {
				try (Connection conn = getConnection();
				     Statement stmt = conn.createStatement()) {
						conn.setAutoCommit(false);
						crearTablasPrincipales(stmt);
						crearTablasRelaciones(stmt);
						conn.commit();
						imprimir("Base de datos inicializada correctamente.");
				} catch (SQLException e) {
						imprimir(String.format("Error al inicializar la base de datos: %s", e.getMessage()));
				}
		}

		private static void crearTablasPrincipales(Statement stmt) throws SQLException {
				String[] tablasPrincipales = {
								"CREATE TABLE IF NOT EXISTS clases (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"nombre TEXT NOT NULL UNIQUE);",

								"CREATE TABLE IF NOT EXISTS tipos_dano (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"tipo TEXT NOT NULL);",

								"CREATE TABLE IF NOT EXISTS efectos (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"nombre TEXT NOT NULL);",

								"CREATE TABLE IF NOT EXISTS habilidades (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"nombre TEXT NOT NULL, " +
												"descripcion TEXT NOT NULL, " +
												"nivel_requerido INTEGER NOT NULL, " +
												"es_ofensiva BOOLEAN NOT NULL, " +
												"dano INTEGER, " +
												"uso_maximo INTEGER NOT NULL, " +
												"tipo_habilidad TEXT NOT NULL, " +
												"id_tipo_dano INTEGER, " +
												"id_efecto INTEGER, " +
												"turnos_de_efecto INTEGER NOT NULL, " +
												"FOREIGN KEY(id_tipo_dano) REFERENCES tipos_dano(id), " +
												"FOREIGN KEY(id_efecto) REFERENCES efectos(id));",

								"CREATE TABLE IF NOT EXISTS equipamientos (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"nombre VARCHAR(255) NOT NULL, " +
												"tipo VARCHAR(50) NOT NULL, " +
												"descripcion TEXT NOT NULL, " +
												"nivel_requerido INTEGER NOT NULL, " +
												"poder INTEGER NOT NULL, " +
												"id_efecto INTEGER, " +
												"FOREIGN KEY(id_efecto) REFERENCES efectos(id));"
				};

				ejecutarLotes(stmt, tablasPrincipales);
		}

		private static void crearTablasRelaciones(Statement stmt) throws SQLException {
				String[] tablasRelaciones = {
								//Esta cambia ya apartir de aca se ponen las 15 de los equipamientos, ya existe la que los enlaza a jugador,
								//luego las agregas
								"CREATE TABLE IF NOT EXISTS armas (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"id_tipo_dano INTEGER NOT NULL, " +
												"FOREIGN KEY(id) REFERENCES equipamientos(id), " +
												"FOREIGN KEY(id_tipo_dano) REFERENCES tipos_dano(id));",

								"CREATE TABLE IF NOT EXISTS armaduras (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"defensa INTEGER NOT NULL, " +
												"FOREIGN KEY(id) REFERENCES equipamientos(id));",

								"CREATE TABLE IF NOT EXISTS personajes (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"nombre TEXT NOT NULL, " +
												"id_clase INTEGER NOT NULL, " +
												"vida_base REAL NOT NULL, " +
												"fuerza_fisica_base REAL NOT NULL, " +
												"fuerza_magica_base REAL NOT NULL, " +
												"fuerza_distancia_base REAL NOT NULL, " +
												"defensa_fisica_base INTEGER NOT NULL, " +
												"defensa_magica_base INTEGER NOT NULL, " +
												"defensa_distancia_base INTEGER NOT NULL, " +
												"mana_base REAL NOT NULL, " +
												"velocidad_base REAL NOT NULL, " +
												"reduccion_dano_base REAL NOT NULL, " +
												"FOREIGN KEY(id_clase) REFERENCES clases(id));",

								"CREATE TABLE IF NOT EXISTS stats_unicos_arquero (" +
												"id_personaje INTEGER PRIMARY KEY, " +
												"precision REAL NOT NULL, " +
												"FOREIGN KEY(id_personaje) REFERENCES personajes(id));",

								"CREATE TABLE IF NOT EXISTS equipos_equipados (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
												"id_jugador INTEGER NOT NULL, " +
												"id_equipo INTEGER NOT NULL, " +
												"slot VARCHAR(50) NOT NULL, " +
												"FOREIGN KEY (id_jugador) REFERENCES jugadores(id), " +
												"FOREIGN KEY (id_equipo) REFERENCES equipamientos(id), " +
												"UNIQUE (id_jugador, slot));",

								"CREATE TABLE IF NOT EXISTS habilidades_clases (" +
										"id INTEGER PRIMARY KEY AUTOINCREMENT," +
										"id_habilidad INTEGER NOT NULL," +
										"id_clase INTEGER NOT NULL," +
										"FOREIGN KEY (id_habilidad) REFERENCES habilidades(id)," +
										"FOREIGN KEY (id_clase) REFERENCES clases(id)," +
										"UNIQUE (id_habilidad, id_clase));",

								"CREATE TABLE IF NOT EXISTS habilidades_jugador (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT," +
												"id_jugador INTEGER NOT NULL," +
												"id_habilidad INTEGER NOT NULL," +
												"FOREIGN KEY (id_jugador) REFERENCES jugadores(id)," +
												"FOREIGN KEY (id_habilidad) REFERENCES habilidades(id)," +
												"UNIQUE (id_jugador, id_habilidad));",

								"CREATE TABLE IF NOT EXISTS habilidades_activas (" +
												"id INTEGER PRIMARY KEY AUTOINCREMENT," +
												"id_jugador INTEGER NOT NULL," +
												"id_habilidad INTEGER NOT NULL," +
												"slot INTEGER NOT NULL," + //Posición de 1 a 4 para las habilidades activas
												"FOREIGN KEY (id_jugador) REFERENCES jugadores(id)," +
												"FOREIGN KEY (id_habilidad) REFERENCES habilidades(id)," +
												"UNIQUE (id_jugador, slot));"
				};

				ejecutarLotes(stmt, tablasRelaciones);
		}

		private static void ejecutarLotes(Statement stmt, String[] sqlStatements) throws SQLException {
				for (String sql : sqlStatements) {
						stmt.execute(sql);
				}
		}
}