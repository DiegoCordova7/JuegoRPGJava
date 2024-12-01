package Aplicaciones.AplicacionRPG.Equipamiento.Armas.ArmasDistancia;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class Arco extends ArmaDistancia {
		private double probabilidadDobleDisparo;
		public Arco(String nombre, String descripcion, int nivelRequerido, int poder,
		            Efecto efecto, TipoDeDano tipoDeDano, int alcance, double probabilidadDobleDisparo) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, alcance);
				this.probabilidadDobleDisparo = probabilidadDobleDisparo;
		}
		public double getProbabilidadDobleDisparo() {
				return probabilidadDobleDisparo;
		}
		public void setProbabilidadDobleDisparo(double probabilidadDobleDisparo) {
				this.probabilidadDobleDisparo = probabilidadDobleDisparo;
		}
}