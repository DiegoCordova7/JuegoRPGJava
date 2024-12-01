package Aplicaciones.AplicacionRPG.Equipamiento.Accesorios;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Equipamiento.Equipamiento;

public abstract class Accesorio extends Equipamiento {

		public Accesorio(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto) {
				super(nombre, descripcion, "Accesorio", nivelRequerido, poder, efecto);
		}
}
