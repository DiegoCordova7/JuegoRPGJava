package Aplicaciones.AplicacionRPG.Ui;

import Aplicaciones.AplicacionRPG.Enums.Clase;
import Aplicaciones.AplicacionRPG.Habilidades.Habilidad;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Arma;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.Armadura;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.Accesorio;
import Aplicaciones.AplicacionRPG.Logica.JuegoRPG;
import Aplicaciones.AplicacionRPG.Logica.SistemaRPG;
import Aplicaciones.AplicacionRPG.Personajes.APersonajes;
import Aplicaciones.AplicacionRPG.Tests.TestUnitario;
import Utilidades.Menus.Menu;
import java.util.List;
import static Utilidades.Impresora.imprimir;
/**
	* Clase que maneja los menús de la aplicación RPG.
	*/
public class Menus {
		public static final int CAPACIDAD_MAXIMA_PERSONAJES = 10;
		public static JuegoRPG juego = new JuegoRPG(CAPACIDAD_MAXIMA_PERSONAJES);
		static SistemaRPG sistema = new SistemaRPG();
		static TestUnitario testUnitario = new TestUnitario();
		/**
			* Muestra el menú principal del juego.
			*/
		public static void menuPrincipal() {
				Menu menuPrincipal = new Menu("Menu Principal");
				menuPrincipal.agregarOpcion("Crear Personaje", Menus::menuCreacion);
				menuPrincipal.agregarOpcion("Eliminar Personaje", () -> sistema.eliminarPersonaje());
				menuPrincipal.agregarOpcion("Mostrar personajes", () -> juego.mostrarPersonajes());
				menuPrincipal.agregarOpcion("Ver Almacen De Habilidades", () -> sistema.verAlmacenHabilidades());
				menuPrincipal.agregarOpcion("Ver Datos De Personaje", () -> sistema.verDatosPersonaje());
				menuPrincipal.agregarOpcion("Cambiar Habilidades", () -> sistema.cambiarHabilidades());
				menuPrincipal.agregarOpcion("Administrar Equipamiento", () -> menuEquipamiento(sistema.tomarPersonaje()));
				menuPrincipal.agregarOpcion("Subir Nivel", () -> sistema.subirNivel());
				menuPrincipal.agregarOpcion("Test", () -> testUnitario.runTest());
				menuPrincipal.ejecutar();
		}
		/**
			* Muestra el menú para crear un personaje.
			*/
		public static void menuCreacion() {
				Menu menuCreacion = new Menu("Menu Creacion");
				String[] clases = Clase.clases();
				for (String clase : clases) {
						menuCreacion.agregarOpcion(clase, () -> sistema.crearPersonaje(juego, clase));
				}
				menuCreacion.ejecutar();
		}
		/**
			* Muestra el menú de equipamiento para un personaje.
			*
			* @param personaje Personaje al que se le administrará el equipamiento.
			*/
		public static void menuEquipamiento(APersonajes personaje) {
				if (personaje == null) {
						return;
				}
				Menu menuEquipamiento = new Menu("Menu Equipamiento");
				menuEquipamiento.agregarOpcion("Armadura", () -> menuArmaduras(personaje));
				menuEquipamiento.agregarOpcion("Arma", () -> menuArmas(personaje));
				menuEquipamiento.agregarOpcion("Accesorios", () -> menuAccesorios(personaje));
				menuEquipamiento.ejecutar();
		}
		/**
			* Muestra el menú para cambiar habilidades de un personaje.
			*
			* @param personaje Personaje al cual se le cambiarán las habilidades.
			*/
		public static void menuHabilidades(APersonajes personaje) {
				if (personaje == null) {
						return;
				}
				Menu menuHabilidades = new Menu("Menu Habilidades");
				imprimir("Cambia la habilidad de tu eleccion");
				Habilidad[] habilidadJugador = personaje.getHabilidadesActivas();
				for (int i = 0; i < habilidadJugador.length; i++) {
						Habilidad habilidad = habilidadJugador[i];
						String nombreHabilidad = habilidad != null ? habilidad.getNombre() : "Vacio";
						final int indiceHabilidad = i;
						menuHabilidades.agregarOpcion(nombreHabilidad, () ->
										sistema.reemplazarHabilidad(personaje, nombreHabilidad, indiceHabilidad));
				}
				menuHabilidades.ejecutar();
		}
		/**
			* Muestra el menú para administrar accesorios de un personaje.
			*
			* @param personaje Personaje al que se le cambiarán los accesorios.
			*/
		public static void menuAccesorios(APersonajes personaje) {
				if (personaje == null) {
						return;
				}
				Menu menuAccesorios = new Menu("Menu Accesorios");
				imprimir("Cambia el accesorio de tu eleccion");
				List<Accesorio> accesoriosEquipados = personaje.getAccesoriosEquipados();
				for (int i = 0; i < accesoriosEquipados.size(); i++) {
						Accesorio accesorio = accesoriosEquipados.get(i);
						String nombreAccesorio = accesorio != null ? accesorio.getNombre() : "Vacio";
						final int indiceAccesorio = i;
						menuAccesorios.agregarOpcion(nombreAccesorio, () ->
										sistema.reemplazarAccesorio(personaje, nombreAccesorio, indiceAccesorio));
				}
				menuAccesorios.ejecutar();
		}
		/**
			* Muestra el menú para administrar armas de un personaje.
			*
			* @param personaje Personaje al que se le cambiará el arma.
			*/
		public static void menuArmas(APersonajes personaje) {
				if (personaje == null) {
						return;
				}
				Menu menuArmas = new Menu("Menu Armas");
				imprimir("Cambia el arma de tu eleccion");
				Arma armaEquipada = personaje.getArmaEquipada();
				String nombreArma = armaEquipada != null ? armaEquipada.getNombre() : "Sin arma equipada";
				menuArmas.agregarOpcion(nombreArma, () ->
								sistema.reemplazarArma(personaje, nombreArma));
				menuArmas.ejecutar();
		}
		/**
			* Muestra el menú para administrar armaduras de un personaje.
			*
			* @param personaje Personaje al que se le cambiará la armadura.
			*/
		public static void menuArmaduras(APersonajes personaje) {
				if (personaje == null) {
						return;
				}
				Menu menuArmaduras = new Menu("Menu Armaduras");
				imprimir("Cambia la armadura de tu eleccion");
				Armadura armaduraEquipada = personaje.getArmaduraEquipada();
				String nombreArmadura = armaduraEquipada != null ? armaduraEquipada.getNombre() : "Sin armadura equipada";
				menuArmaduras.agregarOpcion(nombreArmadura, () ->
								sistema.reemplazarArmadura(personaje, nombreArmadura));
				menuArmaduras.ejecutar();
		}
}