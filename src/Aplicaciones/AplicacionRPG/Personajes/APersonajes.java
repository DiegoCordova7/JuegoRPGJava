package Aplicaciones.AplicacionRPG.Personajes;

import Aplicaciones.AplicacionRPG.Equipamiento.Accesorios.Accesorio;
import Aplicaciones.AplicacionRPG.Equipamiento.AlmacenEquipamientos;
import Aplicaciones.AplicacionRPG.Equipamiento.Armas.Arma;
import Aplicaciones.AplicacionRPG.Equipamiento.Armaduras.Armadura;
import Aplicaciones.AplicacionRPG.Habilidades.AlmacenHabilidadesJugador;
import Aplicaciones.AplicacionRPG.Habilidades.Habilidad;
import Utilidades.Estructuras.Lista;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static Utilidades.Impresiones.ImpresionAviso.imprimirAviso;
import static Utilidades.Impresora.*;

public abstract class APersonajes {
		//Niveles
		private final int NIVEL_MAXIMO = 100;
		private final int MULTIPLICADOR_EXPERIENCIA = 50;
		protected int nivel;
		private int experiencia;
		private int experienciaProximoNivel;

		//Datos Personaje
		private static int ultimoId = 0;
		protected int id;
		protected String nombre;
		public static final Set<String> nombresRegistrados = new HashSet<>();
		protected String claseJugador;

		//stats
		protected double vida;
		protected double fuerzaFisica;
		protected double fuerzaMagica;
		protected double fuerzaDistancia;
		protected int defensaFisica;
		protected int defensaMagica;
		protected int defensaDistancia;
		protected double mana;
		protected double velocidad;
		protected double reduccionDano;

		//RD
		private static final double VALOR_INICIAL_REDUCCION_DANO_GUERRERO = 0.15;
		private static final double VALOR_INICIAL_REDUCCION_DANO_MAGO = 0.07;
		private static final double VALOR_INICIAL_REDUCCION_DANO_ARQUERO = 0.09;
		private static final double INCREMENTO_REDUCCION_DANO_GUERRERO = 0.003;
		private static final double INCREMENTO_REDUCCION_DANO_MAGO = 0.0018;
		private static final double INCREMENTO_REDUCCION_DANO_ARQUERO = 0.0021;
		private static final double LIMITE_REDUCCION_DANO_GUERRERO = 0.45;
		private static final double LIMITE_REDUCCION_DANO_MAGO = 0.25;
		private static final double LIMITE_REDUCCION_DANO_ARQUERO = 0.30;

		//Incrementos al subir de nivel
		protected double incrementoVida;
		protected double incrementoFuerzaFisica;
		protected double incrementoFuerzaMagica;
		protected double incrementoFuerzaDistancia;
		protected double incrementoDefensaFisica;
		protected double incrementoDefensaMagica;
		protected double incrementoDefensaDistancia;
		protected double incrementoMana;
		protected double incrementoVelocidad;

		//Habilidades
		private Habilidad[] habilidadesActivas;
		private int habilidadesMaximas;
		private List<Habilidad> todasLasHabilidades;
		protected AlmacenHabilidadesJugador almacenHabilidades;

		//Equipamiento
		protected Arma armaEquipada;
		protected Armadura armaduraEquipada;
		protected List<Accesorio> accesoriosEquipados;
		protected AlmacenEquipamientos almacenEquipamientos;

		public APersonajes(String nombre, String claseJugador, double vidaBase, double fuerzaFisicaBase,
		                   double fuerzaMagicaBase, double fuerzaDistanciaBase, int defensaFisicaBase,
		                   int defensaMagicaBase, int defensaDistanciaBase, double manaBase, double velocidadBase,
		                   double reduccionDanoBase, List<Habilidad> todasLasHabilidades) {
				if (!esNombreValido(nombre)) {
						throw new IllegalArgumentException(String.format("El nombre %s ya esta en uso.", nombre));
				}
				this.id = generarId();
				if (nombre == null || nombre.trim().isEmpty()) {
						throw new IllegalArgumentException("El nombre no puede ser nulo o vacio.");
				}
				this.nombre = nombre;
				nombresRegistrados.add(nombre);
				this.claseJugador = claseJugador;
				this.nivel = 1;
				this.experiencia = 0;
				this.experienciaProximoNivel = 100;
				this.vida = vidaBase;
				this.fuerzaFisica = fuerzaFisicaBase;
				this.fuerzaMagica = fuerzaMagicaBase;
				this.fuerzaDistancia = fuerzaDistanciaBase;
				this.defensaFisica = defensaFisicaBase;
				this.defensaMagica = defensaMagicaBase;
				this.defensaDistancia = defensaDistanciaBase;
				this.mana = manaBase;
				this.velocidad = velocidadBase;
				this.reduccionDano = reduccionDanoBase;
				this.habilidadesMaximas = 4;
				this.habilidadesActivas = new Habilidad[habilidadesMaximas];
				this.todasLasHabilidades = todasLasHabilidades;
				this.armaEquipada = null;
				this.armaduraEquipada = null;
				this.accesoriosEquipados = new ArrayList<>(4);
				imprimirAviso("Crear_Personaje", String.format("Personaje %s de clase %s creado con exito.", nombre, claseJugador));
		}

