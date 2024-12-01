package Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasCuerpoCuerpo;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Material;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class Hacha extends ArmaCuerpoCuerpo {
		private Material tipoMadera;

		public Hacha(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, TipoDeDano tipoDeDano,
		             double golpeCritico, Material tipoMadera) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, golpeCritico);
				this.tipoMadera = tipoMadera;
		}

		public Material getTipoMadera() {
				return tipoMadera;
		}

		public void setTipoMadera(Material tipoMadera) {
				this.tipoMadera = tipoMadera;
		}
}