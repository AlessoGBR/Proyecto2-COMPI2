package Backend.simbolos;

import Backend.estructuras.Pila;

import java.util.List;

public class TablaSimbolos {

    private final Ambito ambitoGlobal;
    private final Pila<Ambito> pilaAmbitos;

    public TablaSimbolos() {
        this.ambitoGlobal = new Ambito("Global");
        this.pilaAmbitos = new Pila<>();
        this.pilaAmbitos.apilar(ambitoGlobal);
    }

    public Ambito abrirAmbito(String nombre) {
        Ambito nuevo = new Ambito(nombre, getAmbitoActual());
        pilaAmbitos.apilar(nuevo);
        return nuevo;
    }

    public Ambito abrirMarcoFuncion(String nombre) {
        Ambito nuevo = new Ambito(nombre, getAmbitoActual(), true, 1);
        pilaAmbitos.apilar(nuevo);
        return nuevo;
    }

    public Ambito abrirMarcoClase(String nombre) {
        return abrirMarcoClase(nombre, 0);
    }

    // POSICION DEL PRIMER ATRIBUTO DENTRO DEL OBJETO
    public Ambito abrirMarcoClase(String nombre, int primerDesplazamiento) {
        Ambito nuevo = new Ambito(nombre, getAmbitoActual(), true, primerDesplazamiento);
        pilaAmbitos.apilar(nuevo);
        return nuevo;
    }

    public Ambito cerrarAmbito() {
        if (pilaAmbitos.tamanio() > 1) {
            return pilaAmbitos.desapilar();
        }
        return getAmbitoActual();
    }

    public Ambito getAmbitoActual() {
        return pilaAmbitos.cima();
    }

    public Ambito getAmbitoGlobal() {
        return ambitoGlobal;
    }

    public boolean insertar(Simbolo simbolo) {
        return getAmbitoActual().insertar(simbolo);
    }

    public boolean insertarDefinicion(Simbolo simbolo) {
        return getAmbitoActual().insertarDefinicion(simbolo);
    }

    public Simbolo buscar(String identificador) {
        return getAmbitoActual().buscar(identificador);
    }

    public Simbolo buscarEnActual(String identificador) {
        return getAmbitoActual().buscarEnActual(identificador);
    }

    public List<Simbolo> obtenerTodosLosSimbolos() {
        return ambitoGlobal.obtenerTodosLosSimbolos();
    }
}
