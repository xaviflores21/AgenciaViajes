/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafospeso;

/**
 *
 * @author xavier
 */
public class GrafoSingleton {

    private static Grafo<String, String, Integer> instanciaCosto;
    private static Grafo<String, String, Integer> instanciaDistancia;

    public static Grafo<String, String, Integer> instanciaCosto() {
        if (instanciaCosto == null) {
            instanciaCosto = new Grafo<>();
        }
        return instanciaCosto;
    }

    public static Grafo<String, String, Integer> instanciaDistancia() {
        if (instanciaDistancia == null) {
            instanciaDistancia = new Grafo<>();
        }
        return instanciaDistancia;
    }
}
