package Aplicaciones.AplicacionRPG.Logica;
import Aplicaciones.AplicacionRPG.Personajes.APersonajes;
/**
	* Interfaz que define los metodos para gestionar personajes en el juego RPG.
	* Esta interfaz debe ser implementada por cualquier clase que maneje la logica
	* relacionada con la gestion de los personajes, como agregar, eliminar o mostrar personajes.
	*/
interface IGestionPersonajes {
		/**
			* Agrega un personaje al sistema de gestion de personajes.
			*
			* @param personaje El personaje que se desea agregar
			*/
		void agregarPersonaje(APersonajes personaje);
		/**
			* Elimina un personaje del sistema de gestion de personajes mediante su ID.
			*
			* @param id El ID del personaje a eliminar
			*/
		void eliminarPersonaje(int id);
		/**
			* Muestra todos los personajes que actualmente estan en el sistema.
			*/
		void mostrarPersonajes();
}
