package com.example.p001_calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// 07/02/2023
// Error: puedes poner varios puntos, no se valida numero por numero al darle al boton de operacion
// dar a = da error
// dar a = da error

// Titulos de videos de calculadoras de youtube:
// How to Create Calculator in Java NetBeans Full Tutorial
// How to Create Calculator in Eclipse with Java Program

public class MainActivity extends AppCompatActivity {

    TextView mytvOperation;

    Button mybtnC, mybtnDEL, mybtnDiv;

    Button mybtn7, mybtn8, mybtn9, mybtnMultiply;
    Button mybtn4, mybtn5, mybtn6, mybtnSum;
    Button mybtn1, mybtn2, mybtn3, mybtnSubstract;

    Button mybtn0, mybtnPoint, mybtnEqual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hay una clase R que contiene todos los objetos de tu presentacion
        // I/O (Input/Output)
        mytvOperation = (TextView) findViewById(R.id.tvOperation);

        // Special buttons
        mybtnDEL = (Button) findViewById(R.id.btnDEL);
        mybtnC = (Button) findViewById(R.id.btnC);

        // Operations
        mybtnMultiply = (Button) findViewById(R.id.btnMultiply);
        mybtnDiv = (Button) findViewById(R.id.btnDiv);
        mybtnSum = (Button) findViewById(R.id.btnSum);
        mybtnSubstract = (Button) findViewById(R.id.btnSubstract);
        mybtnEqual = (Button) findViewById(R.id.btnEqual);

        // Number buttons
        mybtn0 = (Button) findViewById(R.id.btn0);
        mybtn1 = (Button) findViewById(R.id.btn1);
        mybtn2 = (Button) findViewById(R.id.btn2);
        mybtn3 = (Button) findViewById(R.id.btn3);
        mybtn4 = (Button) findViewById(R.id.btn4);
        mybtn5 = (Button) findViewById(R.id.btn5);
        mybtn6 = (Button) findViewById(R.id.btn6);
        mybtn7 = (Button) findViewById(R.id.btn7);
        mybtn8 = (Button) findViewById(R.id.btn8);
        mybtn9 = (Button) findViewById(R.id.btn9);
        mybtnPoint = (Button) findViewById(R.id.btnPoint);


        // Inicializando
        try {
            Thread.sleep(3000);
            mytvOperation.setText("");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // boton C (clear)
        mybtnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytvOperation.setText("");
            }
        });

        // boton DEL (delete last char)
        mybtnDEL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                input = input.substring(0, input.length()-1);
                mytvOperation.setText(input);
            }
        });


        loadOperationButtons();
        loadNumberButtons();

        // Simbolos
        // boton =
        mybtnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();

                try {
                    Thread.sleep(500);
                    mytvOperation.setText(operacion(view));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void loadOperationButtons(){
        // Operation buttons
        // boton *
        mybtnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();

                if (input.charAt(input.length()-1) != '.' &&
                    !checkSymbol(input.charAt(input.length()-1))) {
                    mytvOperation.setText(input + "*");
                }
            }
        });
        // boton /
        mybtnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();

                if (input.charAt(input.length()-1) != '.' &&
                    !checkSymbol(input.charAt(input.length()-1))) {
                    mytvOperation.setText(input + "/");
                }
            }
        });
        // boton +
        mybtnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();

                if (input.charAt(input.length()-1) != '.' &&
                    !checkSymbol(input.charAt(input.length()-1))) {
                    mytvOperation.setText(input + "+");
                }
            }
        });
        // boton -
        mybtnSubstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();

                if (input.charAt(input.length()-1) != '.' &&
                    !checkSymbol(input.charAt(input.length()-1))) {
                    mytvOperation.setText(input + "-");
                }
            }
        });
    }

    public void loadNumberButtons(){
        // Number buttons
        // 0
        mybtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "0");
            }
        });
        // 1
        mybtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "1");
            }
        });
        // 2
        mybtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "2");
            }
        });
        // 3
        mybtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "3");
            }
        });
        // 4
        mybtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "4");
            }
        });
        // 5
        mybtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "5");
            }
        });
        // 6
        mybtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "6");
            }
        });
        // 7
        mybtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "7");
            }
        });
        // 8
        mybtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "8");
            }
        });
        // 9
        mybtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                mytvOperation.setText(input + "9");
            }
        });
        // .
        mybtnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mytvOperation.getText().toString();
                if(input.charAt(input.length()-1)!='.' &&
                   !checkSymbol(input.charAt(input.length()-1))) {
                    mytvOperation.setText(input + ".");
                }
            }
        });
    }


    // validar 1 numero con solo 1 .
    // validar 2 numero con solo 1 .
    // proceder a la operacion
    public String operacion(View v){
        char simbolo = ' ';
        String cadOperacion;
        String solucion = "";

        String n1=null;
        String n2=null;
        Double resultado=0.0;
        int posicion = 0;

        // coger el string de la cadena operacion
        cadOperacion = mytvOperation.getText().toString();

        // coger el numero1 y el simbolo
        for(int i=posicion; i<cadOperacion.length(); i++) {
            char c = cadOperacion.charAt(i);
            if(checkNumber(c)) {
                // Si es un numero, lo agregas a una variable temporal
                if(n1 == null) {
                    n1 = Character.toString(c);
                } else {
                    n1 += c;
                }
            } else if(checkSymbol(c)) {
                // Si es un simbolo, lo guardas en la variable simbolo y limpias
                simbolo = c;
                n1 = null;
                posicion = i+1;
            }
        }


        // coger el numero2 y el simbolo
        for(int i=posicion; i<cadOperacion.length(); i++) {
            char c = cadOperacion.charAt(i);
            if(checkNumber(c)) {
                // Si es un numero, lo agregas a una variable temporal
                if(n2 == null) {
                    n2 = Character.toString(c);
                } else {
                    n2 += c;
                }
            } else if(checkSymbol(c)) {
                // Si es un simbolo, lo guardas en la variable simbolo y limpias
                simbolo = c;
                n2 = null;
            }
        }


        // realizar la operacion
        switch(simbolo) {
            case '+':
                resultado = Double.parseDouble(n1) + Double.parseDouble(n2);
                break;
            case '-':
                resultado = Double.parseDouble(n1) - Double.parseDouble(n2);
                break;
            case '*':
                resultado = Double.parseDouble(n1) * Double.parseDouble(n2);
                break;
            case '/':
                resultado = Double.parseDouble(n1) / Double.parseDouble(n2);
                break;
        }


        solucion = resultado.toString();

        return solucion;
    }

    public boolean checkNumber(char c){
        boolean flag = false;

        if(c==1 || c==2 || c==3 ||
           c==4 || c==5 || c==6 ||
           c==7 || c==8 || c==9 ||
           c==0 || c=='.'){
            flag = true;
        }

        return flag;
    }


    public boolean checkSymbol(char c){
        boolean flag = false;

        if(c=='+'|| c=='-' || c=='*' || c=='/' || c=='='){
            flag = true;
        }

        return flag;
    }


    public boolean checkPoint(String number){
        boolean flag=false;

        for(int i=0; i<number.length(); i++) {
            if(number.charAt(i)=='.'){
                flag = true;
            }
        }

        return flag;
    }

}