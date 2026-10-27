package Backend.riscv;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class ProgramaRiscV {

    // MAPA DE MEMORIA
    public static final int BASE_TEXTO = 0x00400000;
    public static final int BASE_DATOS = 0x10010000;
    public static final int BASE_HEAP = 0x10040000;
    public static final int PILA_INICIAL = 0x7fffeffc;
    public static final int GP_INICIAL = 0x10008000;

    private final List<InstruccionRiscV> instrucciones;
    private final byte[] datos;
    private final Map<String, Integer> etiquetas;
    private final int indiceEntrada;

    ProgramaRiscV(List<InstruccionRiscV> instrucciones, byte[] datos, Map<String, Integer> etiquetas, int indiceEntrada) {
        this.instrucciones = instrucciones;
        this.datos = datos;
        this.etiquetas = etiquetas;
        this.indiceEntrada = indiceEntrada;
    }

    public List<InstruccionRiscV> getInstrucciones() {
        return Collections.unmodifiableList(instrucciones);
    }

    public byte[] getDatos() {
        return datos;
    }

    public Map<String, Integer> getEtiquetas() {
        return Collections.unmodifiableMap(etiquetas);
    }

    public int getIndiceEntrada() {
        return indiceEntrada;
    }

    public int getCantidadInstrucciones() {
        return instrucciones.size();
    }
}
