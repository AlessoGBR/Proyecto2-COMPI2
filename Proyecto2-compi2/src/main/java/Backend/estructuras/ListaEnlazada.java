package Backend.estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListaEnlazada<T> implements Iterable<T> {

    private static final class Nodo<T> {
        private T valor;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int tamanio;

    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public void agregar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (ultimo == null) {
            primero = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamanio++;
    }

    public void agregarTodos(Iterable<? extends T> elementos) {
        if (elementos == null) {
            return;
        }
        for (T valor : elementos) {
            agregar(valor);
        }
    }


    public T obtener(int indice) {
        return nodoEn(indice).valor;
    }


    public T establecer(int indice, T valor) {
        Nodo<T> nodo = nodoEn(indice);
        T anterior = nodo.valor;
        nodo.valor = valor;
        return anterior;
    }

    public boolean contiene(T valor) {
        for (T actual : this) {
            if (actual == null ? valor == null : actual.equals(valor)) {
                return true;
            }
        }
        return false;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void limpiar() {
        primero = null;
        ultimo = null;
        tamanio = 0;
    }

    private Nodo<T> nodoEn(int indice) {
        if (indice < 0 || indice >= tamanio) {
            throw new IndexOutOfBoundsException("FUERA DE RANGO: " + indice + " (TAMANIO " + tamanio + ")");
        }
        Nodo<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = primero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (actual == null) {
                    throw new NoSuchElementException();
                }
                T valor = actual.valor;
                actual = actual.siguiente;
                return valor;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        boolean primeroImpreso = true;
        for (T valor : this) {
            if (!primeroImpreso) {
                sb.append(", ");
            }
            sb.append(valor);
            primeroImpreso = false;
        }
        return sb.append(']').toString();
    }
}
