/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Diccionario;

/**
 *
 * @author xavier
 * @param <E>
 */
public class ComparadorGenerico<E> implements Comparador<E> {

    @Override
    public boolean esIgual(E objA, E objB) {
        return objA.equals(objB);
    }

    @Override
    public int getHashCode(E key) {
        return key.hashCode();
    }

}
