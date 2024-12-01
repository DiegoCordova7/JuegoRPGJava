package Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasDistancia;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class Ballesta extends ArmaDistancia {
		private int penetracionArmadura;

		public Ballesta(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto,
		                TipoDeDano tipoDeDano, int alcance, int penetracionArmadura) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, alcance);
				this.penetracionArmadura = penetracionArmadura;
		}

		public void setPenetracionArmadura(int penetracionArmadura) {
				this.penetracionArmadura = penetracionArmadura;
		}

		public int getPenetracionArmadura() {
				return penetracionArmadura;
		}
}
