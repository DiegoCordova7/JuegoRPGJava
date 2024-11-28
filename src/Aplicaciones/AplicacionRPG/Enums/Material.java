package Aplicaciones.AplicacionRPG.Enums;

import static Aplicaciones.AplicacionRPG.Enums.Efecto.NINGUNO;

/**
	* Enum que representa los diferentes materiales disponibles en el juego.
	*/
public enum Material {
		/**
			* Material de hierro.
			*/
		HIERRO,
		/**
			* Material de oro.
			*/
		ORO,
		/**
			* Material de plata.
			*/
		PLATA,
		/**
			* Material de acero.
			*/
		ACERO,
		/**
			* Material de madera.
			*/
		MADERA,
		/**
			* Material de piedra.
			*/
		PIEDRA,
		NINGUNO;

		public static Material fromString(String texto) {
				if (texto == null || texto.trim().isEmpty()) {
						return NINGUNO;
				}
				for (Material material : Material.values()) {
						if (material.name().equalsIgnoreCase(texto.trim())) {
								return Material.values()[material.ordinal()];
						}
				}
				throw new IllegalArgumentException(String.format("Efecto no reconocido: %s", texto));
		}
}
