package Aplicaciones.AplicacionRPG.Equipamiento.Armaduras;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Equipamiento.Armadura;

public class ArmaduraPesada extends Armadura {
		private int resistenciaExtra; // Resistencia adicional a ciertos tipos de daño

		public ArmaduraPesada(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto,
		                      int defensa, int resistenciaExtra) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, defensa);
				this.resistenciaExtra = resistenciaExtra;
		}

		public int getResistenciaExtra() {
				return resistenciaExtra;
		}

		public void setResistenciaExtra(int resistenciaExtra) {
				this.resistenciaExtra = resistenciaExtra;
		}
}
