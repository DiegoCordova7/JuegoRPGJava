package Aplicaciones.AplicacionRPG.Logica;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.Accesorio;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Arma;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.Armadura;
import Aplicaciones.AplicacionRPG.Habilidades.Habilidad;
import Aplicaciones.AplicacionRPG.Habilidades.HabilidadManager;
import Aplicaciones.AplicacionRPG.Personajes.APersonajes;
import Aplicaciones.AplicacionRPG.Personajes.Arquero;
import Aplicaciones.AplicacionRPG.Personajes.Guerrero;
import Aplicaciones.AplicacionRPG.Personajes.Mago;
import Utilidades.Estructuras.Lista;
import Utilidades.TipoDato;
import java.util.List;
import static Aplicaciones.AplicacionRPG.Ui.Menus.*;
import static Utilidades.Impresora.*;
import static Utilidades.Impresora.imprimir;
import static Utilidades.UltraTeclado.input;
/**
	* Clase que representa el sistema que maneja las acciones y la logica principal
	* del juego RPG. Permite crear personajes, gestionar habilidades, y realizar
	* otras interacciones dentro del sistema de juego.
	*/
public class SistemaRPG {
		List<Habilidad> todasLasHabilidades = HabilidadManager.cargarHabilidadesDesdeDB();
		/**
			* Crea un nuevo personaje en el juego, eligiendo una clase y un nombre.
			*
			* @param juego El objeto del juego que gestionara los personajes.
			* @param clase La clase del personaje a crear (Guerrero, Mago, Arquero).
			*/
		public void crearPersonaje(JuegoRPG juego, String clase) {
				String parte = "Crear_Personaje";
				imprimirEncabezado("Creacion De Personaje");
				if (juego.getContador() >= CAPACIDAD_MAXIMA_PERSONAJES) {
						imprimirError(parte, "Maximo de personajes alcanzado.");
						return;
				}
				String nombre = input(parte, "Nombre", TipoDato.TEXTO, false, _ -> {});
				APersonajes nuevoPersonaje = crearInstanciaPersonaje(clase, nombre);
				if (nuevoPersonaje != null) {
						juego.agregarPersonaje(nuevoPersonaje);
				}
		}
		/**
			* Crea una instancia de un personaje segun la clase seleccionada.
			*
			* @param clase La clase del personaje a crear (Guerrero, Mago, Arquero).
			* @param nombre El nombre del personaje.
			* @return El objeto APersonajes correspondiente.
			*/
		private APersonajes crearInstanciaPersonaje(String clase, String nombre) {
				return switch (clase) {
						case "Guerrero" -> new Guerrero(nombre, clase, todasLasHabilidades);
						case "Mago" -> new Mago(nombre, clase, todasLasHabilidades);
						case "Arquero" -> new Arquero(nombre, clase, todasLasHabilidades);
						default -> null;
				};
		}
		/**
			* Muestra un aviso cuando no hay personajes disponibles.
			*/
		public void avisoNoPersonajes() {
				imprimirAviso("Personajes_Nulos", "No hay personajes.");
		}
		/**
			* Elimina un personaje del juego utilizando su ID.
			*/
		public void eliminarPersonaje() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return;
				}
				imprimirEncabezado("Eliminacion de Personaje");
				String parte = "Eliminacion_Personaje";
				juego.mostrarPersonajes();
				int idEliminar = input(parte, "Id", TipoDato.ENTERO_POSITIVO, false, _ -> {});
				juego.eliminarPersonaje(idEliminar);
		}
		/**
			* Permite subir el nivel de un personaje, añadiendo experiencia.
			*/
		public void subirNivel() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return;
				}
				imprimirEncabezado("Subir Nivel");
				String parte = "Subir_Nivel";
				juego.mostrarPersonajes();
				int idPersonaje = input(parte, "Id", TipoDato.ENTERO_POSITIVO, false, _ -> {});
				for (APersonajes personaje : juego.personajes) {
						if (personaje != null && personaje.getId() == idPersonaje) {
								int xp = input(parte, "Xp", TipoDato.ENTERO_POSITIVO, false, _ -> {});
								personaje.agregarXP(xp);
						}
				}
		}
		/**
			* Obtiene el ID de un personaje para realizar diversas acciones con el.
			*
			* @return El ID del personaje seleccionado.
			*/
		public int obtenerIdPersonaje() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return 0;
				}
				String parte = "Obtener_Id_Personaje";
				juego.mostrarPersonajes();
				return input(parte, "Id", TipoDato.ENTERO_POSITIVO, false, _ -> {});
		}
		/**
			* Muestra el almacen de habilidades de un personaje.
			*/
		public void verAlmacenHabilidades() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return;
				}
				imprimirEncabezado("Almacen De Habilidades");
				int id = obtenerIdPersonaje();
				juego.mostrarHabilidades(id);
		}
		/**
			* Muestra los datos de un personaje.
			*/
		public void verDatosPersonaje() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return;
				}
				imprimirEncabezado("Ver Datos De Personaje");
				int id = obtenerIdPersonaje();
				juego.mostrarDatosPersonajes(id);
		}
		/**
			* Permite cambiar las habilidades de un personaje.
			*/
		public void cambiarHabilidades() {
				if (juego.getContador() == 0) {
						avisoNoPersonajes();
						return;
				}
				imprimirEncabezado("Cambiar Habildades De Personaje");
				int id = obtenerIdPersonaje();
				APersonajes personajeBuscado = null;
				for (APersonajes personaje : juego.personajes) {
						if (personaje != null && personaje.getId() == id) {
								personajeBuscado = personaje;
						}
				}
				assert personajeBuscado != null;
				menuHabilidades(personajeBuscado);
		}
		/**
			* Reemplaza una habilidad activa de un personaje por una habilidad disponible.
			*
			* @param personaje El personaje cuyo habilidad se reemplazara.
			* @param nombreHabilidad El nombre de la habilidad a reemplazar.
			* @param numeroHabilidad El numero de la habilidad activa a reemplazar.
			*/
		public void reemplazarHabilidad(APersonajes personaje, String nombreHabilidad, int numeroHabilidad) {
				String parte = "Reemplazar_Habilidad";
				imprimir(String.format("Habilidad a cambiar: %s", nombreHabilidad));
				imprimir("Habilidades Disponibles:");
				List<Habilidad> habilidadesDisponibles = personaje.getHabilidadesDisponibles();
				Lista habilidades = new Lista();
				for (Habilidad habilidadesDisponible : habilidadesDisponibles) {
						habilidades.agregar(habilidadesDisponible.getNombre());
				}
				habilidades.imprimir(true);
				int seleccion = input(parte, "Seleccione la habilidad por la que desea cambiar",
								TipoDato.ENTERO_POSITIVO, false, _ -> {});
				if (seleccion > habilidadesDisponibles.size()) {
						return;
				}
				Habilidad habilidadSeleccionada = habilidadesDisponibles.get(seleccion - 1);
				personaje.reemplazarHabilidad(numeroHabilidad, habilidadSeleccionada);
				imprimir(String.format("La habilidad %s ha sido reemplazada por %s.", nombreHabilidad,
								habilidadSeleccionada.getNombre()));
		}
		/**
			* Reemplaza el accesorio del personaje por otro seleccionado de los accesorios disponibles.
			*
			* @param personaje El personaje cuyo accesorio se desea reemplazar.
			* @param nombreAccesorio El nombre del accesorio que se desea reemplazar.
			* @param indiceAccesorio El índice del accesorio a reemplazar en el inventario del personaje.
			*/
		public void reemplazarAccesorio(APersonajes personaje, String nombreAccesorio, int indiceAccesorio) {
				String parte = "Reemplazar_Accesorio";
				imprimir(String.format("Accesorio a cambiar: %s", nombreAccesorio));
				imprimir("Accesorios Disponibles:");
				List<Accesorio> accesoriosDisponibles = personaje.getAccesoriosDisponibles();
				Lista accesorios = new Lista();
				for (Accesorio accesorioDisponible : accesoriosDisponibles) {
						accesorios.agregar(accesorioDisponible.getNombre());
				}
				accesorios.imprimir(true);
				int seleccion = input(parte, "Seleccione el accesorio por la que desea cambiar",
								TipoDato.ENTERO_POSITIVO, false, _ -> {});
				if (seleccion > accesoriosDisponibles.size()) {
						return;
				}
				Accesorio accesorioSeleccionado = accesoriosDisponibles.get(seleccion - 1);
				personaje.reemplazarAccesorio(indiceAccesorio, accesorioSeleccionado);
				imprimir(String.format("El Accesorio %s ha sido reemplazada por %s.", nombreAccesorio,
								accesorioSeleccionado.getNombre()));
		}
		/**
			* Reemplaza el arma del personaje por otra seleccionada de las armas disponibles.
			*
			* @param personaje El personaje cuyo arma se desea reemplazar.
			* @param nombreArma El nombre del arma que se desea reemplazar.
			*/
		public void reemplazarArma(APersonajes personaje, String nombreArma) {
				String parte = "Reemplazar_Armadura";
				imprimir(String.format("Armadura a cambiar: %s", nombreArma));
				imprimir("Armaduras Disponibles:");
				List<Arma> armasDisponibles = personaje.getArmaDisponibles();
				Lista arma = new Lista();
				for (Arma armaDisponible : armasDisponibles) {
						arma.agregar(armaDisponible.getNombre());
				}
				arma.imprimir(true);
				int seleccion = input(parte, "Seleccione el arma por la que desea cambiar",
								TipoDato.ENTERO_POSITIVO, false, _ -> {});
				if (seleccion > armasDisponibles.size()) {
						imprimir("Seleccion invalida.");
						return;
				}
				Arma armaSeleccionada = armasDisponibles.get(seleccion - 1);
				personaje.reemplazarArma(armaSeleccionada);
				imprimir(String.format("La arma %s ha sido reemplazada por %s.", nombreArma, armaSeleccionada.getNombre()));
		}
		/**
			* Reemplaza la armadura del personaje por otra seleccionada de las armaduras disponibles.
			*
			* @param personaje El personaje cuya armadura se desea reemplazar.
			* @param nombreArmadura El nombre de la armadura que se desea reemplazar.
			*/
		public void reemplazarArmadura(APersonajes personaje, String nombreArmadura) {
				String parte = "Reemplazar_Armadura";
				imprimir(String.format("Armadura a cambiar: %s", nombreArmadura));
				imprimir("Armaduras Disponibles:");
				List<Armadura> armadurasDisponibles = personaje.getArmaduraDisponibles();
				Lista armaduras = new Lista();
				for (Armadura armaduraDisponible : armadurasDisponibles) {
						armaduras.agregar(armaduraDisponible.getNombre());
				}
				armaduras.imprimir(true);
				int seleccion = input(parte, "Seleccione la armadura por la que desea cambiar",
								TipoDato.ENTERO_POSITIVO, false, _ -> {});
				if (seleccion > armadurasDisponibles.size()) {
						imprimir("Seleccion invalida.");
						return;
				}
				Armadura armaduraSeleccionada = armadurasDisponibles.get(seleccion - 1);
				personaje.reemplazarArmadura(armaduraSeleccionada);
				imprimir(String.format("La armadura %s ha sido reemplazada por %s.", nombreArmadura,
								armaduraSeleccionada.getNombre()));
		}
		/**
			* Obtiene el personaje basado en su ID.
			*
			* @return El personaje correspondiente al ID buscado, o null si no se encuentra.
			*/
		public APersonajes tomarPersonaje() {
				int id = obtenerIdPersonaje();
				APersonajes personajeBuscado = null;
				for (APersonajes personaje : juego.personajes) {
						if (personaje != null && personaje.getId() == id) {
								personajeBuscado = personaje;
						}
				}
				return personajeBuscado;
		}
}