package com.example.e013_hilos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

// 24/04/2023
// para hacer una select a un servidor para una bbdd tendrias que hacer esto

public class MainActivity extends AppCompatActivity {

    ProgressBar pB1;
    private MiTareaAsincrona tarea1;

    // 4/5/2023
    private ProgressDialog pDialog;
    private MiTareaAsincronaDialog tarea2;


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


    public void botonconhilos1(View vista){
        Contador micontador = new Contador(pB1, 10);
        micontador.start();
    }


    public void botonconhilos(View vista){
        new Thread(new Runnable() {
            @Override
            public void run() {
                pB1.post(new Runnable() {
                    @Override
                    public void run() {
                        pB1.setProgress(0);
                    }
                });

                for(int i=1; i<=10; i++){
                    tareaLarga();
                    pB1.post(new Runnable() {
                        @Override
                        public void run() {
                            pB1.incrementProgressBy(10);
                        }
                    });
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    // 25/04/2023
    public void boton_tarea_async(View vista){
        tarea1 = new MiTareaAsincrona();
        tarea1.execute();
    }

    // 25/04/2023
    public void canc_tarea_async(View vista){
        tarea1.cancel(true);
    }


    // 4/5/2023
    public void boton_tarea_async_dialog(View vista){
        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pDialog.setMessage("Procesando...");
        pDialog.setCancelable(true);
        pDialog.setMax(100);

        tarea2 = new MiTareaAsincronaDialog();
        tarea2.execute();
    }



    // 25/04/2023
    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean>{
        @Override
        // (Void... params) --> tipo de parametro que vas a recibir
        protected Boolean doInBackground(Void... params){
            for (int i=1; i<=10; i++){
                tareaLarga();
                publishProgress(i*10);
                if(isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            int progreso = values[0].intValue();
            pB1.setProgress(progreso);
        }

        @Override
        protected void onPreExecute(){
            pB1.setMax(100);
            pB1.setProgress(0);
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onCancelled(){
            Toast.makeText(MainActivity.this, "Tarea cancelada", Toast.LENGTH_SHORT).show();
        }
    }



    // 4/5/2023
    private class MiTareaAsincronaDialog extends AsyncTask<Void, Integer, Boolean>{
        @Override
        // (Void... params) --> tipo de parametro que vas a recibir
        protected Boolean doInBackground(Void... params){
            for (int i=1; i<=10; i++){
                tareaLarga();
                publishProgress(i*10);
                if(isCancelled()){
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            int progreso = values[0].intValue();
            pDialog.setProgress(progreso);
        }

        @Override
        protected void onPreExecute(){
            pDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    MiTareaAsincronaDialog.this.cancel(true);
                }
            });
            pDialog.setProgress(0);
            pDialog.show();
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                pDialog.dismiss();
                Toast.makeText(MainActivity.this, "Tarea finalizada", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        protected void onCancelled(){
            Toast.makeText(MainActivity.this, "Tarea cancelada", Toast.LENGTH_SHORT).show();
        }
    }
}