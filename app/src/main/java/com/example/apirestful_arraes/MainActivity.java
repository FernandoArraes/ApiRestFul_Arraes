package com.example.apirestful_arraes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Mostrar (View view) {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://uealecpeterson.net/turismo/lugar_turistico/json_getlistadoGrid",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    public void btlimpiar (View view){


        TextView consulta = (TextView)findViewById(R.id.textView3);
        consulta.setText("");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        String lstCategoria="";
        String lstNombre="";
        String lstTelf="";
        JSONArray listaJson = new JSONArray(result);

        for (int i=0; i<listaJson.length();i++)
        {
            JSONObject info=listaJson.getJSONObject(i);
            lstCategoria=lstCategoria+"\n"+info.getString("category").toString();
            lstTelf=lstTelf+"\n"+info.getString("phone").toString();

        }

        TextView consulta = (TextView)findViewById(R.id.textView3);
        consulta.setText("======>Categoria<======"+"\n"
                +lstCategoria+"\n"+"\n"+"======>Nombre<======"+"\n"+lstNombre
                +"\n"+"\n"+"======>Telefono<======"+"\n"+lstTelf);

    }
}