package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.ArmaduraDePlacas;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.ArmaduraLigera;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.ArmaduraPesada;

public abstract class Armadura extends Equipamiento {
		private int defensa;

		public Armadura(String nombre, String descripcion, int nivelRequerido, int poder, Efecto efecto, int defensa) {
				super(nombre, descripcion, "Armadura", nivelRequerido, poder, efecto);
				this.defensa = defensa;
		}

		public int getDefensa() {
				return defensa;
		}
		/**
			* Crea una instancia de Armadura o sus especializaciones segun los datos proporcionados.
			* @param datos Array con los datos del equipamiento.
			* @return Instancia de Armadura o una de sus especializaciones.
			*/
		public static Armadura crearDesdeDatos(String[] datos) {
				String tipo = datos[1];
				String nombre = datos[2];
				String descripcion = datos[3];
				int nivelRequerido = Integer.parseInt(datos[4]);
				int poder = Integer.parseInt(datos[5]);
				Efecto efecto = Efecto.valueOf(datos[6]);
				int defensa = Integer.parseInt(datos[7]);

				return switch (tipo) {
						case "Ligera" -> {
								boolean esFlexible = Boolean.parseBoolean(datos[8]);
								yield new ArmaduraLigera(nombre, descripcion, nivelRequerido, poder, efecto, defensa, esFlexible);
						}
						case "Placas" -> {
								boolean esImpermeable = Boolean.parseBoolean(datos[8]);
								yield new ArmaduraDePlacas(nombre, descripcion, nivelRequerido, poder, efecto, defensa, esImpermeable);
						}
						case "Pesada" -> {
								int resistenciaExtra = Integer.parseInt(datos[8]);
								yield new ArmaduraPesada(nombre, descripcion, nivelRequerido, poder, efecto, defensa, resistenciaExtra);
						}
						default -> throw new IllegalArgumentException(String.format("Tipo de armadura desconocido: %s", tipo));
				};
		}

		public void setDefensa(int defensa) {
				this.defensa = defensa;
		}
}