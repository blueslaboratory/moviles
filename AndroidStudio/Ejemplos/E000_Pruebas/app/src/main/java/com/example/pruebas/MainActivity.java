/*
10/01/2023

layout
D:\AndroidStudioProjects\app\src\main\res\layout\activity_main.xml

 */

package com.example.pruebas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 11/01/2023
    EditText myedt1, myedt2;
    TextView mytv3;
    Button mybt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hay una clase R que contiene todos los objetos de tu presentacion
        myedt1 = (EditText) findViewById(R.id.edt1);
        myedt2 = (EditText) findViewById(R.id.edt2);
        mytv3 = (TextView) findViewById(R.id.tv);

        mybt1 = (Button) findViewById(R.id.btn);


        // Cuando le das al boton un rato te setea el texto a vacio
        // (escuchador largo: pendiente a algo)
        mybt1.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mytv3.setText("");
                return true;
            }
        });

        //sumarSinOnClick();
    }

    // En activity_main.xml meter la funcion sumar en el onClick
    public void sumar(View v){
        Double numero1, numero2, numero3;
        String numero;

        numero = myedt1.getText().toString(); // siempre hay que hacer el toString
        numero1 = Double.parseDouble(numero);

        numero = myedt2.getText().toString(); // siempre hay que hacer el toString
        numero2 = Double.parseDouble(numero);

        numero3 = numero1 + numero2;
        mytv3.setText(String.valueOf(numero3));
    }



    /*

    // En activity_main.xml no hace falta meter la funcion sumar en el onClick
    public void sumarSinOnClick(){
        mybt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double numero1, numero2, numero3;
                String numero;

                numero = myedt1.getText().toString(); // siempre hay que hacer el toString
                numero1 = Double.parseDouble(numero);

                numero = myedt2.getText().toString(); // siempre hay que hacer el toString
                numero2 = Double.parseDouble(numero);

                numero3 = numero1 + numero2;
                mytv3.setText(String.valueOf(numero3));
            }
        });
    }

    */


    /*
    // 10/01/2023
    TextView mytV1;
    Button mybt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // casteando sobre mi clase el objeto grafico:
        // R: especie de clase que contiene todos los objetos de todas las partes graficas de tu aplicacion
        mytV1 = (TextView) findViewById(R.id.edt1);
        mybt1 = (Button) findViewById(R.id.bt1);


        // Cuando le das al boton te setea el texto (escuchador: pendiente a algo)
        mybt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytV1.setText("ADIOS MUNDO CRUEL");
            }
        });

        // Cuando le das al boton te setea el texto (escuchador largo: pendiente a algo)
        mybt1.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        });

    }
    */


    /*
    public void adios(View v){
        mytV1.setText("ADIOS MUNDO CRUEL");
    }
    */

}