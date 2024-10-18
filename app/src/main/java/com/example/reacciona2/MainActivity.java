package com.example.reacciona2;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#FFFFFF")); // Cambiar el color a negro
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }



        /* webView.loadUrl("https://www.reacciona.in");
        String vd="<iframe width='560' height='315' src='https://www.youtube.com/embed/ugzB87QzIK0?si=BlpACYWzcyVcR2cx' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share' referrerpolicy='strict-origin-when-cross-origin' allowfullscreen></iframe>";
        webView.loadData(vd,"text/html","UTF-8");
       // webView.loadData(vd,"text/html","UTF-8");
*/
        //WebView webView = (WebView) findViewById(R.id.wv_main);

        /*MyHttpClient myHttpClient=new MyHttpClient(webView);
        String url = "https://www.reacciona.in";
        myHttpClient.executeRequestInThread(url);*/
        conexion myHttpClient=new conexion();
        String url = "https://www.reacciona.in";
        //getSupportActionBar().hide();

        //startActivity( myHttpClient.openInChrome(url,getPackageManager()));
        myHttpClient.openChromeCustomTab(url,this);

//webView.loadData(result,"text/html","UTF-8");
    }

    }
