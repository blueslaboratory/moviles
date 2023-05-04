package com.example.p004_sumardeprisa;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SumaActivity extends AppCompatActivity {

    private int segundos;
    private TextView tvn1, tvn2, tvCountDown;
    private int n1, n2;
    private EditText edtResultado;
    private Button btnComprobar, btnSalir;

    private ProgressBar pB;
    private MiTareaAsincrona tareaAsincronaProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);

        // Inicializamos los controles de la interfaz de usuario
        pB = findViewById(R.id.pB);
        tvCountDown = findViewById(R.id.tvCountDown);
        tvn1 = findViewById(R.id.tvNum1);
        tvn2 = findViewById(R.id.tvNum2);

        edtResultado = findViewById(R.id.edtResultado);
        btnComprobar = findViewById(R.id.btnComprobar);
        btnSalir = findViewById(R.id.btnSalir);

        // Obtenemos el numero de segundos de la actividad anterior
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            segundos = extras.getInt("segundos");
        }
        /*
        Intent intent = getIntent();
        segundos = intent.getIntExtra("segundos", 0);
         */

        // Numeros aleatorios
        n1 = aleatorio();
        n2 = aleatorio();

        // Setear los textViews
        tvn1.setText(String.valueOf(n1));
        tvn2.setText(String.valueOf(n2));

        // Iniciar contador de tiempo: tarea asincrona barra de progreso
        tareaAsincronaProgressBar = new MiTareaAsincrona();
        tareaAsincronaProgressBar.execute();


        // Boton de salir
        // Configurar el botón de salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverMensajePrimaria("No era tan difícil, en primaria se aprende a sumar");
            }
        });


    }


    // Click boton Comprobar: Comprobar la respuesta del usuario
    // asignarlo en el onclick de activity_suma
    public void comprobar(View view){
        // Cancelar el contador
        tareaAsincronaProgressBar.cancel(true);

        // Bloquear el EditText de respuesta
        edtResultado.setEnabled(false);

        // Comprobar si la respuesta es correcta
        int respuestaUsuario = respuestaUsuario();
        int resultadoEsperado = n1 + n2;
        String mensaje;

        if(respuestaUsuario == resultadoEsperado){
            mensaje = "No era tan difícil, en primaria se aprende a sumar";
        } else{
            mensaje = "Eres un paquete, vuelve a primaria";
        }

        Toast.makeText(SumaActivity.this, mensaje, Toast.LENGTH_SHORT).show();

        // Devolver el mensaje a la actividad anterior
        Intent intent = new Intent();
        intent.putExtra("mensaje", mensaje);
        setResult(RESULT_OK, intent);
        finish();
    }

    

    // Obtener el valor de la respuesta del usuario
    private int respuestaUsuario(){
        String respuesta = edtResultado.getText().toString();
        int nRespuesta = 0;

        try{
            nRespuesta = Integer.parseInt(respuesta);
        } catch (NumberFormatException e){
            nRespuesta = 0;
        }

        if(respuesta.isEmpty()){
            return 0;
        } else{
            return nRespuesta;
        }
    }

    public void volverMensajePrimaria(String mensaje){
        // Cancelar el contador de tiempo
        tareaAsincronaProgressBar.cancel(true);

        // Devolver el mensaje a la actividad anterior
        Intent intent = new Intent();
        intent.putExtra("mensaje", mensaje);

        // Agrega esta linea para comprobar si el mensaje esta siendo enviado correctamente
        Log.d("SumaActivity", "Mensaje enviado: " + mensaje);

        // Cerrar la actividad y volver a la anterior indicando fallo en la suma
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    // aleatorio entre 100.000-999.999
    private int aleatorio(){
        int max = 999999;
        int min = 100000;
        return (int) (Math.random() * (max - min + 1) + max);
    }



    // sleep 1 segundo
    private void sleep_1seg(){
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
    }


    // Cancelar Tarea Asincrona
    public void canc_tarea_async(View vista){
        tareaAsincronaProgressBar.cancel(true);
    }


    // Por alguna razon es mejor que este aqui y sea private, separarla de la clase es un poco lio
    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {
        @Override
        // (Void... params) --> tipo de parametro que vas a recibir
        protected Boolean doInBackground(Void... params){
            for (int i=segundos; i>=0; i--){
                sleep_1seg();
                publishProgress(i);

                if(isCancelled()){
                    volverMensajePrimaria("No era tan difícil, en primaria se aprende a sumar");
                    break;
                }
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values){
            super.onProgressUpdate(values);
            int progreso = values[0].intValue();
            pB.setProgress(progreso);

            //CountDown
            tvCountDown.setText(String.valueOf(progreso));
        }

        @Override
        protected void onPreExecute(){
            pB.setMax(segundos);
            pB.setProgress(segundos);
            tvCountDown.setText(String.valueOf(segundos));
        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                Toast.makeText(SumaActivity.this, "Se acabo el tiempo", Toast.LENGTH_SHORT).show();
                volverMensajePrimaria("No era tan difícil, en primaria se aprende a sumar");
            }
        }

        @Override
        protected void onCancelled(){
            // Toast.makeText(SumaActivity.this, "Has desertado", Toast.LENGTH_SHORT).show();
            volverMensajePrimaria("No era tan difícil, en primaria se aprende a sumar");
        }

    }
}