package com.example.e013_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

// 24/04/2023
// para hacer una select a un servidor para una bbdd tendrias que hacer esto

public class MainActivity extends AppCompatActivity {

    ProgressBar pB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pB1 = findViewById(R.id.pB1);
    }

    public void botonsinhilos(View vista){
        pB1.setMax(100);
        pB1.setProgress(0);

        for (int i=1; i<=10; i++){
            tareaLarga();
            pB1.incrementProgressBy(10);

        }

        Toast.makeText(this, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
    }

    private void tareaLarga(){
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
    }

    public void hola(View vista){
        Toast.makeText(this, "Hola mundo!", Toast.LENGTH_LONG).show();
    }

    public void botonconhilos(View vista){
        Contador micontador = new Contador(pB1, 10);
        micontador.start();
    }


}