package com.example.p004_memory;

// Autor: Antonio Avila

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    Button mybtnReiniciar, mybtnSalir;
    TextView mytv;
    ImageButton myib0, myib1, myib2, myib3, myib4, myib5, myib6, myib7, myib8,
                myib9, myib10, myib11, myib12, myib13, myib14, myib15, myibAnterior;

    int numImagenAnterior, numBotonAnterior, puntuacion, aciertos;
    int[] arrayImagenes;

    ArrayList<Integer> desorden;
    ArrayList<ImageButton> arrayImageButton, bloqueados;

    Handler handler = new Handler();
    boolean compara = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myib0 = (ImageButton) findViewById(R.id.ib0);
        myib1 = (ImageButton) findViewById(R.id.ib1);
        myib2 = (ImageButton) findViewById(R.id.ib2);
        myib3 = (ImageButton) findViewById(R.id.ib3);
        myib4 = (ImageButton) findViewById(R.id.ib4);
        myib5 = (ImageButton) findViewById(R.id.ib5);
        myib6 = (ImageButton) findViewById(R.id.ib6);
        myib7 = (ImageButton) findViewById(R.id.ib7);
        myib8 = (ImageButton) findViewById(R.id.ib8);
        myib9 = (ImageButton) findViewById(R.id.ib9);
        myib10 = (ImageButton) findViewById(R.id.ib10);
        myib11 = (ImageButton) findViewById(R.id.ib11);
        myib12 = (ImageButton) findViewById(R.id.ib12);
        myib13 = (ImageButton) findViewById(R.id.ib13);
        myib14 = (ImageButton) findViewById(R.id.ib14);
        myib15 = (ImageButton) findViewById(R.id.ib15);

        mybtnReiniciar = (Button) findViewById(R.id.btnReiniciar);
        mybtnSalir = (Button) findViewById(R.id.btnSalir);
        mytv = (TextView) findViewById(R.id.tv);

        // Array de las imagenes de parejas
        arrayImagenes = new int[]{
            R.drawable.la0,
            R.drawable.la1,
            R.drawable.la2,
            R.drawable.la3,
            R.drawable.la4,
            R.drawable.la5,
            R.drawable.la6,
            R.drawable.la7
        };

        iniciarJugada();


        // Crear un listener para cada ImageButton en el arrayList
        // Cuando se hace click en el ImageButton correspondiente llamar a revelarCarta
        for (int i = 0; i < arrayImageButton.size(); i++) {

            ImageButton im = arrayImageButton.get(i);
            Integer numero = i;

            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    revelarCarta(im, numero);
                }
            });
        }


        // Lo ponemos en un listener para no meterlo en el onclick del boton
        mybtnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarJugada();
            }
        });

        // Lo ponemos en un listener para no meterlo en el onclick del boton
        mybtnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

    }


    private void iniciarJugada() {
        arrayImageButton = crearArrayImageButton();

        aciertos = 0;
        puntuacion = 0;

        mytv.setText("Iniciando Jugada");

        desorden = barajarCartas();
        mostrarTodasCartas(desorden);

        // Ocultar el temporizador del juego
        taparTemporizador();

        // Habilitar la interaccion del usuario con las cartas
        desbloquear();

        /*
        Es comun utilizar el Handler en Android para realizar acciones en segundo plano,
        programar tareas asincronas o para realizar acciones diferidas en el tiempo.
        En este caso, se esta utilizando para actualizar la puntuacion del juego despues
        de que el temporizador (2000ms) haya finalizado.

        El metodo postDelayed() de la clase Handler permite programar la ejecucion de un
        Runnable despues de un cierto tiempo de espera. En este caso, la puntuacion se
        mostrara en el TextView despues de 2 segundos de haberse iniciado el temporizador.
        */
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mytv.setText("Puntuacion: " + puntuacion);
            }
        }, 2000);
    }
    
    
    // Crear un arraylist de imagenes por defecto (imagenes boton)
    private ArrayList<ImageButton> crearArrayImageButton() {
        ArrayList<ImageButton> aib = new ArrayList<>();
        aib.add(myib0);
        aib.add(myib1);
        aib.add(myib2);
        aib.add(myib3);
        aib.add(myib4);
        aib.add(myib5);
        aib.add(myib6);
        aib.add(myib7);
        aib.add(myib8);
        aib.add(myib9);
        aib.add(myib10);
        aib.add(myib11);
        aib.add(myib12);
        aib.add(myib13);
        aib.add(myib14);
        aib.add(myib15);

        return aib;
    }
    
    
    private ArrayList barajarCartas() {
        desorden = new ArrayList<>();

        // Se agregan por parejas: 8 pares de cartas
        for (int i = 0; i < 8; i++) {
            desorden.add(i);
            desorden.add(i);
        }

        bloqueados = crearArrayImageButton();

        // Barajar
        Collections.shuffle(desorden);

        // Devuelve un array de integers
        return desorden;
    }
    
    
    /*
    Esta funcion se utiliza para establecer las imagenes de las cartas en los
    botones de imagen correspondientes en la interfaz de usuario del juego.
    La lista desorden se utiliza para determinar que imagen debe establecerse
    en cada boton de imagen.
     */
    private void mostrarTodasCartas(ArrayList<Integer> desorden) {
        for (int i = 0; i < arrayImageButton.size(); i++) {
            // Coger boton imagen por defecto nºi (de [0 - arrayImageButton.size()])
            ImageButton im = arrayImageButton.get(i);

            // Setear ese boton imagen nºi a la carta dada la vuelta correspondiente
            // del ArrayList<Integer> desorden
            im.setImageResource(arrayImagenes[desorden.get(i)]);
            im.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
    
    
    /*
    Esta funcion se utiliza para "tapar" las cartas despues de que se hayan
    mostrado durante 10000 milisegundos utilizando un temporizador.
    La funcion establece la imagen de cada boton de imagen en la imagen de fondo
    correspondiente. Esto significa que las cartas ya no seran visibles para el
    usuario y que la siguiente ronda del juego puede comenzar.
    */
    private void taparTemporizador() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arrayImageButton.size(); i++) {
                    ImageButton im = arrayImageButton.get(i);
                    im.setImageResource(R.drawable.fondo);
                    im.setScaleType(ImageView.ScaleType.CENTER_CROP);
                }
            }
        }, 10000);
    }


    /*
    La funcion revelarCarta se utiliza para revelar la carta correspondiente cuando
    el usuario hace clic en un boton de imagen. Si la variable compara es verdadera,
    la funcion comprueba si la imagen de la carta actual es diferente de la imagen de
    la carta anterior y realiza las acciones correspondientes. Si la variable compara
    es falsa, la funcion establece las variables numImagenAnterior, numBotonAnterior y
    myibAnterior en la carta y el boton de imagen actual y establece compara en verdadero
     */
    private void revelarCarta(ImageButton myImageButtonActual, int numActual) {

        // Establecer la imagen de myImageButtonActual al indice correspondiente en arrayImagenes
        myImageButtonActual.setImageResource(arrayImagenes[desorden.get(numActual)]);

        if (compara == true) {

            // bloquear();

            // Comprobar si la imagen de la carta actual es diferente de la
            // imagen de la carta anterior: si es verdad que son distintos --> ha fallado
            // establecemos para la carta actual y la anterior la imagen fondo
            if (desorden.get(numActual) != numImagenAnterior) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        // tapamos la imagen actual con el fondo
                        // taparUno(myImageButtonActual);
                        myImageButtonActual.setImageResource(R.drawable.fondo);

                        // tapamos la imagen anterior con el fondo
                        taparUno(myibAnterior);

                        // cambiamos la puntuacion: puntuacion--
                        cambiarPuntuacion(-1);
                    }
                }, 2000);
                desorden.remove(myImageButtonActual);
                desorden.remove(myibAnterior);

            }
            else {
                // las 2 imagenes son iguales: true
                // deshabilitar el boton de imagen actual
                myImageButtonActual.setEnabled(false);
                cambiarPuntuacion(1);
                aciertos++;

                // Si aciertas todas
                if (aciertos == 8) {
                    // creo el toast de ¡has ganado!
                    myToast();
                }
                //  desbloquear();
            }

            compara = false;

        }

        /*
        else (compara==false)
        Si compara es falsa, la funcion deshabilita el boton de imagen actual y establece
        compara en verdadero. La funcion tambien establece numImagenAnterior en la imagen
        actual (desorden.get(numActual)), numBotonAnterior en el numero actual (numActual) y
        myibAnterior en el boton de imagen actual (myImageButtonActual).
        */
        else {
            myImageButtonActual.setEnabled(false);
            compara = true;

            numImagenAnterior = desorden.get(numActual);
            numBotonAnterior = numActual;
            myibAnterior = myImageButtonActual;
        }
    }


    private void taparUno(ImageButton myboton) {
        myboton.setImageResource(R.drawable.fondo);
        myboton.setEnabled(true);
    }


    private void desbloquear() {
        for (int i = 0; i < arrayImageButton.size(); i++) {
            // si el array de bloqueados lo contiene me lo desbloqueas
            if (bloqueados.contains(arrayImageButton.get(i))) {
                ImageButton im = arrayImageButton.get(i);
                im.setEnabled(true);
            }
        }
    }


    private void bloquear() {
        for (int i = 0; i < arrayImageButton.size(); i++) {
            ImageButton im = arrayImageButton.get(i);
            im.setEnabled(false);
        }
    }


    private void cambiarPuntuacion(int num) {
        puntuacion += num;
        if (puntuacion < 0)
            puntuacion = 0;

        mytv.setText("Puntuacion: " + puntuacion);
    }


    public void myToast() {
        LayoutInflater myInflater = getLayoutInflater();
        View myLayout = myInflater.inflate(R.layout.toast_custom, null);

        Toast myToast = new Toast(getApplicationContext());

        myToast.setGravity(Gravity.BOTTOM, 0, 0);
        myToast.setDuration(Toast.LENGTH_LONG);
        myToast.setView(myLayout);

        myToast.show();
    }
}