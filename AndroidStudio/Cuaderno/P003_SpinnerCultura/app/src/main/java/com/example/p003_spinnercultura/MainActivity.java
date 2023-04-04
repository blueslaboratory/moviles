package com.example.p003_spinnercultura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;


public class MainActivity extends AppCompatActivity {
    String anime [] = {" ","One Punch Man", "Dragon Ball GT", "Hunter X Hunter"};
    String musica [] = {" ","Red Hot Chili Peppers", "Arctic Monkeys", "Yiruma"};
    String serie [] = {" ","Malcolm in The Middle", "Spartacus Gods of the Arena", "Cosmos"};

    Spinner mySp1;
    Spinner mySp2;
    private ArrayAdapter myAdapter;
    public ImageView myiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySp1 = (Spinner) findViewById(R.id.sp1);
        mySp1.setPrompt("Selecciona una opcion");
        mySp2 = (Spinner) findViewById(R.id.sp2);

        myiv = (ImageView) findViewById(R.id.iv);


        mySp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String seleccion= mySp1.getSelectedItem().toString();

                if (seleccion.equalsIgnoreCase( "Serie")){
                    myAdapter = new ArrayAdapter<>( getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, serie);
                    myAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    mySp2.setVisibility(View.VISIBLE);
                    mySp2.setAdapter(myAdapter);

                } else if (seleccion.equalsIgnoreCase( "Grupo de música")){
                    myAdapter = new ArrayAdapter<>(  getApplicationContext(),  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, musica);
                    myAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    mySp2.setVisibility(View.VISIBLE);
                    mySp2.setAdapter(myAdapter);

                } else if (seleccion.equalsIgnoreCase( "anime")){
                    myAdapter = new ArrayAdapter<>(  getApplicationContext(),  androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, anime);
                    myAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
                    mySp2.setVisibility(View.VISIBLE);
                    mySp2.setAdapter(myAdapter);

                } else {
                    mySp2.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                mySp2.setVisibility(View.INVISIBLE);
            }
        });


        mySp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String seleccion2= mySp2.getSelectedItem().toString();
                myiv.setVisibility(View.VISIBLE);

                switch (seleccion2) {
                    case "One Punch Man":
                        myiv.setImageResource(R.drawable.one_punch);
                        break;
                    case "Dragon Ball GT":
                        myiv.setImageResource(R.drawable.dbgt);
                        break;
                    case "Hunter X Hunter" :
                        myiv.setImageResource(R.drawable.hunter_x_hunter);
                        break;
                    case "Red Hot Chili Peppers":
                        myiv.setImageResource(R.drawable.rhcp);
                        break;
                    case "Arctic Monkeys":
                        myiv.setImageResource(R.drawable.arctic_monkeys);
                        break;
                    case "Yiruma":
                        myiv.setImageResource(R.drawable.yiruma);
                        break;
                    case "Malcolm in The Middle" :
                        myiv.setImageResource(R.drawable.malcolm_in_the_middle);
                        break;
                    case "Spartacus Gods of the Arena":
                        myiv.setImageResource(R.drawable.spartacus_gods_of_the_arena);
                        break;
                    case  "Cosmos":
                        myiv.setImageResource(R.drawable.cosmos);
                        break;
                    default:
                        myiv.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                myiv.setVisibility(View.INVISIBLE);
            }
        });

    }
}