package com.example.animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewAnimator;

// UT2.8 Material Design, Animaciones y GIFS
// 01/02/2023
// Ver code de activity_main.xml
// /media/alumno/METAL SANDISK/IES CLARA/Moviles/AndroidProjects/AndroidStudio/animaciones/app/src/main/res/layout/activity_main.xml

public class MainActivity extends AppCompatActivity {

    ImageView myiV1;
    AnimationDrawable miTragaperras ;
    ImageView myImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // el arranque no se puede hacer en el oncreate, hay que hacerlo en el onstart
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myiV1 = (ImageView) findViewById(R.id.iV1);
        myiV1.setBackgroundResource(R.drawable.tragaperras);
        miTragaperras = (AnimationDrawable) myiV1.getBackground();
    }

    // el arranque no se puede hacer en el oncreate, hay que hacerlo en el onstart
    protected void onStart() {
        super.onStart();
        miTragaperras .start();
    };

    // todos los metodos que vayas a llamar desde un control deben de recibir un View v
    public void parar (View v){
        miTragaperras.stop();
    }
}