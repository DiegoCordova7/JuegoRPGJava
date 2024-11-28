package Aplicaciones.AplicacionRPG.Logica;
import Aplicaciones.AplicacionBase;
import Aplicaciones.AplicacionRPG.Datos.SQLiteDB;
import Aplicaciones.AplicacionRPG.GestorRecursos;
import Aplicaciones.AplicacionRPG.Ui.Menus;

public class AplicacionRPG extends AplicacionBase {
		/**
			* Constructor que inicializa la aplicacion con un log especifico.
			*
			* @param nombreArchivo El nombre del archivo de log para esta aplicacion.
			*/
		public AplicacionRPG(String nombreArchivo) {
				super(nombreArchivo);
		}
		/**
			* Metodo abstracto que debe ser implementado por las clases hijas para ejecutar la logica de la aplicacion.
			*/
		@Override
		protected void logicaApp() {
				SQLiteDB.inicializarBaseDeDatos();
				GestorRecursos.cargarEquipamientos();
				GestorRecursos.cargarHabilidades();
				Menus.menuPrincipal();
		}
}