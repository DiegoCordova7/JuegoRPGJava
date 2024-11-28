package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Material;
import Aplicaciones.AplicacionRPG.Enums.TipoDeCorte;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Arco;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Espada;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Hacha;

public abstract class Arma extends Equipamiento {
		private TipoDeDano tipoDeDano;
		public Arma(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, TipoDeDano tipoDeDano) {
				super(nombre, descripcion, "Espada", nivelRequerido, poder, efecto);
				this.tipoDeDano = tipoDeDano;
		}

		public TipoDeDano getTipoDeDano() {
				return tipoDeDano;
		}

		/**
			* Crea una instancia de Arma o sus especializaciones segun los datos proporcionados.
			* @param datos Array con los datos del arma.
			* @return Instancia de Arma o una de sus especializaciones.
			*/
		public static Arma crearDesdeDatos(String[] datos) {
				String tipo = datos[1];
				String nombre = datos[2];
				String descripcion = datos[3];
				int nivelRequerido = Integer.parseInt(datos[4]);
				int poder = Integer.parseInt(datos[5]);
				Efecto efecto = Efecto.valueOf(datos[6]);
				return switch (tipo) {
						case "Espada" -> {
								TipoDeCorte tipoDeCorte = TipoDeCorte.valueOf(datos[7]);
								yield new Espada(nombre, descripcion, nivelRequerido, poder, efecto, TipoDeDano.FISICO, tipoDeCorte);
						}
						case "Arco" -> {
								int alcance = Integer.parseInt(datos[7]);
								yield new Arco(nombre, descripcion, nivelRequerido, poder, efecto, TipoDeDano.FISICO, alcance);
						}
						case "Hacha" -> {
								Material material = Material.valueOf(datos[7]);
								yield new Hacha(nombre, descripcion, nivelRequerido, poder, efecto, TipoDeDano.FISICO, material);
						}
						default -> throw new IllegalArgumentException(String.format("Tipo de arma desconocido: %s", tipo));
				};
		}

		public void setTipoDeDano(TipoDeDano tipoDeDano) {
				this.tipoDeDano = tipoDeDano;
		}
}
