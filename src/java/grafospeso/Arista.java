/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafospeso;

/**
 *
 * @author xavier
 * @param <K>
 * @param <C>
 * @param <V>
 */
public class Arista<K, V, C> {

    private C costo;
    private Nodo<K, V, C> destino;

    public Arista(C costo, Nodo<K, V, C> destino) {
        this.costo = costo;
        this.destino = destino;
    }

    public C getCosto() {
        return costo;
    }

    public void setCosto(C costo) {
        this.costo = costo;
    }

    public Nodo<K, V, C> getDestino() {
        return destino;
    }

    public void setDestino(Nodo<K, V, C> destino) {
        this.destino = destino;
    }

}
