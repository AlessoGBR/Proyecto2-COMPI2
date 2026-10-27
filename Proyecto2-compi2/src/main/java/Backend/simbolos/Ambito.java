package Backend.simbolos;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ambito {

    public static final int SLOT_RETORNO = 0;

    private final String nombre;
    private final Ambito padre;
    private final Map<String, Simbolo> tabla;
    private final Map<String, Simbolo> funciones;
    private final List<Ambito> hijos;
    private final boolean esMarco;
    private int tamanioMarco;

    public Ambito(String nombre, Ambito padre, boolean esMarco, int offsetInicial) {
        this.nombre = nombre;
        this.padre = padre;
        this.tabla = new LinkedHashMap<>();
        this.funciones = new LinkedHashMap<>();
        this.hijos = new ArrayList<>();
        this.esMarco = esMarco || padre == null;
        this.tamanioMarco = offsetInicial;
        if (padre != null) {
            padre.hijos.add(this);
        }
    }

    public Ambito(String nombre, Ambito padre) {
        this(nombre, padre, false, 0);
    }

    public Ambito(String nombre) {
        this(nombre, null, true, 0);
    }

    public boolean esMarco() {
        return esMarco;
    }

    public Ambito getMarco() {
        Ambito actual = this;
        while (!actual.esMarco && actual.padre != null) {
            actual = actual.padre;
        }
        return actual;
    }

    public int reservarEspacio() {
        Ambito marco = getMarco();
        return marco.tamanioMarco++;
    }

    public int getTamanioMarcoContenedor() {
        return getMarco().tamanioMarco;
    }

    public String getNombre() {
        return nombre;
    }

    public Ambito getPadre() {
        return padre;
    }

    public boolean esGlobal() {
        return padre == null;
    }

    public boolean insertar(Simbolo simbolo) {
        if (simbolo == null || tabla.containsKey(simbolo.getIdentificador())) {
            return false;
        }
        simbolo.setNombreAmbito(this.nombre);
        simbolo.setEsGlobal(getMarco().esGlobal());
        simbolo.setDireccionRelativa(reservarEspacio());
        tabla.put(simbolo.getIdentificador(), simbolo);
        return true;
    }

    // ATRIBUTO HEREDADO YA TIENE SU ESPACIO RESERVADO, NO SE LE ASIGNA NUEVO ESPACIO
    public boolean insertarConDireccion(Simbolo simbolo) {
        if (simbolo == null || tabla.containsKey(simbolo.getIdentificador())) {
            return false;
        }
        simbolo.setNombreAmbito(this.nombre);
        tabla.put(simbolo.getIdentificador(), simbolo);
        return true;
    }

    public boolean insertarDefinicion(Simbolo simbolo) {
        if (simbolo == null || tabla.containsKey(simbolo.getIdentificador())) {
            return false;
        }
        simbolo.setNombreAmbito(this.nombre);
        simbolo.setEsGlobal(getMarco().esGlobal());
        tabla.put(simbolo.getIdentificador(), simbolo);
        return true;
    }

    public Simbolo buscar(String identificador) {
        Simbolo encontrado = tabla.get(identificador);
        if (encontrado != null) {
            return encontrado;
        }
        if (padre != null) {
            return padre.buscar(identificador);
        }
        return null;
    }

    public Simbolo buscarEnActual(String identificador) {
        return tabla.get(identificador);
    }

    public boolean insertarFuncion(Simbolo simbolo) {
        if (simbolo == null || funciones.containsKey(simbolo.getIdentificador())) {
            return false;
        }
        simbolo.setNombreAmbito(this.nombre);
        simbolo.setEsGlobal(getMarco().esGlobal());
        funciones.put(simbolo.getIdentificador(), simbolo);
        return true;
    }

    public Simbolo buscarFuncion(String identificador) {
        Simbolo encontrada = funciones.get(identificador);
        if (encontrada != null) {
            return encontrada;
        }
        if (padre != null) {
            return padre.buscarFuncion(identificador);
        }
        return null;
    }

    public Map<String, Simbolo> getTabla() {
        return tabla;
    }

    public Map<String, Simbolo> getFunciones() {
        return funciones;
    }

    public List<Ambito> getHijos() {
        return hijos;
    }

    public int getTamanioMarco() {
        return tamanioMarco;
    }

    public void setTamanioMarco(int tamanioMarco) {
        this.tamanioMarco = tamanioMarco;
    }

    public List<Simbolo> obtenerTodosLosSimbolos() {
        List<Simbolo> lista = new ArrayList<>(tabla.values());
        lista.addAll(funciones.values());
        for (Ambito hijo : hijos) {
            lista.addAll(hijo.obtenerTodosLosSimbolos());
        }
        return lista;
    }
}
