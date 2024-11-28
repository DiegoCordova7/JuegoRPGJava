package Aplicaciones.AplicacionRPG.Habilidades;
import Utilidades.Estructuras.Lista;
import java.util.ArrayList;
import java.util.List;
/**
	* Representa el almacen de habilidades de un jugador en el juego RPG.
	* Gestiona las habilidades disponibles para el jugador, las habilidades activas para el combate y el nivel del jugador.
	*/
public class AlmacenHabilidadesJugador {
		private List<Habilidad> habilidadesDisponibles = new ArrayList<>();
		private List<Habilidad> habilidadesActivas = new ArrayList<>();
		private int nivel;
		/**
			* Constructor que inicializa el almacen con el nivel del jugador.
			*
			* @param nivel El nivel inicial del jugador.
			*/
		public AlmacenHabilidadesJugador(int nivel) {
				this.nivel = nivel;
		}
		/**
			* Agrega una habilidad al almacen de habilidades disponibles si el jugador cumple los requisitos.
			*
			* @param habilidad La habilidad que se desea agregar al almacén.
			*/
		public void agregarHabilidadAlmacen(Habilidad habilidad) {
				habilidadesDisponibles.add(habilidad);
		}
		/**
			* Muestra todas las habilidades disponibles para el jugador en formato de lista.
			*/
		public void verHabilidadesJugador() {
				Lista listaHabilidadesJugador = new Lista();
				for (Habilidad habilidad : habilidadesDisponibles) {
						listaHabilidadesJugador.agregar(habilidad.getNombre());
				}
				listaHabilidadesJugador.imprimir(true);
		}
		/**
			* Obtiene la lista de todas las habilidades disponibles del jugador.
			*
			* @return Lista de habilidades disponibles del jugador.
			*/
		public List<Habilidad> getHabilidadesDisponibles() {
				return habilidadesDisponibles;
		}
		/**
			* Actualiza el nivel del jugador y, potencialmente, las habilidades disponibles.
			*
			* @param nuevoNivel El nuevo nivel del jugador.
			*/
		public void setNivel(int nuevoNivel) {
				this.nivel = nuevoNivel;
		}
		/**
			* Obtiene el nivel actual del jugador.
			*
			* @return El nivel actual del jugador.
			*/
		public int getNivel() {
				return nivel;
		}
		/**
			* Verifica si una habilidad ya esta presente en las habilidades activas del jugador.
			*
			* @param habilidad La habilidad a verificar.
			* @return `true` si la habilidad no esta activada, `false` si ya esta activa.
			*/
		public boolean contieneHabilidad(Habilidad habilidad) {
				return !habilidadesActivas.contains(habilidad);
		}
}