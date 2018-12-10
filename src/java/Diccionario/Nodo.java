/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diccionario;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V>
 */
public class Nodo<K, V> {

    private K llave;
    private V valor;

    Nodo<K, V> anterior;
    Nodo<K, V> siguiente;

    public Nodo(K llave, V valor) {
        this.llave = llave;
        this.valor = valor;
    }

    public Nodo() {
    }

    public K getLlave() {
        return llave;
    }

    public void setLlave(K llave) {
        this.llave = llave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public Nodo<K, V> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<K, V> anterior) {
        this.anterior = anterior;
    }

    public Nodo<K, V> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<K, V> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return llave + " " + valor;
    }

}
