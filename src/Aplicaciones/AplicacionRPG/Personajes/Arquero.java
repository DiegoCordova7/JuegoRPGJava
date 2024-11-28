package Aplicaciones.AplicacionRPG.Personajes;
import Aplicaciones.AplicacionRPG.Habilidades.*;
import java.util.List;
import static Utilidades.Impresora.imprimir;
import static Utilidades.Impresora.imprimirAviso;
/**
	* Clase que representa a un Arquero, un tipo de personaje especializado en
	* ataques a distancia. Implementa la interfaz ISubirNivel para poder mejorar
	* sus atributos al subir de nivel.
	*/
public class Arquero extends APersonajes implements ISubirNivel {
		private int precision;
		// Constantes para los incrementos de atributos al subir de nivel.
		private static final double INCREMENTO_VIDA_POR_NIVEL = 7.0;
		private static final double INCREMENTO_FUERZA_FISICA = 3.0;
		private static final double INCREMENTO_FUERZA_MAGICA = 2.0;
		private static final double INCREMENTO_FUERZA_DISTANCIA = 6.0;
		private static final int INCREMENTO_DEFENSA_FISICA = 2;
		private static final int INCREMENTO_DEFENSA_MAGICA = 2;
		private static final int INCREMENTO_DEFENSA_DISTANCIA = 4;
		private static final double INCREMENTO_MANA = 3.0;
		private static final double INCREMENTO_VELOCIDAD = 4.0;
		/**
			* Constructor que crea un Arquero con atributos especificos.
			*
			* @param nombre El nombre del personaje.
			* @param clase La clase del personaje.
			* @param todasLasHabilidades Lista de todas las habilidades disponibles para el personaje.
			*/
		public Arquero(String nombre, String clase, List<Habilidad> todasLasHabilidades) {
				super(nombre, clase, 54, 30, 24, 78, 27, 18, 42, 57, 27, 0.09, todasLasHabilidades);
				this.incrementoVida = INCREMENTO_VIDA_POR_NIVEL;
				this.incrementoFuerzaFisica = INCREMENTO_FUERZA_FISICA;
				this.incrementoFuerzaMagica = INCREMENTO_FUERZA_MAGICA;
				this.incrementoFuerzaDistancia = INCREMENTO_FUERZA_DISTANCIA;
				this.incrementoDefensaFisica = INCREMENTO_DEFENSA_FISICA;
				this.incrementoDefensaMagica = INCREMENTO_DEFENSA_MAGICA;
				this.incrementoDefensaDistancia = INCREMENTO_DEFENSA_DISTANCIA;
				this.incrementoMana = INCREMENTO_MANA;
				this.incrementoVelocidad = INCREMENTO_VELOCIDAD;
				this.precision = 10;
				this.almacenHabilidades = new AlmacenHabilidadesJugador(getNivel());
				inicializarHabilidades();
		}
		/**
			* Inicializa las habilidades del Arquero.
			* Asigna habilidades por nivel.
			*/
		private void inicializarHabilidades() {
				asignarHabilidadesPorNivel();
		}
		/**
			* Mejora los atributos del Arquero al subir de nivel.
			* En este caso, se incrementa la precision.
			*/
		@Override
		public void mejorarAtributos() {
				super.mejorarAtributos();
				this.precision += 2;
				imprimirAviso("Subir_Nivel", String.format("%s ahora tiene mayor precision.", nombre));
		}
		/**
			* Muestra los detalles del Arquero, como sus atributos y habilidades.
			* Tambien incluye la precision actual.
			*/
		@Override
		public void mostrarDetalles() {
				imprimir(String.format("%sPresicion: %s", super.toString(), precision));
		}
}