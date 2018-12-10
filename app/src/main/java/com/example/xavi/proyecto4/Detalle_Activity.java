package com.example.xavi.proyecto4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xavi.proyecto4.webServices.Nodos;

import java.util.ArrayList;

public class Detalle_Activity extends AppCompatActivity {

    private TextView text_ruta;
    private TextView text_ruta2;
    private TextView text_tiempo;
    private TextView text_precio;
    private String res = "";
    private String res2 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_);

        text_ruta = findViewById(R.id.text_ruta);
        text_ruta2 = findViewById(R.id.text_ruta2);
        text_tiempo = findViewById(R.id.text_tiempo);
        text_precio = findViewById(R.id.text_precio);

        text_tiempo.setText("Duración: " + Nodos.getInstancia().getTiempo() + " Minutos");
        text_precio.setText("Costo: " + Nodos.getInstancia().getPrecio() + " Bs");


        ArrayList<String> rutaCosto = Nodos.getInstancia().getRutaEconomica();
        if (rutaCosto.size() != 0) {
            for (String ruta : rutaCosto) {
                res += ruta + " - ";
            }
            if (Nodos.getInstancia().getTiempo() == 0){
                text_tiempo.setText("");
            }
            text_ruta.setText(res.substring(0, res.length() - 2));

        } else {
            text_ruta.setText("no existe ruta para este viaje");
            text_tiempo.setText("");
        }

        ArrayList<String> rutaTiempo = Nodos.getInstancia().getRutaEconomica();
        if (rutaTiempo.size() != 0) {
            for (String ruta : rutaTiempo) {
                res2 += ruta + " - ";
            }
            if (Nodos.getInstancia().getPrecio() == 0){
                text_precio.setText("");
            }
            text_ruta2.setText(res2.substring(0, res2.length() - 2));
        }else
        {
            text_ruta2.setText("tiempo no disponible");
            text_precio.setText("");
        }

    }
}
