package Aplicaciones.AplicacionRPG;

import Aplicaciones.AplicacionRPG.Equipamiento.Equipamiento;
import Aplicaciones.AplicacionRPG.Equipamiento.EquipamientoManager;
import Aplicaciones.AplicacionRPG.Habilidades.AlmacenHabilidades;
import Aplicaciones.AplicacionRPG.Habilidades.Habilidad;
import Aplicaciones.AplicacionRPG.Habilidades.HabilidadManager;
import java.util.ArrayList;
import java.util.List;
/**
	* Clase encargada de gestionar los recursos, como las habilidades.
	*/
public class GestorRecursos {
		private static List<Habilidad> habilidades = new ArrayList<>();
		private static List<Equipamiento> equipamientos = new ArrayList<>();
		/**
			* Carga las habilidades desde un archivo.
			*
			*/
		public static void cargarHabilidades() {
				habilidades = HabilidadManager.cargarHabilidadesDesdeDB();
				AlmacenHabilidades.cargarHabilidades();
		}
		/**
			* Obtiene la lista de habilidades cargadas.
			*
			* @return Lista de habilidades.
			*/
		public static List<Habilidad> getHabilidades() {
				return habilidades;
		}
		/**
			* Carga los equipamientos desde la DB.
			*
			*/
		public static void cargarEquipamientos() {
				equipamientos = EquipamientoManager.cargarEquipamientosDesdeBD();
		}
		/**
			* Obtiene la lista de equipamientos cargados.
			*
			* @return Lista de Equipamientos.
			*/
		public static List<Equipamiento> getEquipamientos() {
				return equipamientos;
		}
}