package Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasCuerpoCuerpo;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeCorte;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class Espada extends ArmaCuerpoCuerpo {
		private TipoDeCorte tipoCorte;
		private double chanceContraataque;

		public Espada(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, TipoDeDano tipoDeDano,
		              double golpeCritico, TipoDeCorte tipoCorte, double chanceContraataque) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, golpeCritico);
				this.tipoCorte = tipoCorte;
				this.chanceContraataque = chanceContraataque;
		}

		public TipoDeCorte getTipoCorte() {
				return tipoCorte;
		}

		public void setTipoCorte(TipoDeCorte tipoCorte) {
				this.tipoCorte = tipoCorte;
		}

		public double getChanceContraataque() {
				return chanceContraataque;
		}

		public void setChanceContraataque(double chanceContraataque) {
				this.chanceContraataque = chanceContraataque;
		}
}