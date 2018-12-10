/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafospeso;

import Diccionario.Diccionario;
import Diccionario.TablaHash;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author xavier
 * @param <K>
 * @param <V>
 * @param <C>
 */
public class Grafo<K, V, C> {

    private Diccionario<K, Nodo<K, V, C>> vertices = new TablaHash<>();
    private int menor = Integer.MAX_VALUE;

    public Grafo() {
    }

    public void insertarVertice(K key, V value) {
        if (key == null) {
            System.err.println("NO CONTIENE UN ID");
        }
        if (vertices.contieneLlave(key)) {
            System.err.println("EXISTE SIMILAR");
        }
        Nodo<K, V, C> nuevoVertice = new Nodo(key, value);
        vertices.insertar(key, nuevoVertice);
    }

    public V obtener(K key) {
        if (key == null) {
            System.err.println("NO EXISTE ID");
        }
        if (!vertices.contieneLlave(key)) {
            System.err.println("NO CONTIENE LLAVE");
        }
        return vertices.obtener(key).getValor();
    }

    public V eliminarVertice(K key) {
        if (key == null) {
            throw new IllegalArgumentException("El id del vertice no puede ser nulo");
        }

        if (!vertices.contieneLlave(key)) {
            throw new IllegalArgumentException("NO existe el vertice origen dentro del grafo");
        }

        List<Nodo<K, V, C>> nodos = vertices.getValores();
        for (Nodo<K, V, C> nodo : nodos) {
            if (nodo.contieneArista(key)) {
                nodo.eliminarArista(key);
            }
        }
        vertices.eliminar(key);
        return null;
    }

    public void insertarArista(K origen, K destino, C costo) {
        if (!vertices.contieneLlave(origen)) {
            System.err.println("NO HAY LLAVE ORIGEN");
        }
        if (!vertices.contieneLlave(destino)) {
            System.err.println("NO HAY LLAVE DESTINO");
        }
        Nodo<K, V, C> $origen = vertices.obtener(origen);
        Nodo<K, V, C> $destino = vertices.obtener(destino);
        System.out.println("esta recibiendo de costo en grafo insertar: " + costo);
        $origen.insertarArista($destino, costo);
    }

    public void eliminarArista(K origen, K destino) {
        if (!vertices.contieneLlave(origen)) {
            System.err.println("NO HAY LLAVE ORIGEN PARA ELIMINAR");
        }
        if (!vertices.contieneLlave(destino)) {
            System.err.println("NO HAY LLAVE DESTINO PARA ELIMINAR");
        }
        Nodo<K, V, C> $origen = vertices.obtener(origen);
        $origen.eliminarArista(destino);
    }

    public List<K> getIdsVertices() {
        return vertices.getLlaves();
    }

    public List<Nodo<K, V, C>> getValoresVertices() {
        return vertices.getValores();
    }

    public String mostrar() {
        String res = "";
        List<Nodo<K, V, C>> vertic = getValoresVertices();
        for (Nodo<K, V, C> vertice : vertic) {
            res += ("\n" + vertice.getId() + ": \n");
            List<Arista<K, V, C>> list = vertice.getAristas();
            for (Arista<K, V, C> nodo : list) {
                res += (" -> " + nodo.getDestino().getId().toString() + "(" + nodo.getCosto() + ")");
            }
            res += "\n";
        }
        return res;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
    }

    public ArrayList<Camino> obtenerCaminos(K origen, K destino) {
        ArrayList<Camino> caminos = new ArrayList<>();
        
        Nodo<K, V, C> $origen = vertices.obtener(origen);
        Nodo<K, V, C> $destino = vertices.obtener(destino);
        
        Queue<Camino<K, V, C>> cola = new LinkedList<>();
        Camino<K, V, C> camino = new Camino<>();
        
        camino.agregar($origen, 0);
        cola.add(camino);
        
        while (!cola.isEmpty()) {
            Camino<K, V, C> actual = cola.poll();
            Nodo<K, V, C> ultimo = actual.ultima();
            if (ultimo.getId().equals($destino.getId())) {
                caminos.add(actual);
                continue;
            }

            List<Arista<K, V, C>> aristas = ultimo.getAristas();
            for (Arista<K, V, C> arista : aristas) {
                Nodo<K, V, C> destino$ = arista.getDestino();
                if (!actual.contiene(destino$)) {
                    Camino<K, V, C> copia = actual.getCopia();
                    copia.agregar(arista.getDestino(), (Integer) arista.getCosto());
                    cola.add(copia);
                }
            }
        }

        return caminos;
    }

    public ArrayList<Nodo<K, V, C>> buscaMenor(ArrayList<Camino> lista) {
        menor = Integer.MAX_VALUE;
        ArrayList<Nodo<K, V, C>> caminoMenor = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCosto() < menor) {
                caminoMenor = lista.get(i).getLista();
                menor = lista.get(i).getCosto();
            }
        }
        return caminoMenor;
    }

    // ----------- clase Camino ---- para encontrar los caminos
    public class Camino<K, V, C> {

        ArrayList<Nodo<K, V, C>> list;
        int costo;

        public Camino() {
            list = new ArrayList<>();
            costo = 0;
        }

        public Camino(ArrayList<Nodo<K, V, C>> list, int costo) {
            this.list = list;
            this.costo = costo;
        }

        public ArrayList<Nodo<K, V, C>> getLista() {
            return list;
        }

        public void setLista(ArrayList<Nodo<K, V, C>> list) {
            this.list = list;
        }

        public int getCosto() {
            return costo;
        }

        public void setCosto(int costo) {
            this.costo = costo;
        }

        public void agregar(Nodo<K, V, C> nodo, int costo) {
            this.list.add(nodo);
            this.costo += costo;
        }

        public boolean contiene(Nodo<K, V, C> nodo) {
            for (Nodo<K, V, C> nodo1 : list) {
                if (nodo.getId() == nodo1.getId()) {
                    return true;
                }
            }
            return false;
        }

        public Nodo<K, V, C> ultima() {
            return list.get(list.size() - 1);
        }

        public Camino getCopia() {
            Camino camino = new Camino();
            camino.setLista((ArrayList) this.getLista().clone());
            camino.setCosto(this.getCosto());
            return camino;
        }

        @Override
        public String toString() {
            return costo + "";
        }
    }
}
