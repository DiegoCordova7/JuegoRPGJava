package Aplicaciones.AplicacionRPG.Enums;

import Aplicaciones.AplicacionRPG.Personajes.APersonajes;
import static Utilidades.Impresora.imprimir;
import static Utilidades.Impresora.imprimirAviso;
/**
	* Enum que representa los efectos aplicables a los personajes en el juego.
	*/
public enum Efecto {
		/**
			* Aumento de daño para el personaje.
			*/
		AUMENTO_DANO {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.incrementarDano(valor, turnos);
						imprimirAviso("Efecto_Aplicado", String.format("Aumento de daño en %s por %s turnos.", valor, turnos));
				}
		},
		/**
			* Curación para el personaje.
			*/
		CURACION {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.incrementarVida(valor);
						imprimirAviso("Efecto_Aplicado", String.format("Curacion de %s puntos de vida.", valor));
				}
		},
		/**
			* Congelacion del personaje, inmovilizandolo por turnos.
			*/
		CONGELACION {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.setInmovilizado(true, turnos);
						imprimirAviso("Efecto_Aplicado", String.format("Congelacion por %s turnos.", turnos));
				}
		},
		/**
			* Incremento de resistencia del personaje por turnos.
			*/
		RESISTENCIA {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.incrementarResistencia(valor, turnos);
						imprimirAviso("Efecto_Aplicado", String.format("Incremento de resistencia en %s%% por %s turnos.", valor, turnos));
				}
		},
		/**
			* Robo de vida, recuperando vida del personaje.
			*/
		ROBO_DE_ALMA {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.incrementarVida(valor);
						imprimirAviso("Efecto_Aplicado", String.format("Recupera %s puntos de vida.", valor));
				}
		},
		/**
			* Veneno, causando daño a lo largo de varios turnos.
			*/
		VENENO {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.aplicarVeneno(valor, turnos);
						imprimirAviso("Efecto_Aplicado", String.format("Envenenado, causa %s de daño por %s turnos.", valor, turnos));
				}
		},
		/**
			* Veneno, causando daño a lo largo de varios turnos.
			*/
		QUEMAR {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.aplicarQuemadura(valor, turnos);
						imprimirAviso("Efecto_Aplicado", String.format("Quemar, causa %s de daño por %s turnos.", valor, turnos));
				}
		},
		/**
			* Regeneracion de mana para el personaje.
			*/
		REGENERA_MANA {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						objetivo.incrementarMana(valor);
						imprimirAviso("Efecto_Aplicado", String.format("Regeneracion de %s puntos de mana", valor));
				}
		},
		/**
			* Ningun efecto aplicado.
			*/
		NINGUNO {
				@Override
				public void aplicar(APersonajes objetivo, int valor, int turnos) {
						imprimir("Sin efecto aplicado.");
				}
		};
		/**
			* Metodo que obtiene un efecto a partir de una cadena de texto.
			*
			* @param texto El texto a convertir a efecto.
			* @return El efecto correspondiente.
			*/
		public static Efecto fromString(String texto) {
				if (texto == null || texto.trim().isEmpty()) {
						return NINGUNO;
				}
				for (Efecto efecto : Efecto.values()) {
						if (efecto.name().equalsIgnoreCase(texto.trim())) {
								return efecto;
						}
				}
				throw new IllegalArgumentException(String.format("Efecto no reconocido: %s", texto));
		}
		/**
			* Metodo abstracto que define la logica para aplicar un efecto a un personaje.
			*
			* @param objetivo El personaje al que se aplica el efecto.
			* @param valor    El valor asociado al efecto (ej. daño, curacion, porcentaje).
			* @param turnos   La duración del efecto en turnos.
			*/
		public abstract void aplicar(APersonajes objetivo, int valor, int turnos);
}