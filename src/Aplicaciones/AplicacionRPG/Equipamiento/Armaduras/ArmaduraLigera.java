package Aplicaciones.AplicacionRPG.Equipamiento.Armaduras;

import Aplicaciones.AplicacionRPG.Enums.Efecto;

public class ArmaduraLigera extends Armadura {
		private boolean esFlexible;

		public ArmaduraLigera(String nombre, String descripcion, int nivelRequerido, int poder,
		                      Efecto efecto, int defensa, boolean esFlexible) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, defensa);
				this.esFlexible = esFlexible;
		}

		public boolean isEsFlexible() {
				return esFlexible;
		}

		public void setEsFlexible(boolean esFlexible) {
				this.esFlexible = esFlexible;
		}
}