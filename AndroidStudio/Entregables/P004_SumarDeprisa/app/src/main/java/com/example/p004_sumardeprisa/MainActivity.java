package com.example.p004_sumardeprisa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

// 4/05/2023
// Sumando Deprisa

// Para poner el boton rojo ha sido un lio, ver themes

public class MainActivity extends AppCompatActivity {

    Button btnComenzar;
    EditText edtSegundos;
    TextView tvMensajeUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComenzar = findViewById(R.id.btnComenzar);
        edtSegundos = findViewById(R.id.edtSegundos);
        tvMensajeUsuario = findViewById(R.id.tvMensajeUsuario);

        btnComenzar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String strSegundos = edtSegundos.getText().toString();
                 int segundos;
                 boolean esNumero = false;

                 if(strSegundos.isEmpty()){
                     Toast.makeText(MainActivity.this, "Introduzca un numero entre 1 - 60", Toast.LENGTH_SHORT).show();
                 }

                 if(!esNumero){
                     try{
                         segundos = Integer.parseInt(strSegundos);

                         if(segundos<1 || segundos>60){
                             Toast.makeText(MainActivity.this, "Introduzca un numero entre 1 - 60", Toast.LENGTH_SHORT).show();
                         }
                         else{
                             esNumero = true;
                             /*
                             An intent is an abstract description of an operation to be performed. It can be
                             used with startActivity to launch an android.app.Activity, broadcastIntent to send
                             it to any interested BroadcastReceiver components, and Context.startService or
                             Context.bindService to communicate with a background android.app.Service.
                             An Intent provides a facility for performing late runtime binding between the code
                             in different applications. Its most significant use is in the launching of
                             activities, where it can be thought of as the glue between activities. It is
                             basically a passive data structure holding an abstract description of an action to
                             be performed.
                             */

                             Intent intent = new Intent(MainActivity.this, SumaActivity.class);
                             intent.putExtra("segundos", segundos);
                             // hay que usar el deprecated, si usas startActivity a secas no consigo saber
                             // cual es el metodo parejo, porque esta claro que no es onActivityResult()
                             startActivityForResult(intent, 1);

                         }

                     // Valor no numerico
                     } catch (NumberFormatException e){
                         Toast.makeText(MainActivity.this, "Introduzca un numero entre 1 - 60", Toast.LENGTH_SHORT).show();
                     }
                 }

             }

        });

    }

    // Recoger el mensaje que nos mandan de vuelta
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String mensaje = data.getStringExtra("mensaje");
            tvMensajeUsuario.setText(mensaje);

            // Agrega esta linea para comprobar si el mensaje esta llegando correctamente
            Log.d("MainActivity", "Mensaje recibido: " + mensaje);
        }

        // tvMensajeUsuario.setText("Hola carabola");

    }

}