package Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasCuerpoCuerpo;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Arma;

public abstract class ArmaCuerpoCuerpo extends Arma {
		private double golpeCritico;
		public ArmaCuerpoCuerpo(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto,
		                        TipoDeDano tipoDeDano, double golpeCritico) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano);
				this.golpeCritico = golpeCritico;
		}

		public double getGolpeCritico() {
				return golpeCritico;
		}

		public void setGolpeCritico(double golpeCritico) {
				this.golpeCritico = golpeCritico;
		}
}