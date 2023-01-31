package com.example.mensajesusuario;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


// 31/01/2023
// /media/alejandro/METAL SANDISK/IES CLARA/Moviles/AndroidProjects/AndroidStudio/MensajesUsuario
// /app/build.gradle
// cambiar el 32 por el 33

public class MainActivity extends AppCompatActivity {

    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // Boton Toast
    public void verToast(View view) {

        LayoutInflater myInflter = getLayoutInflater();
        View myLayout = myInflter.inflate(R.layout.layout_toast, null);
        Toast myToast = new Toast(getApplicationContext());
        myToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);
        myToast.show();

        /*
        Context context = getApplicationContext();
        String text = "Hello toast!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        */
    }



    // Boton Alerta
    public void myAlert(View vista){
        AlertDialog.Builder MyAlert = new AlertDialog.Builder(this);

        MyAlert.setTitle("CLASE PMDM DAW+DAM");
        MyAlert.setMessage("Aprendiendo a poner AlertDialog");


        // al dar al boton perfecto llama a paquete y meteme a patricio2
        MyAlert.setPositiveButton("Perfecto", null);
        MyAlert.setNegativeButton("No me entero", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                paquete();
            }
        });
        MyAlert.setNeutralButton("A ver si ahora", null);


        AlertDialog dialog = MyAlert.create();

        dialog.show();
    }

    private void paquete(){
        myImageView = findViewById(R.id.iv1);
        myImageView.setImageResource( R.drawable.patricio2);
    }



    // Boton alerta de Futbol
    // /media/alejandro/METAL SANDISK/IES CLARA/Moviles/AndroidProjects/AndroidStudio/MensajesUsuario
    // /app/src/main/res/values/strings.xml
    public void myAlertFutbol(View vista){
        AlertDialog.Builder MyAlert = new AlertDialog.Builder(this);

        MyAlert.setTitle("CLASE PMDM DAW+DAM");


        // al dar al boton perfecto llama a paquete y meteme a patricio2
        MyAlert.setItems(R.array.deportes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // i indica la posición del array clickeada
                pinta_deporte(i);
            }
        });

        AlertDialog dialog = MyAlert.create();
        dialog.show();
    }


    private void pinta_deporte(int i){
        Context context = getApplicationContext();
        String text = null;

        switch (i){
            case 0:{
                text = "Opcion 1 - Futbol";
                break;
            }

            case 1:{
                text = "Opcion 2 - Futbolin";
                break;
            }

            case 2:{
                text = "Opcion 3 - Futbol sala";
                break;
            }

            case 3:{
                text = "Opcion 4 - Futbol 7";
                break;
            }
        }

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }






}
