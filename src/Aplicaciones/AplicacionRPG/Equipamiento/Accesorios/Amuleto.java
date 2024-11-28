package Aplicaciones.AplicacionRPG.Equipamiento.Accesorios;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Piedra;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorio;

public class Amuleto extends Accesorio {
		private Piedra piedra;

		public Amuleto(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, Piedra piedra) {
				super(nombre, descripcion, nivelRequerido, poder, efecto);
				this.piedra = piedra;
		}

		public Piedra getPiedra() {
				return piedra;
		}

		public void setPiedra(Piedra piedra) {
				this.piedra = piedra;
		}
}