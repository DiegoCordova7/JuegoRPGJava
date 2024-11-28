package Aplicaciones.AplicacionRPG.Equipamiento;

import Utilidades.Estructuras.Lista;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.imprimirEncabezado;
//import static Aplicaciones.AplicacionRPG.Equipamientos.EquipamientoManager.cargarEquipamientosDesdeArchivo;

/**
	* Representa el almacen global de equipamientos disponibles en el juego RPG.
	* Proporciona metodos estaticos para cargar, agregar, buscar y mostrar equipamientos.
	*/
public class AlmacenEquipamientos {

		private static List<Equipamiento> equipamientosDisponibles = new ArrayList<>();

		/**
			* Carga los equipamientos desde un archivo de configuracion.
			*
			* @param rutaArchivo Ruta al archivo desde el cual se cargaran los equipamientos.
			*                    El archivo debe tener el formato adecuado para ser procesado.
			*/
		public static void cargarEquipamientos(String rutaArchivo) {
				//equipamientosDisponibles = cargarEquipamientosDesdeArchivo(rutaArchivo);
				if (equipamientosDisponibles.isEmpty()) {
						System.err.println("No se pudieron cargar los equipamientos en el almacen.");
				}
		}

		/**
			* Agrega un equipamiento al almacen global.
			*
			* @param equipamiento El equipamiento a agregar.
			*/
		public static void agregarEquipamiento(Equipamiento equipamiento) {
				if (equipamiento == null) {
						System.err.println("No se puede agregar un equipamiento nulo.");
						return;
				}

				if (buscarEquipamiento(equipamiento.getNombre()) != null) {
						System.err.printf("El equipamiento '%s' ya existe en el almacen.\n", equipamiento.getNombre());
						return;
				}

				equipamientosDisponibles.add(equipamiento);
				System.out.printf("Equipamiento agregado exitosamente: %s\n", equipamiento.getNombre());
		}

		/**
			* Obtiene la lista de todos los equipamientos disponibles en el almacen.
			*
			* @return Lista de equipamientos disponibles.
			*/
		public static List<Equipamiento> getEquipamientosDisponibles() {
				return equipamientosDisponibles;
		}

		/**
			* Busca un equipamiento en el almacen por su nombre.
			*
			* @param nombre El nombre del equipamiento a buscar.
			* @return El equipamiento encontrado, o null si no se encuentra.
			*/
		public static Equipamiento buscarEquipamiento(String nombre) {
				for (Equipamiento equipamiento : equipamientosDisponibles) {
						if (equipamiento.getNombre().equalsIgnoreCase(nombre)) {
								return equipamiento;
						}
				}
				return null;
		}

		/**
			* Muestra todos los equipamientos disponibles en el almacen.
			*/
		public static void mostrarEquipamientos() {
				imprimirEncabezado("Equipamientos disponibles en el almacen:");
				Lista equipamientos = new Lista();
				for (Equipamiento equipamiento : equipamientosDisponibles) {
						equipamientos.agregar(equipamiento.getNombre());
				}
				equipamientos.imprimir(true);
		}

		public List<Accesorio> getAccesorios() {
				List<Accesorio> accesorios = new ArrayList<>();
				for (Equipamiento equipamiento : equipamientosDisponibles) {
						if (equipamiento instanceof Accesorio) {
								accesorios.add((Accesorio) equipamiento);
						}
				}
				return accesorios;
		}

		public List<Arma> getArmas() {
				List<Arma> armas = new ArrayList<>();
				for (Equipamiento equipamiento : equipamientosDisponibles) {
						if (equipamiento instanceof Arma) {
								armas.add((Arma) equipamiento);
						}
				}
				return armas;
		}

		public List<Armadura> getArmaduras() {
				List<Armadura> armaduras = new ArrayList<>();
				for (Equipamiento equipamiento : equipamientosDisponibles) {
						if (equipamiento instanceof Armadura) {
								armaduras.add((Armadura) equipamiento);
						}
				}
				return armaduras;
		}
}
