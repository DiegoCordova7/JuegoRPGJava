package Aplicaciones.AplicacionRPG.Enums;

import java.util.Arrays;

/**
	* Enum que representa los diferentes materiales disponibles en el juego.
	*/
public enum Clase {
GUERRERO, MAGO, ARQUERO;

		public static Clase fromString(String texto) {
				for (Clase clase : Clase.values()) {
						if (clase.name().equalsIgnoreCase(texto.trim())) {
								return Clase.values()[clase.ordinal()];
						}
				}
				throw new IllegalArgumentException(String.format("Clase no reconocido: %s", texto));
		}

		public static String[] clases() {
				Clase[] clases = Clase.values();
				String[] claseStrings = new String[clases.length];
				for (int i = 0; i < clases.length; i++) {
						claseStrings[i] = hacerMayuscula(clases[i].name());
				}
				return claseStrings;
		}

		public static String hacerMayuscula(String texto) {
				texto = texto.toLowerCase();
				String finalTexto = texto;
				return texto.chars()
								.mapToObj(c -> (char) c)
								.findFirst()
								.map(Character::toUpperCase)
								.map(primera -> primera + finalTexto.substring(1))
								.orElse(texto);
		}
}
