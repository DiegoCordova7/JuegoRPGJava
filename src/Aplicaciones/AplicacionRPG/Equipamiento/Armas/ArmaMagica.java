package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Arma;

public abstract class ArmaMagica extends Arma {
		private int poderMagico;
		private int regeneracionMana;

		public ArmaMagica(String nombre, String descripcion, int nivelRequerido, int poder,
		                  Efecto efecto, TipoDeDano tipoDeDano, int poderMagico, int regeneracionMana) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano);
				this.poderMagico = poderMagico;
				this.regeneracionMana = regeneracionMana;
		}

		public int getPoderMagico() {
				return poderMagico;
		}

		public void setPoderMagico(int poderMagico) {
				this.poderMagico = poderMagico;
		}

		public int getRegeneracionMana() {
				return regeneracionMana;
		}

		public void setRegeneracionMana(int regeneracionMana) {
				this.regeneracionMana = regeneracionMana;
		}

		@Override
		public String toString() {
				return String.format("%s, Poder Magico: %s, Regeneracion Mana: %s",
								super.toString(), poderMagico, regeneracionMana);
		}
}