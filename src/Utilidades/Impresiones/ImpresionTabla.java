package Utilidades.Impresiones;
import java.util.ArrayList;
import java.util.List;
import static Utilidades.Impresiones.Colores.colorear;
import static Utilidades.Impresora.imprimirEspacio;
import static Utilidades.Impresora.imprimirSeguido;
/**
 * Clase que proporciona metodos para imprimir tablas formateadas en la consola.
 * La tabla puede incluir un titulo, un encabezado y datos. Ademas, ajusta el ancho de las columnas
 * segun el contenido y el titulo.
 */
public class ImpresionTabla {
    private static final int ANCHO_MAXIMO_CELDA = 40;
    private static final String ESQUINA = colorear(Colores.GRIS, "+");
    private static final String VERTICAL = colorear(Colores.GRIS, "|");
    private static final String HORIZONTAL = colorear(Colores.GRIS, "-");
    /**
     * Metodo que imprime una tabla en la consola.
     *
     * @param datos El arreglo bidimensional que contiene los datos de la tabla.
     * @param encabezado El arreglo que contiene los encabezados de las columnas.
     * @param titulo El titulo de la tabla.
     */
    public static void imprimirTabla(String[][] datos, String[] encabezado, String titulo) {
        int numColumnas = encabezado.length;
        int[] anchos = new int[numColumnas];
        for (int i = 0; i < numColumnas; i++) {
            int maxLongitud = encabezado[i].length();
            for (String[] fila : datos)
                maxLongitud = Math.max(maxLongitud, fila[i].length());
            anchos[i] = Math.min(maxLongitud, ANCHO_MAXIMO_CELDA);
        }
        imprimirFilaTitulo(titulo, anchos);
        imprimirLineaSeparadora(anchos);
        imprimirEncabezado(encabezado, anchos);
        imprimirLineaSeparadora(anchos);
        for (String[] fila : datos) {
            imprimirFila(fila, anchos);
            imprimirLineaSeparadora(anchos);
        }
    }
    /**
     * Imprime el titulo de la tabla con el formato adecuado.
     *
     * @param titulo El titulo de la tabla.
     * @param anchos Los anchos de las columnas de la tabla.
     */
    private static void imprimirFilaTitulo(String titulo, int[] anchos) {
        int anchoTotal = 0;
        for (int ancho : anchos)
            anchoTotal += ancho + 3;
        int espacioIzquierda = (anchoTotal - titulo.length()) / 2;
        int espacioDerecha = anchoTotal - titulo.length() - espacioIzquierda - 1;
        imprimirLinea(anchoTotal);
        imprimirSeguido(VERTICAL);
        imprimirSeguido(" ".repeat(espacioIzquierda));
        imprimirSeguido(colorear(Colores.AZUL,titulo));
        imprimirSeguido(" ".repeat(espacioDerecha));
        imprimirSeguido(VERTICAL);
        imprimirEspacio();
    }
    /**
     * Imprime una linea de la tabla.
     *
     * @param cantidad La cantidad de caracteres de la linea a imprimir.
     */
    private static void imprimirLinea (int cantidad) {
        for (int i = 0; i <= cantidad; i++)
            if (i == 0 || i == cantidad)
                imprimirSeguido(ESQUINA);
            else
                imprimirSeguido(HORIZONTAL);
        imprimirEspacio();
    }
    /**
     * Imprime una fila de datos en la tabla, dividiendo el contenido si es necesario para ajustarse al ancho de la columna.
     *
     * @param fila Los datos de la fila.
     * @param anchos Los anchos de las columnas.
     */
    private static void imprimirFila (String[] fila, int[] anchos) {
        List<List<String>> lineasPorColumna = new ArrayList<>();
        for (int i = 0; i < fila.length; i++) {
            String contenido = fila[i].trim();
            List<String> lineas = dividirTexto(contenido, anchos[i]);
            lineasPorColumna.add(lineas);
        }
        int maxLineas = lineasPorColumna.stream().mapToInt(List::size).max().orElse(0);
        for (int linea = 0; linea < maxLineas; linea++) {
            imprimirSeguido(VERTICAL);
            for (int i = 0; i < lineasPorColumna.size(); i++) {
                List<String> lineas = lineasPorColumna.get(i);
                String contenido = (linea < lineas.size()) ? lineas.get(linea) : "";
                imprimirSeguido(String.format(" %-"+anchos[i]+"s ", contenido));
                imprimirSeguido(VERTICAL);
            }
            imprimirEspacio();
        }
    }
    /**
     * Imprime el encabezado de la tabla.
     *
     * @param encabezado Los encabezados de las columnas.
     * @param anchos Los anchos de las columnas.
     */
    private static void imprimirEncabezado(String[] encabezado, int[] anchos) {
        imprimirSeguido(VERTICAL);
        for (int i = 0; i < encabezado.length; i++) {
            imprimirSeguido(String.format(" %-"+anchos[i]+"s ", encabezado[i]));
            imprimirSeguido(VERTICAL);
        }
        imprimirEspacio();
    }
    /**
     * Imprime una linea separadora en la tabla.
     *
     * @param anchos Los anchos de las columnas de la tabla.
     */
    private static void imprimirLineaSeparadora(int[] anchos) {
        imprimirSeguido(ESQUINA);
        for (int ancho : anchos)
            imprimirSeguido(HORIZONTAL.repeat(ancho + 2) + ESQUINA);
        imprimirEspacio();
    }
    /**
     * Divide el texto en lineas de longitud adecuada para ajustarse al ancho maximo de la columna.
     *
     * @param texto El texto a dividir.
     * @param anchoMaximo El ancho maximo de cada columna.
     * @return Una lista de lineas de texto que representan el contenido ajustado al ancho.
     */
    private static List<String> dividirTexto(String texto, int anchoMaximo) {
        List<String> lineas = new ArrayList<>();
        String[] palabras = texto.split(" ");
        StringBuilder lineaActual = new StringBuilder();
        for (String palabra : palabras) {
            palabra = palabra.trim();
            if (lineaActual.length() + palabra.length() + 1 <= anchoMaximo) {
                if (!lineaActual.isEmpty())
                    lineaActual.append(" ");
                lineaActual.append(palabra);
            } else {
                lineas.add(lineaActual.toString());
                lineaActual.setLength(0);
                lineaActual.append(palabra);
            }
        }
        if (!lineaActual.isEmpty())
            lineas.add(lineaActual.toString());
        return lineas;
    }
}