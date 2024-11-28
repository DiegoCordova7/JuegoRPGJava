package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Material;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Arma;

public class Hacha extends Arma {
		private Material tipoMadera;

		public Hacha(String nombre, String descripcion, int nivelRequerido, int poder,
		             Efecto efecto, TipoDeDano tipoDeDano, Material tipoMadera) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano);
				this.tipoMadera = tipoMadera;
		}

		public Material getTipoMadera() {
				return tipoMadera;
		}

		public void setTipoMadera(Material tipoMadera) {
				this.tipoMadera = tipoMadera;
		}
}