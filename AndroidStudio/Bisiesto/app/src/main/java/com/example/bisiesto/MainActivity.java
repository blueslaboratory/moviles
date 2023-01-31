package com.example.bisiesto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout mycl;
    Switch switchFondo;
    Button mybtnAleatorio, mybtnVerificar, mybtnExit;
    TextView mytvAleatorio, mytvVerificar;
    RadioButton myyes, myno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mycl = (ConstraintLayout) findViewById(R.id.cl);
        switchFondo = (Switch) findViewById(R.id.switchFondo);

        mytvAleatorio = (TextView) findViewById(R.id.tvAleatorio);
        mytvVerificar = (TextView) findViewById(R.id.tvVerificar);

        mybtnAleatorio = (Button) findViewById(R.id.btnAleatorio);
        mybtnVerificar = (Button) findViewById(R.id.btnVerificar);
        mybtnExit = (Button) findViewById(R.id.btnExit);

        myyes = (RadioButton) findViewById(R.id.yes);
        myno = (RadioButton) findViewById(R.id.no);


        // verificar si es bisiesto o no
        verificar();



    }


    public void fondoAmarillo(){
        switchFondo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(switchFondo.isChecked()){

                    mycl.setBackgroundColor(Color.parseColor("#f1f885"));
                }
                else{
                    mycl.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    // crear un aleatorio
    public void aleatorio(View v){
        Integer aleatorio;

        // int nRandom = (int) (Math.random() * (max - min + 1) + min);
        aleatorio = (int) (Math.random() * (3000 - 0 + 1) + 0);
        mytvAleatorio.setText(String.valueOf(aleatorio));
    }


    // https://developer.android.com/guide/topics/ui/controls/radiobutton?hl=es-419#java
    // https://www.develou.com/radiobutton-android/
    // verificar si es aleatorio o no
    public void verificar(){

        mybtnVerificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String strAleatorio = String.valueOf(mytvAleatorio.getText());
                int random = Integer.parseInt(strAleatorio);

                if (!strAleatorio.isEmpty()) {
                    boolean check;
                    if (!myyes.isChecked() && !myno.isChecked()) {
                        mytvVerificar.setText("marca un radio button");
                    } else {
                        if (myyes.isChecked()) {

                            if ((random % 4 == 0) && ((random % 100 != 0) || (random % 400 == 0))) {
                                mytvVerificar.setText("VERDADERO. Es Bisiesto");
                            } else {
                                mytvVerificar.setText("FALSO. No Bisiesto");
                            }
                        }

                        if (myno.isChecked()) {
                            if (!(random % 4 == 0) && ((random % 100 != 0) || (random % 400 == 0))) {
                                mytvVerificar.setText("VERDADERO. No Bisiesto");
                            } else {
                                mytvVerificar.setText("FALSO. Es Bisiesto");
                            }

                        }
                    }
                }
            }
        });
    }


    // exit app
    public void salir(View v){
        System.exit(0);
    }

}