package Backend.estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pila<T> implements Iterable<T> {

    private static final class Nodo<T> {
        private final T valor;
        private final Nodo<T> anterior;

        private Nodo(T valor, Nodo<T> anterior) {
            this.valor = valor;
            this.anterior = anterior;
        }
    }

    private Nodo<T> cima;
    private int tamanio;

    public Pila() {
        this.cima = null;
        this.tamanio = 0;
    }

    public void apilar(T valor) {
        cima = new Nodo<>(valor, cima);
        tamanio++;
    }

    public T desapilar() {
        if (cima == null) {
            throw new NoSuchElementException("NO SE PUEDE DESAPILAR: PILA VACIA");
        }
        T valor = cima.valor;
        cima = cima.anterior;
        tamanio--;
        return valor;
    }

    public T cima() {
        if (cima == null) {
            throw new NoSuchElementException("NO HAY CIMA: PILA VACIA");
        }
        return cima.valor;
    }

    public T cimaONulo() {
        return cima == null ? null : cima.valor;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public int tamanio() {
        return tamanio;
    }

    public void limpiar() {
        cima = null;
        tamanio = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Nodo<T> actual = cima;

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
                actual = actual.anterior;
                return valor;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CIMA [");
        boolean primero = true;
        for (T valor : this) {
            if (!primero) {
                sb.append(", ");
            }
            sb.append(valor);
            primero = false;
        }
        return sb.append("] BASE").toString();
    }
}
