package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import com.example.mammamia.R;
import com.example.mammamia.adapter.CustomAdapter;
import com.example.mammamia.model.Constant;

import java.util.ArrayList;
import java.util.List;

// 11/05/2023
// Icons: flaticon
// Tutorial de youtube: Admob App 20 || Project Create in android studio || Part 1 Recipes App


public class MainActivity extends AppCompatActivity {

    int condition;
    Intent intent;

    GridView gridView;
    CustomAdapter customAdapter;
    List<Constant> constantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        constantList = new ArrayList<>();

        intent = getIntent();
        condition = intent.getIntExtra("condition", 00);

        if (condition == 1){
            aperitivos();
        } else if(condition ==2){
            platos1();
        } else if(condition ==3){
            platos2();
        } else if(condition ==4){
            postres();
        }

        customAdapter = new CustomAdapter(MainActivity.this, constantList, this.condition);
        gridView.setAdapter(customAdapter);
    }



    private void aperitivos() {
        // picture (p), title (t), ingredientes (i), elaboracion (e)
        constantList.add(new Constant(R.drawable.entrante_p001_yorkhuevohilado,
                getResources().getString(R.string.entrante_t001_yorkhuevohilado),
                getResources().getString(R.string.entrante_i001_yorkhuevohilado),
                getResources().getString(R.string.entrante_e001_yorkhuevohilado)));
        constantList.add(new Constant(R.drawable.entrante_p002_nachosguacamole,
                getResources().getString(R.string.entrante_t002_nachosguacamole),
                getResources().getString(R.string.entrante_i002_nachosguacamole),
                getResources().getString(R.string.entrante_e002_nachosguacamole)));
    }

    private void platos1() {
        // picture (p), title (t), ingredientes (i), elaboracion (e)
        constantList.add(new Constant(R.drawable.plato1_p001_ensalada,
                getResources().getString(R.string.plato1_t001_ensalada),
                getResources().getString(R.string.plato1_i001_ensalada),
                getResources().getString(R.string.plato1_e001_ensalada)));
        constantList.add(new Constant(R.drawable.plato1_p002_lentejas,
                getResources().getString(R.string.plato1_t002_lentejas),
                getResources().getString(R.string.plato1_i002_lentejas),
                getResources().getString(R.string.plato1_e002_lentejas)));
        constantList.add(new Constant(R.drawable.plato1_p003_garbanzos,
                getResources().getString(R.string.plato1_t003_garbanzos),
                getResources().getString(R.string.plato1_i003_garbanzos),
                getResources().getString(R.string.plato1_e003_garbanzos)));

    }

    private void platos2() {
        // picture (p), title (t), ingredientes (i), elaboracion (e)
        constantList.add(new Constant(R.drawable.plato2_p001_pizza,
                getResources().getString(R.string.plato2_t001_pizza),
                getResources().getString(R.string.plato2_i001_pizza),
                getResources().getString(R.string.plato2_e001_pizza)));
        constantList.add(new Constant(R.drawable.plato2_p002_pasta,
                getResources().getString(R.string.plato2_t002_pasta),
                getResources().getString(R.string.plato2_i002_pasta),
                getResources().getString(R.string.plato2_e002_pasta)));
        constantList.add(new Constant(R.drawable.plato2_p003_lasagna,
                getResources().getString(R.string.plato2_t003_lasagna),
                getResources().getString(R.string.plato2_i003_lasagna),
                getResources().getString(R.string.plato2_e003_lasagna)));
        constantList.add(new Constant(R.drawable.plato2_p004_marmitako,
                getResources().getString(R.string.plato2_t004_marmitako),
                getResources().getString(R.string.plato2_i004_marmitako),
                getResources().getString(R.string.plato2_e004_marmitako)));
        constantList.add(new Constant(R.drawable.plato2_p005_solomillo,
                getResources().getString(R.string.plato2_t005_solomillo),
                getResources().getString(R.string.plato2_i005_solomillo),
                getResources().getString(R.string.plato2_e005_solomillo)));
        constantList.add(new Constant(R.drawable.plato2_p006_tortillapatatas,
                getResources().getString(R.string.plato2_t006_tortillapatatas),
                getResources().getString(R.string.plato2_i006_tortillapatatas),
                getResources().getString(R.string.plato2_e006_tortillapatatas)));
        constantList.add(new Constant(R.drawable.plato2_p007_salmonvapor,
                getResources().getString(R.string.plato2_t007_salmonvapor),
                getResources().getString(R.string.plato2_i007_salmonvapor),
                getResources().getString(R.string.plato2_e007_salmonvapor)));
    }

    private void postres() {
        // picture (p), title (t), ingredientes (i), elaboracion (e)
        constantList.add(new Constant(R.drawable.postre_p001_tartalimon,
                getResources().getString(R.string.postre_t001_tartalimon),
                getResources().getString(R.string.postre_i001_tartalimon),
                getResources().getString(R.string.postre_e001_tartalimon)));
        constantList.add(new Constant(R.drawable.postre_p002_arrozconleche,
                getResources().getString(R.string.postre_t002_arrozconleche),
                getResources().getString(R.string.postre_i002_arrozconleche),
                getResources().getString(R.string.postre_e002_arrozconleche)));
    }
}