package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeCorte;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Arma;

public class Espada extends Arma {
		private TipoDeCorte tipoCorte;

		public Espada(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto,
		              TipoDeDano tipoDeDano, TipoDeCorte tipoCorte) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano);
				this.tipoCorte = tipoCorte;
		}

		public TipoDeCorte getTipoCorte() {
				return tipoCorte;
		}

		public void setTipoCorte(TipoDeCorte tipoCorte) {
				this.tipoCorte = tipoCorte;
		}
}

