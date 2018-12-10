package Diccionario;

import java.util.Iterator;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V>
 */
public class Iterador<K, V> implements Iterator<Nodo<K, V>> {

    private Nodo<K, V> actual;

    public Iterador(Nodo<K, V> inicial) {
        this.actual = inicial;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public Nodo<K, V> next() {
        Nodo<K, V> nodo = actual;
        actual = actual.getSiguiente();
        return nodo;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
