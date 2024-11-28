package Aplicaciones.AplicacionRPG.Personajes;
import Aplicaciones.AplicacionRPG.Habilidades.*;
import java.util.List;
import static Utilidades.Impresora.imprimir;
import static Utilidades.Impresora.imprimirAviso;
/**
	* Clase que representa un Guerrero, un tipo de personaje con habilidades
	* y atributos especificos. Implementa la interfaz ISubirNivel para poder
	* mejorar sus atributos al subir de nivel.
	*/
public class Guerrero extends APersonajes implements ISubirNivel {
		// Constantes para los incrementos de atributos al subir de nivel.
		private static final double INCREMENTO_VIDA_POR_NIVEL = 10.0;
		private static final double INCREMENTO_FUERZA_FISICA = 5.0;
		private static final double INCREMENTO_FUERZA_MAGICA = 1.0;
		private static final double INCREMENTO_FUERZA_DISTANCIA = 2.0;
		private static final int INCREMENTO_DEFENSA_FISICA = 4;
		private static final int INCREMENTO_DEFENSA_MAGICA = 2;
		private static final int INCREMENTO_DEFENSA_DISTANCIA = 3;
		private static final double INCREMENTO_MANA = 1.0;
		private static final double INCREMENTO_VELOCIDAD = 1.0;
		/**
			* Constructor que crea un Guerrero con atributos especificos.
			*
			* @param nombre El nombre del personaje.
			* @param clase  La clase del personaje.
			* @param todasLasHabilidades Lista de todas las habilidades disponibles para el personaje.
			*/
		public Guerrero(String nombre, String clase, List<Habilidad> todasLasHabilidades) {
				super(nombre, clase, 90, 69, 6, 18, 48, 27, 21, 16, 7, 0.15, todasLasHabilidades);
				this.incrementoVida = INCREMENTO_VIDA_POR_NIVEL;
				this.incrementoFuerzaFisica = INCREMENTO_FUERZA_FISICA;
				this.incrementoFuerzaMagica = INCREMENTO_FUERZA_MAGICA;
				this.incrementoFuerzaDistancia = INCREMENTO_FUERZA_DISTANCIA;
				this.incrementoDefensaFisica = INCREMENTO_DEFENSA_FISICA;
				this.incrementoDefensaMagica = INCREMENTO_DEFENSA_MAGICA;
				this.incrementoDefensaDistancia = INCREMENTO_DEFENSA_DISTANCIA;
				this.incrementoMana = INCREMENTO_MANA;
				this.incrementoVelocidad = INCREMENTO_VELOCIDAD;
				inicializarHabilidades();
		}
		/**
			* Inicializa las habilidades del Guerrero.
			* Asigna habilidades por nivel.
			*/
		private void inicializarHabilidades() {
				asignarHabilidadesPorNivel();
		}
		/**
			* Mejora los atributos del Guerrero al subir de nivel.
			*/
		@Override
		public void mejorarAtributos() {
				super.mejorarAtributos();
				imprimirAviso("Subir_Nivel", String.format("%s ahora tiene mas fuerza.", nombre));
		}
		/**
			* Muestra los detalles del Guerrero, como sus atributos y habilidades.
			*/
		@Override
		public void mostrarDetalles() {
				imprimir(super.toString());
		}
}