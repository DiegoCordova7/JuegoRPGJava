package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Arma;

public class Arco extends Arma {
		private int alcance;

		public Arco(String nombre, String descripcion, int nivelRequerido, int poder,
		            Efecto efecto, TipoDeDano tipoDeDano, int alcance) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano);
				this.alcance = alcance;
		}

		public int getAlcance() {
				return alcance;
		}

		public void setAlcance(int alcance) {
				this.alcance = alcance;
		}
}