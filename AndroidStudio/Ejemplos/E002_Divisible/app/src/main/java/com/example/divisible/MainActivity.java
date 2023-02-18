package com.example.divisible;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button mybtnAleatorio, mybtnComprobar, mybtnExit;
    TextView mytvAleatorio, mytvComprobar;
    CheckBox mycb2, mycb3, mycb5, mycb10, mycbNo;
    ImageView myImageView1, myImageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytvAleatorio = (TextView) findViewById(R.id.tvAleatorio);
        mytvComprobar = (TextView) findViewById(R.id.tvComprobar);

        mybtnAleatorio = (Button) findViewById(R.id.btnAleatorio);
        mybtnComprobar = (Button) findViewById(R.id.btnComprobar);
        mybtnExit = (Button) findViewById(R.id.btnExit);

        mycb2 = (CheckBox) findViewById(R.id.cb2);
        mycb3 = (CheckBox) findViewById(R.id.cb3);
        mycb5 = (CheckBox) findViewById(R.id.cb5);
        mycb10 = (CheckBox) findViewById(R.id.cb10);
        mycbNo = (CheckBox) findViewById(R.id.cbNo);

        myImageView1 = findViewById(R.id.iv1);
        myImageView2 = findViewById(R.id.iv2);

        verificar();

    }


    // crear un aleatorio
    public void aleatorio(View v){
        Integer aleatorio;

        // int nRandom = (int) (Math.random() * (max - min + 1) + min);
        aleatorio = (int) (Math.random() * (2000 - 1000 + 1) + 1000);
        mytvAleatorio.setText(String.valueOf(aleatorio));
    }


    // verificar si es aleatorio o no
    public void verificar(){

        mybtnComprobar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String strAleatorio = String.valueOf(mytvAleatorio.getText());

                // comprobar si no esta vacio el aleatorio, si existe
                if (!strAleatorio.isEmpty()) {
                    int random = Integer.parseInt(strAleatorio);
                    boolean flag = true;

                    if(flag){

                        // 2 (si no es divisible, es falso)
                        // si esta checkeado y si divisible por 2 te has equivocado
                        // hay que comprobar los falsos
                        if(mycb2.isChecked()){
                            if(random%2!=0){
                                flag=false;
                            }
                        }
                        // no checkeado y sí divisible por 2 te has equivocado
                        else{
                            if(random%2==0){
                                flag=false;
                            }
                        }

                        // 3
                        if(mycb3.isChecked()){
                            if(random%3!=0){
                                flag=false;
                            }
                        }
                        // no checkeado
                        else{
                            if(random%3==0){
                                flag=false;
                            }
                        }

                        // 5
                        if(mycb5.isChecked()){
                            if(random%5!=0){
                                flag=false;
                            }
                        }
                        // no checkeado
                        else{
                            if(random%5==0){
                                flag=false;
                            }
                        }

                        // 10
                        if(mycb10.isChecked()){
                            if(random%10!=0){
                                flag=false;
                            }
                        }
                        // no checkeado
                        else{
                            if(random%10==0){
                                flag=false;
                            }
                        }

                        //ninguno
                        if(mycbNo.isChecked() & !flag){

                        }

                        // No es divisible entre ninguno: flag = true
                        // si flag es true, de momento podemos entrar
                        // el resto de checkboxes no debe estar marcado porque si no es falso por defecto
                        if(flag){

                            mytvComprobar.setText("CORRECTO");
                            mytvComprobar.setBackgroundColor(Color.GREEN);
                            mytvComprobar.setTextColor(Color.WHITE);
                            flag = false;

                        }
                        else{
                            mytvComprobar.setText("INCORRECTO");
                            mytvComprobar.setBackgroundColor(Color.RED);
                            mytvComprobar.setTextColor(Color.WHITE);
                        }
                    }

                }
                else{
                    mytvComprobar.setText("GENERA UN ALEATORIO");
                }
            }
        });
    }


    // exit app
    public void salir(View v){
        System.exit(0);
    }

}