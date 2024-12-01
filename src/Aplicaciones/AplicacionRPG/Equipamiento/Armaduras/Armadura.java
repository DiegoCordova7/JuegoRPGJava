package Aplicaciones.AplicacionRPG.Equipamiento.Armaduras;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Equipamiento.Equipamiento;

public abstract class Armadura extends Equipamiento {
		private int defensa;

		public Armadura(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, int defensa) {
				super(nombre, descripcion, "Armadura", nivelRequerido, poder, efecto);
				this.defensa = defensa;
		}

		public int getDefensa() {
				return defensa;
		}
		
		public void setDefensa(int defensa) {
				this.defensa = defensa;
		}
}