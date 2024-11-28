package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Piedra;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class Cetro extends ArmaMagica {
		private Piedra gemaPrincipal;

		public Cetro(String nombre, String descripcion, int nivelRequerido, int poder,
		             Efecto efecto, TipoDeDano tipoDeDano, int poderMagico,
		             int regeneracionMana, Piedra gemaPrincipal) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, poderMagico, regeneracionMana);
				this.gemaPrincipal = gemaPrincipal;
		}

		public Piedra getGemaPrincipal() {
				return gemaPrincipal;
		}

		public void setGemaPrincipal(Piedra gemaPrincipal) {
				this.gemaPrincipal = gemaPrincipal;
		}

		@Override
		public String toString() {
				return String.format("%s, Gema Principal: %s", super.toString(), gemaPrincipal);
		}
}