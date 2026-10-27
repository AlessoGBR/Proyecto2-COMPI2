package Backend.estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Cola<T> implements Iterable<T> {

    private static final class Nodo<T> {
        private final T valor;
        private Nodo<T> siguiente;

        private Nodo(T valor) {
            this.valor = valor;
            this.siguiente = null;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> ultimo;
    private int tamanio;

    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    public void encolar(T valor) {
        Nodo<T> nuevo = new Nodo<>(valor);
        if (ultimo == null) {
            frente = nuevo;
        } else {
            ultimo.siguiente = nuevo;
        }
        ultimo = nuevo;
        tamanio++;
    }

    public T desencolar() {
        if (frente == null) {
            throw new NoSuchElementException("NO SE PUEDE DESENCOLAR: COLA VACIA");
        }
        T valor = frente.valor;
        frente = frente.siguiente;
        if (frente == null) {
            ultimo = null;
        }
        tamanio--;
        return valor;
    }

    public T frente() {
        if (frente == null) {
            throw new NoSuchElementException("NO HAY INICIO: COLA VACIA");
        }
        return frente.valor;
    }

    public T frenteONulo() {
        return frente == null ? null : frente.valor;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void limpiar() {
        frente = null;
        ultimo = null;
        tamanio = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = frente;

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
        StringBuilder sb = new StringBuilder("FRENTE [");
        boolean primero = true;
        for (T valor : this) {
            if (!primero) {
                sb.append(", ");
            }
            sb.append(valor);
            primero = false;
        }
        return sb.append("] FINAL").toString();
    }
}
