package com.example.ruleta;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button mybtnNewPlayer;
    TextView mytvSaldoNum;

    RadioButton myradioBtnParImpar;
    RadioButton myradioBtnDocena;

    Spinner myspinnerOpcion;
    ArrayAdapter<CharSequence> opcionAdapter;
    EditText myedtApuesta;

    Button mybtnApostar;
    TextView mytvResultadoLanzamiento;


    // Controlar Toast de errores
    int errorComprobacion = 0;

    // String resultado
    String stringResultado = "";

    // Juega docenas
    Boolean juegaDocenas = false;

    // Handler para los tiempos de espera
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mybtnNewPlayer = (Button) findViewById(R.id.btnNewPlayer);
        mytvSaldoNum = (TextView) findViewById(R.id.tvSaldoNum);

        myradioBtnParImpar = (RadioButton) findViewById(R.id.radioBtnParImpar);
        myradioBtnDocena = (RadioButton) findViewById(R.id.radioBtnDocena);

        myspinnerOpcion = (Spinner) findViewById(R.id.spinnerOpcion);
        myedtApuesta = (EditText) findViewById(R.id.edtApuesta);

        mybtnApostar = (Button) findViewById(R.id.btnApostar);
        mytvResultadoLanzamiento = (TextView) findViewById(R.id.tvResultadoLanzamiento);


        // Populate ArrayAdapter using string array and a spinner layout that we will define
        // * this can be used to refer current class instance variable
        // * this can be used to invoke current class method (implicitly)
        // * this can be passed as an argument in the method call
        // * this can be used to return the current class instance from the method

        // Cargado por defecto
        opcionAdapter = ArrayAdapter.createFromResource(this, R.array.array_par_impar, R.layout.spinner_layout);

        myradioBtnParImpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_par_impar, R.layout.spinner_layout);
                opcionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinnerOpcion.setAdapter(opcionAdapter);
            }
        });

        myradioBtnDocena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_docenas, R.layout.spinner_layout);
                opcionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinnerOpcion.setAdapter(opcionAdapter);
            }
        });


        nuevoJugador();

        // puedes comentarlo y agregarlo al onClick del boton 'Lanzar los dados'
        lanzarBolita();
    }


    // la funcion lanzar bolita
    public void nuevoJugador() {

        // Darle al botoncito
        mybtnNewPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mytvSaldoNum.setText("100");

                mybtnNewPlayer.setEnabled(false);

                Toast.makeText(MainActivity.this,
                         "\n¡Bienvenido a la Ruleta!",
                        Toast.LENGTH_LONG).show();

                // Hacemos un delay
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mybtnNewPlayer.setEnabled(true);
                    }
                }, 2000);
            }


        });
    }

            // Comprobaciones antes de lanzar los dados (1-4)
    // 1. El saldo > 0
    public boolean comprobarSaldo(View v){
        int saldo = Integer.parseInt(mytvSaldoNum.getText().toString());

        if (saldo>0) {
            return true;
        }
        else{
            errorComprobacion = 1;
            return false;
        }
    }

    // 2. Opcion Par/Impar, Docenas seleccionado
    public boolean comprobarRadioSeleccionado(View v){
        if(!myradioBtnParImpar.isChecked() && !myradioBtnDocena.isChecked()) {
            errorComprobacion = 2;
            return false;
        }
        else{
            return true;
        }
    }

    // 3. Spinner desplegable seleccionado
    public boolean comprobarSpinnerSeleccionado(View v){
        //***************************************
        //************SIN TERMINAR***************
        //***************************************

        // no necesario al coger una opcion automaticamente
        // Eliminado del strings.xml
        // <item>Seleccione una opcion</item>
        return true;
    }

    // 4. Apuesta no nula y numerica
    public boolean comprobarApuesta(View v){

        try{
            int apuesta = Integer.parseInt(myedtApuesta.getText().toString());

            if(apuesta>0){
                return true;
            }
            else{
                errorComprobacion = 4;
                return false;
            }

        } catch (Exception e){
            errorComprobacion = 4;
            return false;
        }
    }

    // 5. Saldo mayor o igual que la apuesta
    public boolean saldoMayorqueApuesta(View v){
        int saldo = Integer.parseInt(mytvSaldoNum.getText().toString());
        int apuesta = Integer.parseInt(myedtApuesta.getText().toString());

        if(saldo>=apuesta){
            return true;
        }
        else{
            errorComprobacion = 5;
            return false;
        }
    }


    // la funcion lanzar bolita
    public void lanzarBolita(){

        // Darle al botoncito
        mybtnApostar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // comprobar que:
                // 1. El saldo > 0: comprobarSaldo
                // 2. Opcion Par/Impar, docena seleccionado: comprobarRadioSeleccionado
                // 3. Spinner desplegable seleccionado: comprobarSpinnerSeleccionado
                // 4. Apuesta no nula (tiene valor) y numerica: comprobarApuesta
                // 5. Apuesta no supera el saldo: saldoMayorqueApuesta

                // si todo es correcto: lanzamos los dados
                if(comprobarSaldo(v) &&
                   comprobarRadioSeleccionado(v) &&
                   comprobarSpinnerSeleccionado(v) &&
                   comprobarApuesta(v) &&
                   saldoMayorqueApuesta(v)
                   ){


                    // Intento 1: sacar el gif de los dados
                    gifRuleta(v);


                    // haciendo que el boton no sea clickable hasta que hayan pasado 4000ms
                    mybtnApostar.setEnabled(false);


                    // Delay para el lanzamiento de la ruleta
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            // Conseguir un valor aleatorio para los dados
                            randomRuleta(v);
                            actualizarSaldo(gana());
                            ganandoMuchoBancarrota();
                            seguirJugando();
                            mybtnApostar.setEnabled(true);
                        }
                    }, 2000);


                }
                else{
                    switch(errorComprobacion){
                        case 1:
                            Toast.makeText(MainActivity.this,
                                    "\nError: saldo < 0",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this,
                                    "\nError: seleccione una opcion",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case 3:
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this,
                                    "\nError: apuesta no valida",
                                    Toast.LENGTH_LONG).show();
                            break;
                        case 5:
                            Toast.makeText(MainActivity.this,
                                    "\nError: la apuesta supera el saldo",
                                    Toast.LENGTH_LONG).show();
                            break;
                        default:
                            Toast.makeText(MainActivity.this,
                                    "\nError al lanzar la bolita",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }


            // Generar valores aleatorios para los dados
            public void randomRuleta(View v){
                int resultadoLanzamiento;

                // int nRandom = (int) (Math.random() * (max - min + 1) + min);
                resultadoLanzamiento = (int) (Math.random() * (36 - 0 + 1) + 0);

                mytvResultadoLanzamiento.setText(String.valueOf(resultadoLanzamiento));
            }

            // Lanzar los dados con un gif (intento 1: fallido - imagen estatica)
            public void gifRuleta(View v){
                int duracion = Toast.LENGTH_LONG;
                Toast toastRuleta = new Toast(MainActivity.this);
                toastRuleta.setDuration(duracion);

                LayoutInflater myInflator = getLayoutInflater();
                View myLayout = myInflator.inflate(R.layout.ruleta_casino, null);

                toastRuleta.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                toastRuleta.setView(myLayout);
                toastRuleta.show();
            }

            public void ganandoMuchoBancarrota(){
                int saldo = Integer.parseInt(mytvSaldoNum.getText().toString());
                if(saldo>=200){

                    int duracion = Toast.LENGTH_LONG;
                    Toast toastRuleta = new Toast(MainActivity.this);
                    toastRuleta.setDuration(duracion);

                    LayoutInflater myInflator = getLayoutInflater();
                    View myLayout = myInflator.inflate(R.layout.ganando_layout, null);

                    toastRuleta.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toastRuleta.setView(myLayout);
                    toastRuleta.show();

                    Toast.makeText(MainActivity.this,
                            "\nVas Ganando Mucho Dinero",
                            Toast.LENGTH_LONG).show();
                }
                if(saldo<=0){
                    int duracion = Toast.LENGTH_LONG;
                    Toast toastRuleta = new Toast(MainActivity.this);
                    toastRuleta.setDuration(duracion);

                    LayoutInflater myInflator = getLayoutInflater();
                    View myLayout = myInflator.inflate(R.layout.bancarrota_layout, null);

                    toastRuleta.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toastRuleta.setView(myLayout);
                    toastRuleta.show();

                    Toast.makeText(MainActivity.this,
                            "\nHas Perdido Mucho Dinero"
                                    +"\n¡Estas en bancarrota!",
                            Toast.LENGTH_LONG).show();

                }
            }

            public boolean gana(){
                boolean gana = false;

                int resultadoLanzamiento = Integer.parseInt(mytvResultadoLanzamiento.getText().toString());
                stringResultado = "Resultado: " +resultadoLanzamiento;
                String opcion = myspinnerOpcion.getSelectedItem().toString();


                switch(opcion){
                    case "Par":
                        if(resultadoLanzamiento%2 == 0) {
                            gana = true;
                            juegaDocenas = false;
                        }
                        break;
                    case "Impar":
                        if(resultadoLanzamiento%2 != 0) {
                            gana = true;
                            juegaDocenas = false;
                        }
                        break;
                    case "Primera Docena":
                        if(resultadoLanzamiento > 0 && resultadoLanzamiento<=12) {
                            gana = true;
                            juegaDocenas = true;
                        }
                        break;
                    case "Segunda Docena":
                        if(resultadoLanzamiento > 12 && resultadoLanzamiento<=24) {
                            gana = true;
                            juegaDocenas = true;
                        }
                        break;
                    case "Tercera Docena":
                        if(resultadoLanzamiento > 24 && resultadoLanzamiento<=36) {
                            gana = true;
                            juegaDocenas = true;
                        }

                    default:
                        break;
                }

                if (resultadoLanzamiento == 0){
                    gana = false;
                    juegaDocenas = false;
                }

                return gana;

            }


            public void actualizarSaldo(boolean gana){
                int saldo, apuesta;

                saldo = Integer.parseInt(mytvSaldoNum.getText().toString());
                apuesta = Integer.parseInt(myedtApuesta.getText().toString());

                if(gana && !juegaDocenas){
                    saldo = saldo + apuesta;
                    Toast.makeText(MainActivity.this,
                            "\n"+stringResultado +"\n¡Has ganado!",
                            Toast.LENGTH_LONG).show();
                }
                else if(gana && juegaDocenas) {
                    saldo = saldo + apuesta*2;
                    Toast.makeText(MainActivity.this,
                            "\n"+stringResultado +"\n¡Has ganado el doble de la apuesta!",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    saldo = saldo - apuesta;
                    Toast.makeText(MainActivity.this,
                            "\n"+stringResultado +"\n¡Has perdido!",
                            Toast.LENGTH_LONG).show();
                }

                mytvSaldoNum.setText(String.valueOf(saldo));
            }


            public void seguirJugando(){
                AlertDialog.Builder MyAlert = new AlertDialog.Builder(MainActivity.this);

                MyAlert.setTitle("Ruleta");
                MyAlert.setMessage("¿Quieres lanzar la bolita?");

                MyAlert.setPositiveButton("SI", null);
                MyAlert.setNegativeButton("SALIR DEL JUEGO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog dialog = MyAlert.create();
                dialog.show();

            }

        });
    }
}