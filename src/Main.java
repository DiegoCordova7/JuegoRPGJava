import Aplicaciones.AplicacionRPG.Logica.AplicacionRPG;
/**
 * Metodo principal que inicia la/s aplicacion/es.
 *
 * @param args Argumentos de la línea de comandos.
 */
public static void main(String[] args) {
    AplicacionRPG app = new AplicacionRPG("AplicacionRPG");
    app.inicializar();
    app.ejecutar();
    app.finalizar();
}