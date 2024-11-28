package Aplicaciones.AplicacionRPG.Enums;
/**
	* Enum que representa las diferentes piedras disponibles en el juego.
	*/
public enum Piedra {
		/**
			* Piedra esmeralda.
			*/
		ESMERALDA,
		/**
			* Piedra rubi.
			*/
		RUBI,
		/**
			* Piedra zafiro.
			*/
		ZAFIRO,
		/**
			* Piedra amatista.
			*/
		AMATISTA,
		/**
			* Piedra topacio.
			*/
		TOPACIO,
		NINGUNO;

		public static Piedra fromString(String texto) {
				if (texto == null || texto.trim().isEmpty()) {
						return NINGUNO;
				}
				for (Piedra piedra : Piedra.values()) {
						if (piedra.name().equalsIgnoreCase(texto.trim())) {
								return piedra;
						}
				}
				throw new IllegalArgumentException(String.format("Efecto no reconocido: %s", texto));
		}
}