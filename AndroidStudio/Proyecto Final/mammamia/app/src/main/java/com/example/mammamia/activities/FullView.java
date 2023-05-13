package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mammamia.R;
public class FullView extends AppCompatActivity {

    Intent intent;
    String title;
    String ingredientes;
    String elaboracion;
    int image;
    int condition;

    TextView tvActivityFullViewTitle;
    TextView tvActivityFullViewIngredientesTitulo, tvActivityFullViewIngredientesDescripcion;
    TextView tvActivityFullViewElaboracionTitulo, tvActivityFullViewElaboracionDescripcion;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_view);

        // Titulo
        tvActivityFullViewTitle = (TextView) findViewById(R.id.tvActivityFullViewTitle);
        // Ingredientes
        tvActivityFullViewIngredientesTitulo = (TextView) findViewById(R.id.tvActivityFullViewIngredientesTitulo);
        tvActivityFullViewIngredientesDescripcion = (TextView) findViewById(R.id.tvActivityFullViewIngredientesDescripcion);
        // Elaboracion
        tvActivityFullViewElaboracionTitulo = (TextView) findViewById(R.id.tvActivityFullViewElaboracionTitulo);
        tvActivityFullViewElaboracionDescripcion = (TextView) findViewById(R.id.tvActivityFullViewElaboracionDescripcion);
        // Image
        imageView = (ImageView) findViewById(R.id.ivActivityFullViewThumbnail);


        intent = getIntent();

        image = intent.getIntExtra("image", 00);
        condition = intent.getIntExtra("condition", 00);
        title = intent.getStringExtra("title");
        ingredientes = intent.getStringExtra("ingredientes");
        elaboracion = intent.getStringExtra("elaboracion");


        Toast.makeText(FullView.this, title, Toast.LENGTH_LONG).show();


        // Seteamos
        // Titulo e imagen
        imageView.setImageResource(image);
        tvActivityFullViewTitle.setText(title);
        // Ingredientes
        tvActivityFullViewIngredientesTitulo.setText("Ingredientes");
        tvActivityFullViewIngredientesDescripcion.setText(ingredientes);
        // Elaboracion
        tvActivityFullViewElaboracionTitulo.setText("Elaboración");
        tvActivityFullViewElaboracionDescripcion.setText(elaboracion);

    }
}