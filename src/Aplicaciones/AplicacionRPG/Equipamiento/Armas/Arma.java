package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Equipamiento;

public abstract class Arma extends Equipamiento {
		private TipoDeDano tipoDeDano;
		public Arma(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, TipoDeDano tipoDeDano) {
				super(nombre, descripcion, "Arma", nivelRequerido, poder, efecto);
				this.tipoDeDano = tipoDeDano;
		}

		public TipoDeDano getTipoDeDano() {
				return tipoDeDano;
		}

		public void setTipoDeDano(TipoDeDano tipoDeDano) {
				this.tipoDeDano = tipoDeDano;
		}
}
