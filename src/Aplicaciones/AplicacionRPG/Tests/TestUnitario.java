package Aplicaciones.AplicacionRPG.Tests;

import Aplicaciones.AplicacionRPG.Habilidades.Habilidad;
import Aplicaciones.AplicacionRPG.Personajes.Arquero;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresora.imprimir;

public class TestUnitario {

		private Arquero arquero;
		private List<Habilidad> habilidades = new ArrayList<>();

		public TestUnitario() {
				setUp();
		}

		public void runTest() {
				testEstadisticasIniciales();
				testAumentoDeNivel();
		}

		public void setUp() {
				arquero = new Arquero("Arquero1", "Arquero", habilidades);
		}

		public void testEstadisticasIniciales() {
				if (arquero.getVida() == 54 && arquero.getFuerzaFisica() == 30 && arquero.getPrecision() == 10) {
						imprimir("Prueba PASADA: testEstadisticasIniciales");
				} else {
						imprimir("Prueba FALLIDA: testEstadisticasIniciales");
				}
		}

		public void testAumentoDeNivel() {
				double vidaInicial = arquero.getVida();
				double fuerzaFisicaInicial = arquero.getFuerzaFisica();
				int precisionInicial = arquero.getPrecision();

				arquero.subirNivel();

				if (arquero.getVida() > vidaInicial && arquero.getFuerzaFisica() > fuerzaFisicaInicial &&
								arquero.getPrecision() > precisionInicial) {
						imprimir("Prueba PASADA: testAumentoDeNivel");
				} else {
						imprimir("Prueba FALLIDA: testAumentoDeNivel");
				}
		}
}