package com.example.reacciona2;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class consumir_web_service {

    private RequestQueue queue;

    public void fetchData(String datos,RequestQueue queue) {
        // URL del servicio web que deseas consumir
        String url = "https://reacciona.in/"+datos;

        // Crear una solicitud JSON
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try
                        {
                            // Suponiendo que la respuesta contiene un campo llamado "message"
                            String message = response.getString("message");
                            System.out.println(message);

                        } catch (JSONException e) {
                            Log.e("Volley", "Error al parsear el JSON: " + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores
                        Log.e("Volley", "Error de red: " + error.getMessage());

                    }
                });

        // Agregar la solicitud a la cola

        queue.add(jsonObjectRequest);
    }
}
