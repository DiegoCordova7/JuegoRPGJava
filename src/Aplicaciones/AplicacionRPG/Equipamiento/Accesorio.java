package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.Material;
import Aplicaciones.AplicacionRPG.Enums.Piedra;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.Amuleto;
import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.Anillo;

public abstract class Accesorio extends Equipamiento {

		public Accesorio(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto) {
				super(nombre, descripcion, "Accesorio", nivelRequerido, poder, efecto);

		}

		/**
			* Crea una instancia de Accesorio o sus especializaciones segun los datos proporcionados.
			* @param datos Array con los datos del accesorio.
			* @return Instancia de Accesorio o una de sus especializaciones.
			*/
		public static Accesorio crearDesdeDatos(String[] datos) {
				String tipo = datos[1];
				String nombre = datos[2];
				String descripcion = datos[3];
				int nivelRequerido = Integer.parseInt(datos[4]);
				int poder = Integer.parseInt(datos[5]);
				Efecto efecto = Efecto.valueOf(datos[6]);

				return switch (tipo) {
						case "Anillo" -> {
								Material material = Material.valueOf(datos[8]);
								yield new Anillo(nombre, descripcion, nivelRequerido, poder, efecto, material);
						}
						case "Amuleto" -> {
								Piedra piedra = Piedra.valueOf(datos[8]);
								yield new Amuleto(nombre, descripcion, nivelRequerido, poder, efecto, piedra);
						}
						default -> throw new IllegalArgumentException(String.format("Tipo de accesorio desconocido: %s", tipo));
				};
		}
}
