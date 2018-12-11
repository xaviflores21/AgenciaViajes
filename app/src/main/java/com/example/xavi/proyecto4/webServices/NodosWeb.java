package com.example.xavi.proyecto4.webServices;

import android.util.Log;

import com.example.xavi.proyecto4.httpclient.HttpConnection;
import com.example.xavi.proyecto4.httpclient.MethodType;
import com.example.xavi.proyecto4.httpclient.StandarRequestConfiguration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Hashtable;

public class NodosWeb {

    private String url = "http://192.168.2.20:8080/Proyecto4/admin/adminController";

    public ArrayList<String> nodos() {
        Hashtable<String, String> params = new Hashtable<>();
        params.put("accion", "listado");
        String result = HttpConnection.sendRequest(new StandarRequestConfiguration(url, MethodType.POST, params));
        if (result != null){
            try {

                JSONArray array = new JSONArray(result);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    String key = "" + obj.getString("key");
                    Nodos.getInstancia().AddKey(key);
                }
            } catch (JSONException e) {
                Log.e(e.toString(), "informacion: error al obtener la lista");
            }
        }else
        {
            Log.e("Error","No se pudo conectar con el Servidor");
        }

        return Nodos.getInstancia().getLlaves();
    }

    public ArrayList<String> rutaEconomica(String origen, String destino) {
        Nodos.getInstancia().getRutaEconomica().clear();
        Hashtable<String, String> params = new Hashtable<>();
        params.put("accion", "rutaEconomica");
        params.put("origen", origen);
        params.put("destino", destino);
        String result = HttpConnection.sendRequest(new StandarRequestConfiguration(url, MethodType.POST, params));
        try {
            JSONArray array = new JSONArray(result);
            System.out.println("informacion: jsonarray mide: " + array.length());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String key = "" + obj.getString("key");
                System.out.println("informacion: " + key);
                Nodos.getInstancia().AddRutaEconomica(key);
            }
        } catch (JSONException e) {
            Log.e(e.toString(), "informacion: error al obtener la lista RUTA BARATA");
        }
        return Nodos.getInstancia().getRutaEconomica();
    }

    public ArrayList<String> rutaCorta(String origen, String destino) {
        Nodos.getInstancia().getRutaCorta().clear();
        Hashtable<String, String> params = new Hashtable<>();
        params.put("accion", "rutaCorta");
        params.put("origen", origen);
        params.put("destino", destino);
        String result = HttpConnection.sendRequest(new StandarRequestConfiguration(url, MethodType.POST, params));
        try {
            JSONArray array = new JSONArray(result);
            System.out.println("jsonarray mide: " + array.length());
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String key = "" + obj.getString("key");
                Nodos nodos = Nodos.getInstancia();
                nodos.AddRutaCorta(key);
            }
        } catch (JSONException e) {
            Log.e(e.toString(), "informacion: error al obtener la lista");
        }
        return Nodos.getInstancia().getRutaCorta();
    }

    public int Precio() {
        Hashtable<String, String> params = new Hashtable<>();
        params.put("accion", "costo");
        String result = HttpConnection.sendRequest(new StandarRequestConfiguration(url, MethodType.POST, params));
        try {
            JSONObject obj = new JSONObject(result);
            int costo = obj.getInt("key");
            Nodos.getInstancia().setPrecio(costo);

        } catch (JSONException e) {
            Log.e(e.toString(), "informacion: error al obtener la lista");
        }
        return Nodos.getInstancia().getPrecio();
    }

    public int Tiempo() {
        Hashtable<String, String> params = new Hashtable<>();
        params.put("accion", "tiempo");
        String result = HttpConnection.sendRequest(new StandarRequestConfiguration(url, MethodType.POST, params));
        try {
            JSONObject obj = new JSONObject(result);
            int dist = obj.getInt("key");
            Nodos.getInstancia().setTiempo(dist);
        } catch (JSONException e) {
            Log.e(e.toString(), "informacion: error al obtener la lista");
        }
        return Nodos.getInstancia().getTiempo();
    }

}
