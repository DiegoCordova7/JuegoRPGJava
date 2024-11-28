package Aplicaciones.AplicacionRPG.Enums;
/**
	* Enum que representa los diferentes tipos de daño en el juego.
	*/
public enum TipoDeDano {
		/**
			* Daño fisico.
			*/
		FISICO,
		/**
			* Daño magico.
			*/
		MAGICO,
		/**
			* Daño elemental.
			*/
		ELEMENTAL,
		/**
			* Daño psiquico.
			*/
		PSIQUICO,
		/**
			* Daño por veneno.
			*/
		VENENO;
		/**
			* Metodo que obtiene un tipo de daño a partir de una cadena de texto.
			*
			* @param texto El texto que representa el tipo de daño.
			* @return El tipo de daño correspondiente.
			* @throws IllegalArgumentException Si el texto no coincide con ningun tipo de daño.
			*/
		public static TipoDeDano fromString(String texto) {
				for (TipoDeDano tipoDeDano : TipoDeDano.values()) {
						if (tipoDeDano.name().equalsIgnoreCase(texto.trim())) {
								return tipoDeDano;
						}
				}
				throw new IllegalArgumentException(String.format("Valor no valido para TipoDeDano: %s", texto));
		}
}