		public static int generarId() {
				return ++ultimoId;
		}

		public Habilidad[] getHabilidadesActivas() {
				return habilidadesActivas;
		}

		public void reemplazarHabilidad(int numeroHabilidad, Habilidad nuevaHabilidad) {
				if (numeroHabilidad < 0 || numeroHabilidad >= habilidadesActivas.length) {
						imprimirError("Reemplazar_Habilidad" ,"Indice de habilidad no valido.");
						return;
				}
				for (Habilidad habilidadActiva : habilidadesActivas) {
						if (habilidadActiva == nuevaHabilidad) {
								imprimirAviso("Reemplazar_Habilidad", String.format("La %s ya esta activa." , habilidadActiva.getNombre()));
								return;
						}
				}
				String habilidad = (habilidadesActivas[numeroHabilidad] != null) ?
								habilidadesActivas[numeroHabilidad].getNombre() : "N/A";
				imprimir(String.format("Habilidad %s reemplazada por %s.",
								habilidad, nuevaHabilidad.getNombre()));
				habilidadesActivas[numeroHabilidad] = nuevaHabilidad;
		}

		public abstract void mostrarDetalles();

		public int getId() {
				return id;
		}

		public String getNombre() {
				return nombre;
		}

		public void setNombre(String nombre) {
				this.nombre = nombre;
		}

		public int getNivel() {
				return nivel;
		}

		public double getVida() {
				return vida;
		}

		public double getFuerzaFisica() {
				return fuerzaFisica;
		}

		public double getFuerzaMagica() {
				return fuerzaMagica;
		}

		public double getFuerzaDistancia() {
				return fuerzaDistancia;
		}

		public int getDefensaDistancia() {
				return defensaDistancia;
		}

		public int getDefensaFisica() {
				return defensaFisica;
		}

		public int getDefensaMagica() {
				return defensaMagica;
		}

		public double getMana() {
				return mana;
		}

		public void agregarXP(int xp) {
				if (this.nivel == NIVEL_MAXIMO) {
						imprimirAviso("Agregar_XP", String.format("%s alcanzo el nivel maximo.", this.nombre));
						this.experiencia = 0;
						this.experienciaProximoNivel = 0;
						return;
				}
				imprimirAviso("Agregar_XP", String.format("Agregando %d puntos de experiencia a %s.", xp, this.nombre));
				this.experiencia += xp;
				while (this.experiencia >= this.experienciaProximoNivel) {
						this.experiencia -= this.experienciaProximoNivel;
						subirNivel();
				}
		}

		public void subirNivel() {
				this.nivel++;
				this.experienciaProximoNivel = MULTIPLICADOR_EXPERIENCIA * this.nivel;
				imprimirAviso("Subir_Nivel", String.format("¡%s ha subido al nivel %d!", this.nombre, this.nivel));
				mejorarAtributos();
				asignarHabilidadesPorNivel();
		}

		public void asignarHabilidadesPorNivel() {
				if (almacenHabilidades == null) {
						almacenHabilidades = new AlmacenHabilidadesJugador(getNivel());
				}
				for (Habilidad habilidad : todasLasHabilidades) {
						if (!habilidad.sePuedeAprender(getNivel(), getClaseJugador())) {
								continue;
						}
						if (!almacenHabilidades.contieneHabilidad(habilidad)) {
								continue;
						}
						almacenHabilidades.agregarHabilidadAlmacen(habilidad);
						if (contadorHablidadesActivas() <= habilidadesMaximas) {
								agregarHabilidadActiva(habilidad);
								return;
						}
				}
		}

		public int contadorHablidadesActivas() {
				int contadorHabilidades = 0;
				for (Habilidad habilidadActiva : habilidadesActivas) {
						if (habilidadActiva != null) {
								contadorHabilidades++;
						}
				}
				return contadorHabilidades;
		}

		public void agregarHabilidadActiva(Habilidad habilidad) {
				int contadorHabilidades = 0;
				for (Habilidad habilidadActiva : habilidadesActivas) {
						if (habilidadActiva != null) {
								contadorHabilidades++;
						}
				}

				if (contadorHabilidades > habilidadesMaximas) {
						imprimirAviso("Agregar_Habilidad", "No se pueden agregar mas habilidades activas.");
						return;
				}

				for (int i = 0; i < habilidadesActivas.length; i++) {
						if (habilidadesActivas[i] == null) {
								habilidadesActivas[i] = habilidad;
								imprimirAviso("Agregar_Habilidad", String.format("Habilidad %s agregada al personaje", habilidad.getNombre()));
								return;
						}
				}
		}

