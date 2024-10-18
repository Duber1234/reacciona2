package com.example.reacciona2;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsIntent;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.io.IOException;

public class conexion {
    public Intent openInChrome(String url,PackageManager pm) {
        String chromePackageName = "com.android.chrome";


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage(chromePackageName);  // Establece el paquete de Chrome
        return intent;

        }
    public void openChromeCustomTab(String url, Context c) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();

        // Personalizar el color de la barra de herramientas (opcional)

        //builder.setToolbarColor(c.getResources().getColor(android.R.color.transparent));
        builder.setToolbarCornerRadiusDp(1);
        // Crear la intención CustomTab
        CustomTabsIntent customTabsIntent = builder.build();

        // Abrir la URL en Chrome Custom Tabs

        //customTabsIntent.launchUrl(c, Uri.parse(url));


        new Thread(new Runnable() {
            @Override
            public void run() {
                // Llamar al método makeRequest
                //final String response = makeRequest(url);

                // Si necesitas actualizar la UI (que solo puede ser manipulada en el hilo principal)
                // Usa un Handler para enviar los resultados al hilo principal (UI thread)
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // Aquí puedes actualizar la UI con la respuesta
                        // Por ejemplo, mostrar la respuesta en un TextView
                        customTabsIntent.launchUrl(c, Uri.parse(url));




                    }
                });

            }
        }).start();
    }
}

