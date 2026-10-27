package Backend.c3d;

import Backend.estructuras.Pila;
import java.util.ArrayList;
import java.util.List;

public class ControladorC3D {

    private int contadorTemporales;
    private int contadorEtiquetas;
    private int tamanioAreaGlobal;
    private final List<Cuarteta> cuartetas;
    private final Pila<String> pilaBreak;
    private final Pila<String> pilaContinue;

    public ControladorC3D() {
        this.contadorTemporales = 0;
        this.contadorEtiquetas = 0;
        this.tamanioAreaGlobal = 0;
        this.cuartetas = new ArrayList<>();
        this.pilaBreak = new Pila<>();
        this.pilaContinue = new Pila<>();
    }

    //GENERADOR DE VARIABLES TEMPORALES
    public String nuevoTemporal() {
        return "T" + (contadorTemporales++);
    }

    //GENERADOR DE ETIQUETAS
    public String nuevaEtiqueta() {
        return "L" + (contadorEtiquetas++);
    }

    public Cuarteta agregar(OperadorC3D op, String arg1, String arg2, String res) {
        Cuarteta c = new Cuarteta(op, arg1, arg2, res);
        cuartetas.add(c);
        return c;
    }

    public int getTamanioAreaGlobal() {
        return tamanioAreaGlobal;
    }

    public void setTamanioAreaGlobal(int tamanioAreaGlobal) {
        this.tamanioAreaGlobal = tamanioAreaGlobal;
    }

    public void agregarComentario(String texto) {
        agregar(OperadorC3D.COMENTARIO, texto, "", "");
    }

    public void agregarEtiqueta(String etiqueta) {
        agregar(OperadorC3D.ETIQUETA, "", "", etiqueta);
    }

    public void agregarGoto(String etiqueta) {
        agregar(OperadorC3D.GOTO, "", "", etiqueta);
    }

    public void agregarAsignacion(String dest, String origen) {
        agregar(OperadorC3D.ASIG, origen, "", dest);
    }

    public void apilarCiclo(String etiquetaBreak, String etiquetaContinue) {
        pilaBreak.apilar(etiquetaBreak);
        pilaContinue.apilar(etiquetaContinue);
    }

    public void desapilarCiclo() {
        if (!pilaBreak.estaVacia()) pilaBreak.desapilar();
        if (!pilaContinue.estaVacia()) pilaContinue.desapilar();
    }

    public String getEtiquetaBreakActual() {
        return pilaBreak.cimaONulo();
    }

    public String getEtiquetaContinueActual() {
        return pilaContinue.cimaONulo();
    }

    public String guardarCadenaEnHeap(String texto) {
        String tInicio = nuevoTemporal();
        agregar(OperadorC3D.ASIG, "H", "", tInicio);
        for (int i = 0; i < texto.length(); i++) {
            int charVal = (int) texto.charAt(i);
            agregar(OperadorC3D.ASIG_HEAP, String.valueOf(charVal), "", "H");
            agregar(OperadorC3D.SUMA, "H", "1", "H");
        }

        agregar(OperadorC3D.ASIG_HEAP, "-1", "", "H");
        agregar(OperadorC3D.SUMA, "H", "1", "H");

        return tInicio;
    }

    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }

    public String obtenerCodigoC3D() {
        StringBuilder sb = new StringBuilder();
        for (Cuarteta c : cuartetas) {
            sb.append(c.aFormatoC3D()).append("\n");
        }
        return sb.toString();
    }

    public int getCantidadTemporales() {
        return contadorTemporales;
    }

    public void reiniciar() {
        contadorTemporales = 0;
        contadorEtiquetas = 0;
        tamanioAreaGlobal = 0;
        cuartetas.clear();
        pilaBreak.limpiar();
        pilaContinue.limpiar();
    }
}
