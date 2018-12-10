package com.example.xavi.proyecto4.webServices;

import java.util.ArrayList;

public class Nodos {

    private int precio = 0;
    private int tiempo = 0;
    public static Nodos instancia;

    private ArrayList<String> llaves = new ArrayList<>();
    private ArrayList<String> rutaCorta = new ArrayList<>();
    private ArrayList<String> rutaEconomica = new ArrayList<>();

    protected Nodos() {

    }

    public void AddKey(String key) {

        llaves.add(key);
    }

    public void AddRutaCorta(String key) {

        rutaCorta.add(key);
    }

    public void AddRutaEconomica(String key) {

        rutaEconomica.add(key);
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public static Nodos getInstancia() {
        if (instancia==null)
            instancia = new Nodos();
        return instancia;
    }

    public static void setInstancia(Nodos instancia) {
        Nodos.instancia = instancia;
    }

    public ArrayList<String> getLlaves() {
        return llaves;
    }

    public void setLlaves(ArrayList<String> llaves) {
        this.llaves = llaves;
    }

    public ArrayList<String> getRutaCorta() {
        return rutaCorta;
    }

    public void setRutaCorta(ArrayList<String> rutaCorta) {
        this.rutaCorta = rutaCorta;
    }

    public ArrayList<String> getRutaEconomica() {
        return rutaEconomica;
    }

    public void setRutaEconomica(ArrayList<String> rutaEconomica) {
        this.rutaEconomica = rutaEconomica;
    }
}
