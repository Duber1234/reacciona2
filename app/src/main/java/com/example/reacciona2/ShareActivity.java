package com.example.reacciona2;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ShareActivity extends AppCompatActivity {
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el intent que lanzó la actividad
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        // Verificar que la acción sea SEND y el tipo de datos sea texto
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Manejar texto compartido
            }
        }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Procesa el texto compartido (en este caso, un enlace de YouTube)
            Log.d("SharedText", sharedText);
            consumir_web_service cn=new consumir_web_service();
            queue = Volley.newRequestQueue(this);
            cn.fetchData("welcome/publicar2?share="+sharedText,queue);
            // Ahora redirige al MainActivity
            Intent mainIntent = new Intent(this, MainActivity.class);

            // Puedes pasar datos a MainActivity si lo necesitas
            mainIntent.putExtra("SHARED_TEXT", sharedText);

            // Cierra la actividad actual para "refrescar" la app
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            // Inicia la MainActivity
            startActivity(mainIntent);

            // Finaliza la actividad actual para que no quede en la pila
            finish();
        }
    }
}