package Aplicaciones.AplicacionRPG.Logica;
import Aplicaciones.AplicacionRPG.Personajes.APersonajes;
import static Aplicaciones.AplicacionRPG.Personajes.APersonajes.nombresRegistrados;
import static Utilidades.Impresora.*;
/**
	* Clase encargada de gestionar los personajes dentro del juego RPG.
	* Implementa la interfaz IGestionPersonajes para proporcionar los metodos
	* necesarios para agregar, eliminar y mostrar personajes.
	*/
public class JuegoRPG implements IGestionPersonajes {
		public APersonajes[] personajes;
		private int contador;
		/**
			* Constructor de la clase JuegoRPG.
			* Inicializa el arreglo de personajes con la capacidad proporcionada
			* y el contador en 0.
			*
			* @param capacidad La capacidad maxima de personajes que puede almacenar el juego
			*/
		public JuegoRPG(int capacidad) {
				personajes = new APersonajes[capacidad];
				contador = 0;
		}
		/**
			* Obtiene la cantidad actual de personajes en el juego.
			*
			* @return El contador de personajes agregados
			*/
		public int getContador() {
				return contador;
		}
		/**
			* Agrega un personaje al juego.
			* Si hay espacio en el arreglo de personajes, se agrega el nuevo personaje
			* y se incrementa el contador.
			*
			* @param personaje El personaje que se desea agregar al juego
			*/
		@Override
		public void agregarPersonaje(APersonajes personaje) {
				if (contador < personajes.length) {
						personajes[contador++] = personaje;
						imprimirAviso("Agregar_Personajes", "Personaje agregado exitosamente.");
				} else {
						imprimirError("Agregar_Personajes", "No se pueden agregar mas personajes.");
				}
		}
		/**
			* Elimina un personaje del juego en base a su ID.
			* Si el personaje es encontrado, se elimina y el contador se ajusta.
			*
			* @param id El ID del personaje a eliminar
			*/
		@Override
		public void eliminarPersonaje(int id) {
				for (int i = 0; i < contador; i++) {
						if (personajes[i].getId() == id) {
								nombresRegistrados.remove(personajes[i].getNombre());
								personajes[i] = personajes[--contador];
								personajes[contador] = null;
								imprimirAviso("Eliminar_Personajes", "Personaje eliminado exitosamente.");
								return;
						}
				}
				imprimir(String.format("Personaje con ID %s no encontrado.", id));
		}
		/**
			* Muestra la lista de personajes actualmente en el juego.
			* Si no hay personajes, se muestra un aviso.
			*/
		@Override
		public void mostrarPersonajes() {
				if (contador == 0) {
						imprimirAviso("Mostrar_Personajes", "No hay personajes en el juego.");
						return;
				}
				imprimirEncabezado("Personajes en el juego:");
				for (int i = 0; i < contador; i++) {
						imprimir(String.format("ID: %s, Nombre: %s", personajes[i].getId(), personajes[i].getNombre()));
				}
		}
		/**
			* Muestra las habilidades de un personaje dado su ID.
			*
			* @param id El ID del personaje cuyas habilidades se desean mostrar
			*/
		public void mostrarHabilidades(int id) {
				for (int i = 0; i < contador; i++) {
						if (personajes[i].getId() == id) {
								personajes[i].verHabilidadesConocidas();
						}
				}
		}
		/**
			* Muestra los datos detallados de un personaje, incluyendo sus habilidades activas.
			*
			* @param id El ID del personaje cuyos datos se desean mostrar
			*/
		public void mostrarDatosPersonajes(int id) {
				for (int i = 0; i < contador; i++) {
						if (personajes[i].getId() != id) {
								continue;
						}
						imprimir(personajes[i].toString());
						personajes[i].mostrarHabilidadesActivas();
				}
		}
}