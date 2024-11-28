package Aplicaciones.AplicacionRPG.Habilidades;
import Aplicaciones.AplicacionRPG.Enums.Efecto;
import Aplicaciones.AplicacionRPG.Enums.TipoDeDano;
import java.util.List;
/**
	* Representa una habilidad en el juego RPG. Cada habilidad tiene propiedades como el nombre, la descripcion,
	* el nivel requerido para aprenderla, el tipo de daño que causa, los efectos que tiene, entre otros.
	*/
public class Habilidad {
		private String nombre;
		private String descripcion;
		private int nivelRequerido;
		private boolean esOfensiva;
		private int dano;
		private int usoMaximo;
		private String tipoHabilidad;
		private TipoDeDano tipoDano;
		private Efecto efecto;
		private int turnosDeEfecto;
		private List<String> clasesPermitidas;
		/**
			* Constructor por defecto de la clase Habilidad. Inicializa los turnos de efecto a 0.
			*/
		public Habilidad() {
				this.turnosDeEfecto = 0;
		}
		/**
			* Constructor que inicializa todos los atributos de la habilidad.
			*
			* @param nombre             El nombre de la habilidad.
			* @param descripcion        La descripción de la habilidad.
			* @param nivelRequerido     El nivel requerido para aprender la habilidad.
			* @param esOfensiva         Indica si la habilidad es ofensiva.
			* @param dano               El daño que causa la habilidad.
			* @param usoMaximo          El numero maximo de usos de la habilidad.
			* @param tipoHabilidad      El tipo de la habilidad.
			* @param tipoDano           El tipo de daño que causa la habilidad.
			* @param efecto             El efecto de la habilidad.
			* @param turnosDeEfecto     Los turnos durante los cuales el efecto de la habilidad esta activo.
			* @param clasesPermitidas   Las clases de personajes que pueden aprender la habilidad.
			*/
		public Habilidad(String nombre, String descripcion, int nivelRequerido, boolean esOfensiva,
		                 int dano, int usoMaximo, String tipoHabilidad, TipoDeDano tipoDano, Efecto efecto,
		                 int turnosDeEfecto, List<String> clasesPermitidas) {
				this.nombre = nombre;
				this.descripcion = descripcion;
				this.nivelRequerido = nivelRequerido;
				this.esOfensiva = esOfensiva;
				this.dano = dano;
				this.usoMaximo = usoMaximo;
				this.tipoHabilidad = tipoHabilidad;
				this.tipoDano = tipoDano;
				this.efecto = efecto;
				this.turnosDeEfecto = turnosDeEfecto;
				this.clasesPermitidas = clasesPermitidas;
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

		public int getNivelRequerido() {
				return nivelRequerido;
		}

		public void setNivelRequerido(int nivelRequerido) {
				this.nivelRequerido = nivelRequerido;
		}

		public boolean esOfensiva() {
				return esOfensiva;
		}

		public void setEsOfensiva(boolean esOfensiva) {
				this.esOfensiva = esOfensiva;
		}

		public int getDano() {
				return dano;
		}

		public void setDano(int dano) {
				this.dano = dano;
		}

		public void setTipoDano(TipoDeDano tipoDano) {
				this.tipoDano = tipoDano;
		}

		public TipoDeDano getTipoDano() {
				return tipoDano;
		}

		public int getUsoMaximo() {
				return usoMaximo;
		}

		public void setUsoMaximo(int usoMaximo) {
				this.usoMaximo = usoMaximo;
		}

		public String getTipoHabilidad() {
				return tipoHabilidad;
		}

		public void setTipoHabilidad(String tipoHabilidad) {
				this.tipoHabilidad = tipoHabilidad;
		}

		public Efecto getEfecto() {
				return efecto;
		}

		public void setEfecto(Efecto efecto) {
				this.efecto = efecto;
		}

		public int getTurnosDeEfecto() {
				return turnosDeEfecto;
		}

		public void setTurnosDeEfecto(int turnosDeEfecto) {
				this.turnosDeEfecto = turnosDeEfecto;
		}

		public void setClasesPermitidas(List<String> clasesPermitidas) {
				this.clasesPermitidas = clasesPermitidas;
		}
		/**
			* Determina si un personaje puede aprender esta habilidad segun su nivel y clase.
			*
			* @param nivel El nivel del personaje.
			* @param clase La clase del personaje.
			* @return `true` si el personaje puede aprender la habilidad, `false` en caso contrario.
			*/
		public boolean sePuedeAprender(int nivel, String clase) {
				return nivel == this.nivelRequerido && clasesPermitidas.contains(clase);
		}
/*
*Estos metodos seran desarrollados despues.
* */
		public void usarHabilidad() {
		}
		public void setPorcentajeAcierto(int i) {
		}

		@Override
		public String toString() {
				return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
								nombre, descripcion, nivelRequerido, esOfensiva, dano, usoMaximo,
								tipoHabilidad, tipoDano, efecto, turnosDeEfecto, clasesPermitidas);
		}
}