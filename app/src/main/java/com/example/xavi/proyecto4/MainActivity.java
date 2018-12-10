package com.example.xavi.proyecto4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.xavi.proyecto4.webServices.Nodos;
import com.example.xavi.proyecto4.webServices.NodosWeb;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner spin_origen;
    private Spinner spin_destino;
    private Button btn_consultar;
    private Button btn_actualizar;

    private String nodoOrigen;
    private String nodoDestino;

    NodosWeb nodosWebs = new NodosWeb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin_origen = findViewById(R.id.spin_origen);
        spin_destino = findViewById(R.id.spin_destino);
        btn_consultar = findViewById(R.id.btn_consultar);
        btn_actualizar = findViewById(R.id.btn_actualizar);

        btn_consultar.setOnClickListener(this);
        btn_actualizar.setOnClickListener(this);
        cargarSpiner();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_consultar:
                int pos = spin_origen.getSelectedItemPosition();
                int pos2 = spin_destino.getSelectedItemPosition();

                if (pos != -1 || pos2 != -1) {
                    if (spin_origen.getSelectedItem().toString().trim() != spin_destino.getSelectedItem().toString().trim()) {
                        nodosWebs.rutaEconomica(getNodoOrigen(), getNodoDestino());
                        nodosWebs.rutaCorta(getNodoOrigen(), getNodoDestino());
                        nodosWebs.Precio();
                        nodosWebs.Tiempo();
                        Intent inten = new Intent(MainActivity.this, Detalle_Activity.class);
                        startActivity(inten);
                    }else
                    {
                        Toast.makeText(MainActivity.this, "Error: El destino tiene que ser diferente al origen", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_actualizar:
                cargarSpiner();
                break;
        }
    }

    private void cargarSpiner() {

        Nodos.getInstancia().getLlaves().clear();
        final ArrayList<String> nodo = nodosWebs.nodos();
        if (nodo != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nodo);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_origen.setAdapter(adapter);

            spin_origen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setNodoOrigen(nodo.get(position));
                    Log.i(parent.toString(), nodo.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nodo);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spin_destino.setAdapter(adapter2);

            spin_destino.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setNodoDestino(nodo.get(position));
                    Log.i(parent.toString(), nodo.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        } else {
            String hola = "holaaaa";
        }


    }


    public String getNodoOrigen() {
        return nodoOrigen;
    }

    public void setNodoOrigen(String nodoOrigen) {
        this.nodoOrigen = nodoOrigen;
    }

    public String getNodoDestino() {
        return nodoDestino;
    }

    public void setNodoDestino(String nodoDestino) {
        this.nodoDestino = nodoDestino;
    }
}
