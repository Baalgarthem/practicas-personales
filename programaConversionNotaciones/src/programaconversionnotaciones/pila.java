package programaconversionnotaciones;

import java.util.*;

/**
 *
 * @author Arturo Ramirez
 * @param <T>
 */
public class pila<T> {
    private ArrayList<T> pila;

    public pila() {
        pila = new ArrayList<T>();
    }

    public void push(T elemento) {
        pila.add(elemento);
    }

    public T pop() {
        if (pila.isEmpty()) {
            throw new NoSuchElementException();
        }
        return pila.remove(pila.size() - 1);
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public T peek() {
        if (pila.isEmpty()) {
            throw new NoSuchElementException();
        }
        return pila.get(pila.size() - 1);
    }

    public int size() {
        return pila.size();
    }
}
