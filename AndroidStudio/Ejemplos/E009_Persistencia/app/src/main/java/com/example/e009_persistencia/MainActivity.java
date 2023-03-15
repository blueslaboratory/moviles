package com.example.e009_persistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText myEditText;
    TextView myTextview;

    Integer progreso, progresoTotal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myEditText = findViewById(R.id.editTextNumber);
        myTextview = findViewById(R.id.TextView);
    }

    public void aniadirProgreso(View v){
        progreso = Integer.parseInt(myEditText.getText().toString());

        progresoTotal = progresoTotal + progreso;
    }

    public void verProgreso(View v){
        myTextview.setText(progresoTotal.toString());
    }
}