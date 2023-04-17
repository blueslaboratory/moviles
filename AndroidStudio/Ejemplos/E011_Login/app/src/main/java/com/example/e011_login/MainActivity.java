package com.example.e011_login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    // 17/04/2023

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void loginConEmail(View vista){

    }



    /*
    // 12/04/2023


    EditText et1;
    String mensaje;

    ActivityResultLauncher<Intent> myActivityResultLauncher;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK) {
                        Intent my_intent_vuelta = result.getData();
                        String mensaje_vuelta = my_intent_vuelta.getStringExtra("extra_vuelta").toString();
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje_vuelta, duration);
                        toast.show();
                    }
                    else if(result.getResultCode() == Activity.RESULT_CANCELED){
                        String mensaje_vuelta = "Sin mensaje de vuelta";
                        Context context = getApplicationContext();
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, mensaje_vuelta, duration);
                        toast.show();
                    }
                }
        });



    }

    // Lanzar el mensaje desde el MainActivity a LoginActivity
    // Bundle: contenedor de parejas clave-valor
    // lo asignamos al boton

    public void lanzar_actividad(View vista){
        et1 = findViewById(R.id.editTextText);
        mensaje = et1.getText().toString();

        Intent my_intent = new Intent(this, LoginActivity.class);

        // pareja clave-valor en el objeto intent:
        my_intent.putExtra("extra_mensaje", mensaje);


        /*
        Tienes que elegir uno de los 2:
        - Si no esperas ningun resultado con startActivity te vale
         */
        // startActivity(my_intent);
    /*
        myActivityResultLauncher.launch(my_intent);
    }

    */

}