package com.example.dados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
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

import com.bumptech.glide.Glide;
import android.graphics.drawable.AnimationDrawable;


// Duda: Cuando se pasa el View v a un metodo?
/*
El objeto View se pasa a un metodo en Android Studio cuando se necesita interactuar con la vista
de alguna manera, como cambiar su contenido o asignar un comportamiento especifico a un evento.
 */

public class MainActivity extends AppCompatActivity {

    TextView mytvSaldo, mytvSaldoNum;
    RadioButton myradioBtnParImpar, myradioBtnMayorMenor;
    Spinner myspinnerOpcion;
    ArrayAdapter<CharSequence> opcionAdapter;
    EditText myeditTextNumber;
    Button mybtnLanzar;
    ImageView myivDado1, myivDado2;
    TextView mytvDado1, mytvDado2;

    // Dados dinamicos: intento 2
    // ImageView myivXML_loader;

    // Handler: como crear un hilo
    Handler handler = new Handler();

    // Controlar Toast de errores
    int errorComprobacion = 0;

    // Cargar gifs de una url de internet
    String stringResultado = "";
    String urlImagenDados = "http://clipart-library.com/images/qcBX8Xp8i.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // seteamos las variables
        mytvSaldo = (TextView) findViewById(R.id.tvSaldo);
        mytvSaldoNum = (TextView) findViewById(R.id.tvSaldoNum);

        myradioBtnParImpar = (RadioButton) findViewById(R.id.radioBtnParImpar);
        myradioBtnMayorMenor = (RadioButton) findViewById(R.id.radioBtnMayorMenor);

        myspinnerOpcion = (Spinner) findViewById(R.id.spinnerOpcion);
        myeditTextNumber = (EditText) findViewById(R.id.editTextNumber);
        mybtnLanzar = (Button) findViewById(R.id.btnLanzar);

        // dados estaticos
        myivDado1 = (ImageView) findViewById(R.id.ivDado1);
        myivDado2 = (ImageView) findViewById(R.id.ivDado2);

        // dados dinamicos (gifs)
        // Cargar un gif animado en ANDROID usando Glide
        Uri urlparse = Uri.parse(urlImagenDados);
        Glide.with(getApplicationContext()).load(urlparse).into(myivDado1);
        Glide.with(getApplicationContext()).load(urlparse).into(myivDado2);

        // dados dinamicos: intento 2 - AlertView
        // myivXML_loader = (ImageView) findViewById(R.id.ivXML_loader);

        mytvDado1 = (TextView) findViewById(R.id.tvDado1);
        mytvDado2 = (TextView) findViewById(R.id.tvDado2);


        // Populate ArrayAdapter using string array and a spinner layout that we will define
        // * this can be used to refer current class instance variable
        // * this can be used to invoke current class method (implicitly)
        // * this can be passed as an argument in the method call
        // * this can be used to return the current class instance from the method

        opcionAdapter = ArrayAdapter.createFromResource(this, R.array.array_par_impar, R.layout.spinner_layout);


        myradioBtnParImpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_par_impar, R.layout.spinner_layout);
                opcionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinnerOpcion.setAdapter(opcionAdapter);
            }
        });

        myradioBtnMayorMenor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionAdapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.array_mayor_menor, R.layout.spinner_layout);
                opcionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                myspinnerOpcion.setAdapter(opcionAdapter);
            }
        });


        // puedes comentarlo y agregarlo al onClick del boton 'Lanzar los dados'
        lanzarDados();

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

    // 2. Opcion Par/Impar, Mayor/Menor que 7 seleccionado
    public boolean comprobarRadioSeleccionado(View v){
        if(!myradioBtnParImpar.isChecked() && !myradioBtnMayorMenor.isChecked()) {
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
            int apuesta = Integer.parseInt(myeditTextNumber.getText().toString());

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
        int apuesta = Integer.parseInt(myeditTextNumber.getText().toString());

        if(saldo>=apuesta){
            return true;
        }
        else{
            errorComprobacion = 5;
            return false;
        }
    }


    // la funcion lanzar dados
    public void lanzarDados(){

        // Darle al botoncito
        mybtnLanzar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // comprobar que:
                // 1. El saldo > 0: comprobarSaldo
                // 2. Opcion Par/Impar, Mayor/Menor que 7 seleccionado: comprobarRadioSeleccionado
                // 3. Spinner desplegable seleccionado: comprobarSpinnerSeleccionado
                // 4. Apuesta no nula (tiene valor) y numerica: comprobarApuesta
                // 5. Apuesta no supera el saldo: saldoMayorqueApuesta

                // si todo es correcto: lanzamos los dados
                if(comprobarSaldo(v) &&
                   comprobarRadioSeleccionado(v) &&
                   comprobarSpinnerSeleccionado(v) &&
                   comprobarApuesta(v) &&
                   saldoMayorqueApuesta(v)){


                    // Intento 1: sacar el gif de los dados
                    gifDados(v);

                    // Intento 2: pasarle la imagen, si no no va (no va)
                    // gifDados(myivXML_loader);

                    // Conseguir un valor aleatorio para los dados
                    randomDados(v);

                    // haciendo que el boton no sea clickable hasta que hayan pasado 3000ms
                    mybtnLanzar.setEnabled(false);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            actualizarSaldo(gana());
                            seguirJugando();
                            mybtnLanzar.setEnabled(true);
                        }
                    }, 3000);

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
                                    "\nError al lanzar los dados",
                                    Toast.LENGTH_LONG).show();
                            break;
                    }

                }
            }
        });


    }


    // Generar valores aleatorios para los dados
    public void randomDados(View v){
        Integer dado1, dado2;

        // int nRandom = (int) (Math.random() * (max - min + 1) + min);
        dado1 = (int) (Math.random() * (6 - 1 + 1) + 1);
        dado2 = (int) (Math.random() * (6 - 1 + 1) + 1);

        mytvDado1.setText(String.valueOf(dado1));
        mytvDado2.setText(String.valueOf(dado2));

    }


    // Lanzar los dados con un gif (intento 1: fallido - imagen estatica)
    public void gifDados(View v){
        int duracion = Toast.LENGTH_LONG;
        Toast toastdados = new Toast(MainActivity.this);
        toastdados.setDuration(duracion);

        LayoutInflater myInflator = getLayoutInflater();
        View myLayout = myInflator.inflate(R.layout.daditos_layout, null);

        toastdados.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toastdados.setView(myLayout);
        toastdados.show();
    }



    // Lanzar los dados con un gif (intento 2: fallido por completo (A Tony y Mirel les funciona))
    /*
    public void gifDados(View v){
        LayoutInflater myInflter = getLayoutInflater();
        View gif = myInflter.inflate(R.layout.daditos_layout, null);
        Toast myToastDados = new Toast(getApplicationContext());
        Glide.with(MainActivity.this).load(R.drawable.daditos).into((ImageView) gif.findViewById(R.id.iv_gifdados));

        myToastDados.setGravity(Gravity.CENTER_VERTICAL, 0,0);
        myToastDados.setDuration(Toast.LENGTH_LONG);
        myToastDados.setView(gif);
        myToastDados.show();

    }
    */



    public boolean gana(){
        boolean gana = false;

        int vDado1 = Integer.parseInt(mytvDado1.getText().toString());
        int vDado2 = Integer.parseInt(mytvDado2.getText().toString());

        int resultado = vDado1+vDado2;
        stringResultado = "Resultado: " +resultado;

        String opcion = myspinnerOpcion.getSelectedItem().toString();


        switch(opcion){
            case "Par":
                if(resultado%2 == 0) {
                    gana = true;
                }
                break;
            case "Impar":
                if(resultado%2 != 0) {
                    gana = true;
                }
                break;
            case "Mayor que 7":
                if(resultado >= 7) {
                    gana = true;
                }
                break;
            case "Menor que 7":
                if(resultado < 7) {
                    gana = true;
                }
                break;
            default:
                break;
        }

        return gana;

    }


    public void actualizarSaldo(boolean gana){
        int saldo, apuesta;

        saldo = Integer.parseInt(mytvSaldoNum.getText().toString());
        apuesta = Integer.parseInt(myeditTextNumber.getText().toString());

        if(gana){
            saldo = saldo + apuesta;
            Toast.makeText(MainActivity.this,
                    "\n"+stringResultado +"\n¡Has ganado!",
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

        MyAlert.setTitle("DICE ROLL");
        MyAlert.setMessage("¿Quieres tirar los dados?");

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
}