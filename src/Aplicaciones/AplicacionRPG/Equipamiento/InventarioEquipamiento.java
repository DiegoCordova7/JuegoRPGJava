package Aplicaciones.AplicacionRPG.Equipamiento;

import Utilidades.Estructuras.Lista;

import java.util.*;

public class InventarioEquipamiento {
		private List<Equipamiento> equipamientos;

		public InventarioEquipamiento() {
				this.equipamientos = new ArrayList<>();
		}

		public void agregarEquipamiento(Equipamiento equipamiento) {
				equipamientos.add(equipamiento);
		}

		public Equipamiento obtenerEquipamiento(String nombre) {
				for (Equipamiento e : equipamientos) {
						if (e.getNombre().equalsIgnoreCase(nombre)) {
								return e;
						}
				}
				return null;
		}

		public boolean puedeEquipar(String nombre, int nivelJugador) {
				Equipamiento equipamiento = obtenerEquipamiento(nombre);
				return equipamiento != null && equipamiento.getNivelRequerido() <= nivelJugador;
		}

		public void mostrarEquipamientos() {
				for (Equipamiento equipamiento : equipamientos) {
						Lista mostrarEquipamiento = new Lista();
						mostrarEquipamiento.agregar(String.format("Nombre: %s", equipamiento.getNombre()));
						mostrarEquipamiento.agregar(String.format("Descripcion: %s", equipamiento.getDescripcion()));
						mostrarEquipamiento.agregar(String.format("Tipo: %s", equipamiento.getTipo()));
						mostrarEquipamiento.agregar(String.format("Nivel Requerido: %s", equipamiento.getNivelRequerido()));
						mostrarEquipamiento.agregar(String.format("Poder: %s", equipamiento.getPoder()));
						mostrarEquipamiento.agregar(String.format("Efecto: %s", equipamiento.getEfecto()));
						mostrarEquipamiento.imprimir(false);
				}
		}
}