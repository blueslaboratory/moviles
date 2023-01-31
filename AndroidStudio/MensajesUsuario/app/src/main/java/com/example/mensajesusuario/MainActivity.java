package com.example.mensajesusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


// 31/01/2023
// /media/alejandro/METAL SANDISK/IES CLARA/Moviles/AndroidProjects/AndroidStudio/MensajesUsuario/app/build.gradle
// cambiar el 32 por el 33

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



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
}
