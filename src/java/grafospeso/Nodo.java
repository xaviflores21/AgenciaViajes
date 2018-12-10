/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafospeso;

import Diccionario.Diccionario;
import Diccionario.TablaHash;
import java.util.List;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V>
 * @param <C>
 */
public class Nodo<K, V, C> {

    private K id;
    private V valor;
    private Diccionario<K, Arista<K, V, C>> aristas = new TablaHash<>();

    protected Nodo(K id, V valor) {
        this.id = id;
        this.valor = valor;
    }

    public K getId() {
        return id;
    }

    public void setId(K id) {
        this.id = id;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public void insertarArista(Nodo<K, V, C> destino, C costo) {
        if (destino == null) {
            throw new IllegalArgumentException("El nodo destino no puede ser nulo");
        }
        aristas.insertar(id, new Arista<>(costo, destino));
    }

    public void eliminarArista(K id) {
        if (id == null) {
            System.err.println("id null en eliminar Arista");
        }
        if (!contieneArista(id)) {
            System.err.println("no contiene arista en eliminar Arista");
        }
        aristas.eliminar(id);
    }

    public boolean contieneArista(K id) {
        return aristas.contieneLlave(id);
    }

    public List<Arista<K, V, C>> getAristas() {
        return aristas.getValores();
    }

    @Override
    public String toString() {
        return id.toString() + valor.toString() + "";
    }
}
