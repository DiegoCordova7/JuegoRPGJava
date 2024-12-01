package Aplicaciones.AplicacionRPG.Habilidades;
import java.util.ArrayList;
import java.util.List;
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
}