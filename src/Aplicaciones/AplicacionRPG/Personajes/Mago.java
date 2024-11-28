package Aplicaciones.AplicacionRPG.Personajes;
import Aplicaciones.AplicacionRPG.Habilidades.*;
import java.util.List;
import static Utilidades.Impresora.imprimir;
import static Utilidades.Impresora.imprimirAviso;
/**
	* Clase que representa a un Mago en el juego. Hereda de APersonajes y
	* implementa la interfaz ISubirNivel.
	*/
public class Mago extends APersonajes implements ISubirNivel {
		// Constantes que definen el incremento de atributos por nivel
		private static final double INCREMENTO_VIDA_POR_NIVEL = 6.0;
		private static final double INCREMENTO_FUERZA_FISICA = 1.0;
		private static final double INCREMENTO_FUERZA_MAGICA = 8.0;
		private static final double INCREMENTO_FUERZA_DISTANCIA = 2.0;
		private static final int INCREMENTO_DEFENSA_FISICA = 1;
		private static final int INCREMENTO_DEFENSA_MAGICA = 4;
		private static final int INCREMENTO_DEFENSA_DISTANCIA = 2;
		private static final double INCREMENTO_MANA = 12.0;
		private static final double INCREMENTO_VELOCIDAD = 2.0;
		/**
			* Constructor de la clase Mago. Inicializa los atributos especificos
			* del Mago y llama al constructor de la clase base.
			*
			* @param nombre Nombre del mago.
			* @param clase Clase del mago.
			* @param todasLasHabilidades Lista de todas las habilidades disponibles para el mago.
			*/
		public Mago(String nombre, String clase, List<Habilidad> todasLasHabilidades) {
				super(nombre, clase, 66, 15, 90, 36, 18, 48, 24, 120, 21, 0.07, todasLasHabilidades);
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
			* Inicializa las habilidades del mago segun su nivel.
			*/
		private void inicializarHabilidades() {
				asignarHabilidadesPorNivel();
		}
		/**
			* Mejora los atributos del mago al subir de nivel.
			* Se incrementan los valores de mana y defensa magica.
			*/
		@Override
		public void mejorarAtributos() {
				super.mejorarAtributos();
				imprimirAviso("Subir_Nivel", String.format("%s ahora tiene mas mana y defensa magica.", nombre));
		}
		/**
			* Muestra los detalles del mago.
			*/
		@Override
		public void mostrarDetalles() {
				imprimir(super.toString());
		}
}