/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diccionario;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V>
 */
public class TablaHash<K, V> extends Diccionario<K, V> {

    private DiccionarioSecuencia[] elementos;
    private float factorCarga;
    private int limite;
    private int cantidadElementos;

    public TablaHash(Comparador c) {
        super(c);
        this.inicializar();
    }

    public TablaHash() {
        super();
        this.inicializar();
    }

    private void inicializar() {
        this.elementos = new DiccionarioSecuencia[11];
        this.factorCarga = 0.75f;
        this.limite = (int) (elementos.length * factorCarga);
        this.cantidadElementos = 0;
    }

    private int getPosicion(K key, int n) {
        int hash;
        hash = comparador.getHashCode(key);
        return (hash & 0x7FFFFFFF) % n;
    }

    @Override
    public boolean contieneLlave(K key) {

        if (key == null) {
            throw new IllegalArgumentException("La llave no puede ser nula");
        }

        int posicion = getPosicion(key, elementos.length);
        if (elementos[posicion] == null) {
            return false;
        }

        return elementos[posicion].contieneLlave(key);
    }

    @Override
    public void insertar(K key, V value) {
        if (key == null) {
            throw new NullPointerException("La llave no puede ser nula");
        }
        int codigoHash = comparador.getHashCode(key);
        int posicion = getPosicion(codigoHash, elementos.length);
        if (contieneLlave(key)) {
            elementos[posicion].insertar(key, value);
            cantidadElementos++;
            return;
        }
        if (cantidadElementos >= limite) {
            redistribuir();
        }
        posicion = getPosicion(codigoHash, elementos.length);
        if (elementos[posicion] == null) {
            elementos[posicion] = new DiccionarioSecuencia<>(comparador);
        }
        elementos[posicion].insertar(key, value);
        cantidadElementos++;
    }

    @Override
    public V obtener(K key) {
        if (key == null) {
            throw new IllegalArgumentException("La llave no puede ser nula");
        }

        int posicion = getPosicion(key, elementos.length);
        if (elementos[posicion] == null) {
            return null;
        }

        return (V) elementos[posicion].obtener(key);
    }

    /**
     *
     * @param key
     * @return
     */
    @Override
    public V eliminar(K key) {
        if (key == null) {
            throw new NullPointerException("La llave no puede ser nula");
        }
        int codigoHash = comparador.getHashCode(key);
        int laPosicion = getPosicion(codigoHash, elementos.length);
        if (elementos[laPosicion] == null) {
            return null;
        }
        V valor = (V) elementos[laPosicion].eliminar(key);
        cantidadElementos--;
        if (elementos[laPosicion].estaVacio()) {
            elementos[laPosicion] = null;
        }
        return valor;
    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public List<K> getLlaves() {
        List<K> lista = new LinkedList<>();
        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento == null) {
                continue;
            }
            lista.addAll(elemento.getLlaves());
        }
        return lista;
    }

    @Override
    public List<V> getValores() {
        List<V> lista = new LinkedList<>();
        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento == null) {
                continue;
            }
            lista.addAll(elemento.getValores());
        }
        return lista;
    }

    private void redistribuir() {
        int nuevaCapacidad = elementos.length * 2 + 1;
        limite = (int) (nuevaCapacidad * factorCarga);
        DiccionarioSecuencia<K, V>[] listaAux = new DiccionarioSecuencia[nuevaCapacidad];
        for (DiccionarioSecuencia<K, V> tabla : elementos) {
            if (tabla == null) {
                continue;
            }
            Iterator<Nodo<K, V>> auxIterator = tabla.iterator();
            while (auxIterator.hasNext()) {
                Nodo<K, V> nodo = auxIterator.next();
                int codigoHash = comparador.getHashCode(nodo.getLlave());
                int posicion = getPosicion(codigoHash, listaAux.length);
                if (listaAux[posicion] == null) {
                    listaAux[posicion] = new DiccionarioSecuencia<>(comparador);
                }
                listaAux[posicion].insertar(nodo.getLlave(), nodo.getValor());
            }
        }
        this.elementos = listaAux;
    }

    private int getPosicion(int hash, int capacidad) {
        return (hash & Integer.MAX_VALUE) % capacidad;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
