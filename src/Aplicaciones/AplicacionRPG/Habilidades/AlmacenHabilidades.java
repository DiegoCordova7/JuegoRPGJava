package Aplicaciones.AplicacionRPG.Habilidades;
import Utilidades.Estructuras.Lista;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.imprimirEncabezado;
/**
	* Representa el almacen global de habilidades disponibles en el juego RPG.
	* Proporciona metodos estaticos para cargar, buscar y mostrar habilidades.
	*/
public class AlmacenHabilidades {
		private static List<Habilidad> habilidadesDisponibles = new ArrayList<>();
		/**
			* Carga las habilidades desde un archivo de configuracion.
			*
			*/
		public static void cargarHabilidades() {
				habilidadesDisponibles = HabilidadManager.cargarHabilidadesDesdeDB();
				if (habilidadesDisponibles.isEmpty()) {
						System.err.println("No se pudieron cargar las habilidades en el almacen.");
				}
		}
		/**
			* Obtiene la lista de todas las habilidades disponibles en el almacen.
			*
			* @return Lista de habilidades disponibles.
			*/
		public static List<Habilidad> getHabilidadesDisponibles() {
				return habilidadesDisponibles;
		}
		/**
			* Busca una habilidad en el almacén por su nombre.
			*
			* @param nombre El nombre de la habilidad a buscar.
			* @return La habilidad encontrada, o null si no se encuentra.
			*/
		public static Habilidad buscarHabilidad(String nombre) {
				for (Habilidad habilidad : habilidadesDisponibles) {
						if (habilidad.getNombre().equalsIgnoreCase(nombre)) {
								return habilidad;
						}
				}
				System.err.printf(String.format("Habilidad no encontrada: %s", nombre));
				return null;
		}
		/**
			* Muestra todas las habilidades disponibles en el almacen.
			*/
		public static void mostrarHabilidades() {
				imprimirEncabezado("Habilidades disponibles en el almacen:");
				Lista habilidades = new Lista();
				for (Habilidad habilidad : habilidadesDisponibles) {
						habilidades.agregar(habilidad.getNombre());
				}
				habilidades.imprimir(true);
		}
}