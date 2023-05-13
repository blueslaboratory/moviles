package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.mammamia.R;

public class Privacidad extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacidad);

        webView = (WebView) findViewById(R.id.webViewActivityPrivacidad);

        // El archivo y la carpeta que estamos cargando son: /assets/privacidad.html
        webView.loadUrl("file:///android_asset/privacidad.html");

    }
}