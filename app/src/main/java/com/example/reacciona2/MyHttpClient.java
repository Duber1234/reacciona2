package com.example.reacciona2;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class MyHttpClient {

    private OkHttpClient client;
    private WebView webView;


    public MyHttpClient(WebView webView) {
        this.webView = webView;
        client = new OkHttpClient();
    }
    public MyHttpClient() {
        client = new OkHttpClient();
    }

    public String makeRequest(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }
    // Método para consumir en un hilo
    public void executeRequestInThread(final String url,String tipo) {
        // Crear un hilo nuevo
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Llamar al método makeRequest
                    final String response = makeRequest(url);

                    // Si necesitas actualizar la UI (que solo puede ser manipulada en el hilo principal)
                    // Usa un Handler para enviar los resultados al hilo principal (UI thread)
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // Aquí puedes actualizar la UI con la respuesta
                            // Por ejemplo, mostrar la respuesta en un TextView

                            //m1(response);
                            System.out.println("Respuesta: " + response);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejar la excepción si algo sale mal
                }
            }
        }).start();  // Iniciar el hilo
    }
    // Método para consumir en un hilo
    public void executeRequestInThreadSend(final String url) {
        // Crear un hilo nuevo
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Llamar al método makeRequest
                    final String response = makeRequest(url);

                    // Si necesitas actualizar la UI (que solo puede ser manipulada en el hilo principal)
                    // Usa un Handler para enviar los resultados al hilo principal (UI thread)
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            // Aquí puedes actualizar la UI con la respuesta
                            // Por ejemplo, mostrar la respuesta en un TextView

                            //m1(response);
                           // System.out.println("Respuesta: " + response);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("error 1");
                    // Manejar la excepción si algo sale mal
                }
            }
        }).start();  // Iniciar el hilo
    }
    public void m1(String datos){






        //webView.loadUrl("https://www.youtube.com/embed/tu_video_id");

        if (Build.VERSION.SDK_INT >= 19) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        webView.setWebViewClient(new WebViewClient());
        //webView.loadData(datos,"text/html","UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        //webView.setWebChromeClient(new WebChromeClient());
        //webView.setWebViewClient(new WebViewClient());

        webView.loadDataWithBaseURL("https://www.reacciona.in", datos, "text/html", "UTF-8", null);

    }
}