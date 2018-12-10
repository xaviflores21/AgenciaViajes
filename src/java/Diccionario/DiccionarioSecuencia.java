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
public class DiccionarioSecuencia<K, V> extends Diccionario<K, V> {

    // la llave no puede ser nula
    private Nodo<K, V> primero;
    private Nodo<K, V> ultimo;

    public DiccionarioSecuencia() {
        super();
    }

    public DiccionarioSecuencia(Comparador comparador) {
        super(comparador);
    }

    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void insertar(K key, V value) {

        cantidadElementos++;
        Nodo<K, V> nuevoNodo = new Nodo<K, V>(key, value);
        if (primero == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
            return;
        }

        nuevoNodo.setAnterior(ultimo);
        ultimo.setSiguiente(nuevoNodo);
        ultimo = nuevoNodo;
    }

    private Nodo<K, V> getNodo(K key) {
        Nodo<K, V> nodo = primero;
        while (nodo != null) {
            if (comparador.esIgual(nodo.getLlave(), key)) {
                return nodo;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    @Override
    public V obtener(K key) {
        Nodo<K, V> nodo = getNodo(key);
        if (nodo != null) {
            return nodo.getValor();
        }
        return null;
    }

    @Override
    public V eliminar(K key) {

        Nodo<K, V> nodo = getNodo(key);
        if (nodo == null) {
            return null;
        }

        cantidadElementos--;
        V value = nodo.getValor();
        if (primero == ultimo && nodo == primero) {
            primero = null;
            ultimo = null;
            return value;
        }

        if (primero == nodo) {
            primero.getSiguiente().setAnterior(null);
            primero = primero.getSiguiente();
            nodo.setSiguiente(null);
            return value;
        }

        if (ultimo == nodo) {
            ultimo.getAnterior().setSiguiente(null);
            ultimo = ultimo.getAnterior();
            nodo.setAnterior(null);
            return value;
        }

        nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(nodo.getAnterior());
        nodo.setSiguiente(null);
        nodo.setAnterior(null);

        return value;
    }

    @Override
    public boolean contieneLlave(K key) {
        return getNodo(key) != null;
    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public List<K> getLlaves() {
        List lista = new LinkedList();

        if (primero == null) {
            return null;
        }

        Nodo aux = primero;
        for (int i = 0; i < cantidadElementos; i++) {
            lista.add(aux.getLlave());
            aux = aux.getSiguiente();
        }
        return lista;
    }

    @Override
    public List<V> getValores() {
        LinkedList<V> valores = new LinkedList<>();
        Nodo<K, V> auxNodo = primero;
        while (auxNodo != null) {
            valores.add(auxNodo.getValor());
            auxNodo = auxNodo.getSiguiente();
        }
        return valores;
    }

    @Override
    public String toString() {
        Nodo aux = primero;
        String k = "";
        String v = "";
        while (aux != null) {
            System.out.println(aux + "");
            aux = aux.getSiguiente();
        }
        return k + " " + v;
    }

    public Iterator<Nodo<K, V>> iterator() {
        return new Iterador<>(primero);
    }
}
