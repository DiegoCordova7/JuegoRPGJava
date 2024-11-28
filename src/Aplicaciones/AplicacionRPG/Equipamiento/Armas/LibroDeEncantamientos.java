package Aplicaciones.AplicacionRPG.Equipamiento.Armas;

import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;

public class LibroDeEncantamientos extends ArmaMagica {
		private final int aumentoPoderHabilidad;
		private final int reduccionCostoMana;

		public LibroDeEncantamientos(String nombre, String descripcion, int nivelRequerido, int poder,
		                             Efecto efecto, TipoDeDano tipoDeDano, int poderMagico,
		                             int regeneracionMana, int aumentoPoderHabilidad, int reduccionCostoMana) {
				super(nombre, descripcion, nivelRequerido, poder, efecto, tipoDeDano, poderMagico, regeneracionMana);

				if (aumentoPoderHabilidad < 0 || reduccionCostoMana < 0) {
						throw new IllegalArgumentException("Los valores de mejora deben ser positivos.");
				}

				this.aumentoPoderHabilidad = aumentoPoderHabilidad;
				this.reduccionCostoMana = reduccionCostoMana;
		}

		public int getAumentoPoderHabilidad() {
				return aumentoPoderHabilidad;
		}

		public int getReduccionCostoMana() {
				return reduccionCostoMana;
		}

		/**
			* Aplica los efectos del libro a las habilidades magicas del personaje.
			* @param habilidad Nombre de la habilidad mágica a potenciar.
			* @return Informacion sobre el cambio aplicado.
			*/
		public String potenciarHabilidad(String habilidad) {
				return String.format("La habilidad '%s' ha sido potenciada con %s puntos de daño adicional y el costo de mana" +
								"reducido en %s puntos.", habilidad, aumentoPoderHabilidad, reduccionCostoMana);
		}
}