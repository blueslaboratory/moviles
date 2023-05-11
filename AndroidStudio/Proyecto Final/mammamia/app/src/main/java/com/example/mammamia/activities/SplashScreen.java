package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mammamia.R;

public class SplashScreen extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /*
        Un Handler es una clase que permite programar y ejecutar acciones en un hilo especifico,
        generalmente el hilo principal (tambien conocido como hilo de interfaz de usuario o UI
        thread). Proporciona un mecanismo para enviar y procesar mensajes y Runnable (acciones)
        entre diferentes hilos.

        Lo que este handler hace es mostrar SplashScreen durante 3000ms
        */
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this, Dashboard.class));
            }
        }, 3000);
    }
}