		public void usarHabilidad(int indice) {
				if (indice >= 0 && indice < habilidadesActivas.length) {
						habilidadesActivas[indice].usarHabilidad();
				} else {
						imprimirAviso("Usar_Habilidad", "Habilidad no disponible.");
				}
		}

		public String toString() {
				return String.format(
								"""
								Detalles Del Jugador
								Id: %s
								Nombre: %s
								Clase: %s
								Nivel: %d
								Experiencia: %d/%d
								Vida: %.2f
								Mana: %.2f
								Fuerza Fisica: %.2f
								Fuerza Magica: %.2f
								Fuerza Distancia: %.2f
								Defensa Fisica: %d
								Defensa Magica: %d
								Defensa Distancia: %d
								Velocidad: %.2f
								Reduccion de Daño: %.2f%%
								Arma Equipada: %s
								Armadura Equipada: %s
								Accesorio Equipados:
								%s
								""", id, nombre, claseJugador, nivel, experiencia, experienciaProximoNivel, vida, mana,
								fuerzaFisica, fuerzaMagica, fuerzaDistancia, defensaFisica, defensaMagica, defensaDistancia,
								velocidad, reduccionDano * 100, armaEquipada != null ? armaEquipada.getNombre() : "Ninguna",
								armaduraEquipada != null ? armaduraEquipada.getNombre() : "Ninguna", mostrarAccesorios()
				);
		}

		public void mejorarAtributos() {
				this.vida += incrementoVida;
				this.fuerzaFisica += incrementoFuerzaFisica;
				this.fuerzaMagica += incrementoFuerzaMagica;
				this.fuerzaDistancia += incrementoFuerzaDistancia;
				this.defensaFisica += (int) incrementoDefensaFisica;
				this.defensaMagica += (int) incrementoDefensaMagica;
				this.defensaDistancia += (int) incrementoDefensaDistancia;
				this.mana += incrementoMana;
				this.velocidad += incrementoVelocidad;

				switch (this) {
						case Guerrero _ ->
										this.reduccionDano = Math.min(VALOR_INICIAL_REDUCCION_DANO_GUERRERO +
														((this.nivel - 1) * INCREMENTO_REDUCCION_DANO_GUERRERO), LIMITE_REDUCCION_DANO_GUERRERO);
						case Mago _ ->
										this.reduccionDano = Math.min(VALOR_INICIAL_REDUCCION_DANO_MAGO +
														((this.nivel - 1) * INCREMENTO_REDUCCION_DANO_MAGO), LIMITE_REDUCCION_DANO_MAGO);
						case Arquero _ ->
										this.reduccionDano = Math.min(VALOR_INICIAL_REDUCCION_DANO_ARQUERO +
														((this.nivel - 1) * INCREMENTO_REDUCCION_DANO_ARQUERO), LIMITE_REDUCCION_DANO_ARQUERO);
						default -> throw new IllegalStateException(String.format("Necesita valor: %s", this));
				}
		}
		/**
			Muestra las
			*/
		public void mostrarHabilidadesActivas() {
				imprimirEncabezado("Habilidades Activas:");
				Lista listaHabilidades = new Lista();
				for (Habilidad habilidadesActiva : habilidadesActivas) {
						if (habilidadesActiva != null && !listaHabilidades.duplicado(habilidadesActiva)) {
								listaHabilidades.agregar(habilidadesActiva.getNombre());
						}
				}
				listaHabilidades.imprimir(true);
		}
		/**
			* Verifica si el nombre del personaje es valido
			* @param nombre Nombre del personaje
			* @return un booleano true si el nombre es valido, false sino.
			*/
		private static boolean esNombreValido(String nombre) {
				return !nombresRegistrados.contains(nombre);
		}
		/**
			* Retorna la clase del jugador
			* @return la clase del jugador
			*/
		public String getClaseJugador() {
				return claseJugador;
		}

		public void verHabilidadesConocidas() {
				if (almacenHabilidades == null) {
						imprimirAviso("Ver_Habilidades", "El personaje aun no tiene un almacen de habilidades.");
						return;
				}
				almacenHabilidades.verHabilidadesJugador();
		}

		public List<Habilidad> getHabilidadesDisponibles() {
				return almacenHabilidades.getHabilidadesDisponibles();
		}

