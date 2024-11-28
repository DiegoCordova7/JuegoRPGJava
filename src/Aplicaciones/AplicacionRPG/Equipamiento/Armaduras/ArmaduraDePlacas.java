package Aplicaciones.AplicacionRPG.Equipamiento.Armaduras;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Equipamiento.Armadura;

public class ArmaduraDePlacas extends Armadura {
		private boolean esImpermeable;

		public ArmaduraDePlacas(String nombre, String descripcion, int nivelRequerido, int poder,
		                        Efecto efecto, int defensa, boolean esImpermeable) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, defensa);
				this.esImpermeable = esImpermeable;
		}

		public boolean isEsImpermeable() {
				return esImpermeable;
		}

		public void setEsImpermeable(boolean esImpermeable) {
				this.esImpermeable = esImpermeable;
		}
}
