package Aplicaciones.AplicacionRPG.Equipamiento.Accesorios;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Material;

public class Anillo extends Accesorio {
		private Material material;

		public Anillo(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto,
		              Material material) {
				super(nombre, descripcion, nivelRequerido, poder, efecto);
				this.material = material;
		}

		public Material getMaterial() {
				return material;
		}

		public void setMaterial(Material material) {
				this.material = material;
		}
}