		public void equiparArma(Arma arma) {
				if (arma.getNivelRequerido() > this.nivel) {
						imprimirAviso("Equipar_Arma", String.format("Nivel insuficiente para equipar el arma %s" +
										"(Requiere nivel %d).", arma.getNombre(), arma.getNivelRequerido()));
						return;
				}
				if (this.armaEquipada.getNombre().equalsIgnoreCase(arma.getNombre())) {
						imprimirAviso("Equipar_Arma", String.format("Ya tienes equipada la arma: %s.", arma.getNombre()));
						return;
				}
				this.armaEquipada = arma;
				imprimirAviso("Equipar_Arma", String.format("Se ha equipado el arma: %s.", arma.getNombre()));
		}

		public void equiparArmadura(Armadura armadura) {
				if (armadura.getNivelRequerido() > this.nivel) {
						imprimirAviso("Equipar_Armadura", String.format("Nivel insuficiente para equipar la armadura %s" +
										"(Requiere nivel %d).", armadura.getNombre(), armadura.getNivelRequerido()));
						return;
				}
				if (this.armaduraEquipada.getNombre().equalsIgnoreCase(armadura.getNombre())) {
						imprimirAviso("Equipar_Armadura", String.format("Ya tienes equipada la armadura: %s.", armadura.getNombre()));
						return;
				}
				this.armaduraEquipada = armadura;
				imprimirAviso("Equipar_Armadura", String.format("Se ha equipado la armadura: %s.", armadura.getNombre()));
		}

		public void equiparAccesorio(Accesorio accesorio) {
				if (accesoriosEquipados.size() < 4) {
						accesoriosEquipados.add(accesorio);
				}
		}

		public boolean quitarAccesorio(Accesorio accesorio) {
				return accesoriosEquipados.remove(accesorio);
		}

		public void cambiarAccesorio(Accesorio nuevoAccesorio, Accesorio viejoAccesorio) {
				if (quitarAccesorio(viejoAccesorio)) {
						equiparAccesorio(nuevoAccesorio);
				}
		}

		public List<Accesorio> getAccesoriosEquipados() {
				return accesoriosEquipados;
		}

		public String mostrarAccesorios() {
				if (accesoriosEquipados.isEmpty()) {
						imprimirAviso("No hay Accesorios Equipados", "No tienes accesorios equipados.");
						return "No hay Accesorios Equipados";
				}
				Lista accesorios = new Lista();
				for (Accesorio accesorio : getAccesoriosEquipados()) {
						if (accesorio != null) {
								accesorios.agregar(accesorio);
						}
				}
				return accesorios.imprimirTexto(true);
		}

		public void quitarArma() {
				if (armaEquipada != null) {
						imprimirAviso("Quitar_Arma", String.format("Se ha quitado el arma: %s.", armaEquipada.getNombre()));
						armaEquipada = null;
				} else {
						imprimirAviso("Quitar_Arma", "No hay arma equipada.");
				}
		}

		public void quitarArmadura() {
				if (armaduraEquipada != null) {
						imprimirAviso("Quitar_Armadura", String.format("Se ha quitado la armadura: %s.", armaduraEquipada.getNombre()));
						armaduraEquipada = null;
				} else {
						imprimirAviso("Quitar_Armadura", "No hay armadura equipada.");
				}
		}

		public void incrementarDano(int valor, int turnos) {
		}

		public void incrementarVida(int valor) {
		}

		public void setInmovilizado(boolean b, int turnos) {
		}

		public void incrementarResistencia(int valor, int turnos) {
		}

		public void aplicarVeneno(int valor, int turnos) {
		}

		public void incrementarMana(int valor) {

		}

		public void aplicarQuemadura(int valor, int turnos) {
		}

		public List<Accesorio> getAccesoriosDisponibles() {
				return almacenEquipamientos.getAccesorios();
		}

		public List<Arma> getArmaDisponibles() {
				return almacenEquipamientos.getArmas();
		}

		public List<Armadura> getArmaduraDisponibles() {
				return almacenEquipamientos.getArmaduras();
		}

		public void reemplazarAccesorio(int indiceAccesorio, Accesorio accesorioSeleccionado) {
				if (indiceAccesorio < 0 || indiceAccesorio >= accesoriosEquipados.size()) {
						imprimirError("Reemplazar_Accesorio", "Indice de accesorio no valido.");
						return;
				}

				imprimir(String.format("Accesorio %s reemplazada por %s.",
								accesoriosEquipados.get(indiceAccesorio).getNombre(), accesorioSeleccionado.getNombre()));
				cambiarAccesorio(getAccesoriosDisponibles().get(indiceAccesorio), accesorioSeleccionado);
		}

		public void reemplazarArmadura(Armadura armaduraSeleccionada) {
				this.armaduraEquipada = armaduraSeleccionada;
		}

		public void reemplazarArma(Arma armaSeleccionada) {
				this.armaEquipada = armaSeleccionada;
		}

		public Arma getArmaEquipada() {
				return armaEquipada;
		}

		public Armadura getArmaduraEquipada() {
				return armaduraEquipada;
		}
}