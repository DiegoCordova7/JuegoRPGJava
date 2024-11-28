package Aplicaciones.AplicacionRPG.Equipamiento;

import Aplicaciones.AplicacionRPG.Enums.Efecto;

public class Equipamiento {
		private String nombre;
		private String descripcion;
		private String tipo;
		private int nivelRequerido;
		private int poder;
		private Efecto efecto;

		public Equipamiento(String nombre, String descripcion, String tipo, int nivelRequerido, int poder, Efecto efecto) {
				this.nombre = nombre;
				this.descripcion = descripcion;
				this.tipo = tipo;
				this.nivelRequerido = nivelRequerido;
				this.poder = poder;
				this.efecto = efecto;
		}

		public String getNombre() {
				return nombre;
		}

		public void setNombre(String nombre) {
				this.nombre = nombre;
		}

		public String getDescripcion() {
				return descripcion;
		}

		public void setDescripcion(String descripcion) {
				this.descripcion = descripcion;
		}

		public String getTipo() {
				return tipo;
		}

		public void setTipo(String tipo) {
				this.tipo = tipo;
		}

		public int getNivelRequerido() {
				return nivelRequerido;
		}

		public void setNivelRequerido(int nivelRequerido) {
				this.nivelRequerido = nivelRequerido;
		}

		public int getPoder() {
				return poder;
		}

		public void setPoder(int poder) {
				this.poder = poder;
		}

		public Efecto getEfecto() {
				return efecto;
		}

		public void setEfecto(Efecto efecto) {
				this.efecto = efecto;
		}
}
