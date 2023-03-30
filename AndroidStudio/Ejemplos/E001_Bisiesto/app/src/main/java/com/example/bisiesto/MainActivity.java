package com.example.bisiesto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout mycl;
    Switch myswitchFondo;
    Button mybtnAleatorio, mybtnVerificar, mybtnExit;
    TextView mytvAleatorio, mytvVerificar;
    RadioButton myyes, myno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mycl = (ConstraintLayout) findViewById(R.id.cl);
        myswitchFondo = (Switch) findViewById(R.id.switchFondo);

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

    // switch
    // para pillarlo en el onClick hay que pasarle View v a la funcion
    // hay que especificar la funcion en el onClick del switch
    public void fondoAmarillo(View v) {
        myswitchFondo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (myswitchFondo.isChecked()) {
                    mycl.setBackgroundColor(Color.parseColor("#f1f885"));
                } else {
                    mycl.setBackgroundColor(Color.WHITE);
                }
            }
        });
    }

    // crear un aleatorio
    public void aleatorio(View v) {
        int aleatorio;

        // int nRandom = (int) (Math.random() * (max - min + 1) + min);
        // 1582 es el primer year del calendario gregoriano
        aleatorio = (int) (Math.random() * (6900 - 1582 + 1) + 1582);
        mytvAleatorio.setText(String.valueOf(aleatorio));
    }


    // https://developer.android.com/guide/topics/ui/controls/radiobutton?hl=es-419#java
    // https://www.develou.com/radiobutton-android/
    // verificar si es bisiesto o no sin necesidad de poner la funcion en el onClick del boton
    public void verificar() {

        mybtnVerificar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String strAleatorio = String.valueOf(mytvAleatorio.getText());

                // comprobar si no esta vacio el aleatorio, si existe
                if (!strAleatorio.isEmpty()) {

                    int random = Integer.parseInt(strAleatorio);

                    // comprobar si hay un numero en el aleatorio
                    if (Double.parseDouble(strAleatorio) > 0) {

                        if (!myyes.isChecked() && !myno.isChecked()) {
                            mytvVerificar.setText("marca un radio button");
                            mytvVerificar.setTextColor(Color.BLUE);
                        } else {
                            if (myyes.isChecked()) {

                                if ((random % 4 == 0) && ((random % 100 != 0) || (random % 400 == 0))) {
                                    mytvVerificar.setText("VERDADERO. Es Bisiesto");
                                    mytvVerificar.setTextColor(Color.GREEN);

                                } else {
                                    mytvVerificar.setText("FALSO. No Bisiesto");
                                    mytvVerificar.setTextColor(Color.RED);
                                }
                            }

                            if (myno.isChecked()) {
                                if ((random % 4 != 0) && ((random % 100 == 0) || (random % 400 != 0))) {
                                    mytvVerificar.setText("VERDADERO. No Bisiesto");
                                    mytvVerificar.setTextColor(Color.GREEN);
                                } else {
                                    mytvVerificar.setText("FALSO. Es Bisiesto");
                                    mytvVerificar.setTextColor(Color.RED);
                                }

                            }
                        }
                    }

                } else {
                    mytvVerificar.setText("genera un aleatorio");
                }
            }
        });
    }


    // exit app
    public void salir(View v) {
        System.exit(0);
    }

}