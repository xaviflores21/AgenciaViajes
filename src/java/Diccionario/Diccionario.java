/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diccionario;

import java.util.List;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V> 
 * Si existe llave repetida se actualiza su valor con el ultimo valor
 */
public abstract class Diccionario<K, V> {

    protected int cantidadElementos;
    Comparador<K> comparador;

    public Diccionario() {
        this.comparador = new ComparadorGenerico<>();
    }

    public Diccionario(Comparador comparador) {
        this.comparador = comparador;
    }

    public abstract void insertar(K key, V value);

    public abstract V obtener(K key);

    public abstract V eliminar(K key);

    public abstract boolean contieneLlave(K key);

    public abstract boolean estaVacio();

    public abstract List<K> getLlaves();

    public abstract List<V> getValores();

    public int getCantidadElementos() {
        return cantidadElementos;
    }

}
