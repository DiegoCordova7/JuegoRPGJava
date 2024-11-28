package Aplicaciones.AplicacionRPG.Enums;
/**
	* Enum que representa los diferentes tipos de corte disponibles en el juego.
	*/
public enum TipoDeCorte {
		/**
			* Corte recto.
			*/
		RECTO,
		/**
			* Corte curvo.
			*/
		CURVO,
		/**
			* Corte encebado.
			*/
		ENCEBADO,
		/**
			* Corte obtuso.
			*/
		OBTUSO,
		NINGUNO;
		public static TipoDeCorte fromString(String texto) {
				if (texto == null || texto.trim().isEmpty()) {
						return NINGUNO;
				}
				for (TipoDeCorte tipoDeCorte : TipoDeCorte.values()) {
						if (tipoDeCorte.name().equalsIgnoreCase(texto.trim())) {
								return tipoDeCorte;
						}
				}
				throw new IllegalArgumentException(String.format("Tipo De Corte no reconocido: %s", texto));
		}
}