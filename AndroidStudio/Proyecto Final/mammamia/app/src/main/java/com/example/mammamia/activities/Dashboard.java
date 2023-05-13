package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mammamia.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Dashboard extends AppCompatActivity {

    List<SlideModel> slideModelListAperitivos, slideModelListPlato1, slideModelListPlato2, slideModelListPostres;
    ImageSlider imageSliderAperitivos, imageSliderPlato1, imageSliderPlato2, imageSliderPostres;

    // Cuando le doy al boton de retroceso del dispositivo
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // si la version de Android que se esta ejecutando es >= a Android 8.1:
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1){
            // Cierra la actividad actual y todas las actividades relacionadas con ella
            finishAndRemoveTask();
        }

        // Asegura que se cierren todas las actividades y la aplicación se termine por completo
        this.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // https://github.com/denzcoskun/ImageSlideshow
        imageSliderAperitivos = (ImageSlider) findViewById(R.id.image_slider_aperitivos);
        imageSliderPlato1 = (ImageSlider) findViewById(R.id.image_slider_plato1);
        imageSliderPlato2 = (ImageSlider) findViewById(R.id.image_slider_plato2);
        imageSliderPostres = (ImageSlider) findViewById(R.id.image_slider_postres);

        addImages();
    }

    // https://github.com/denzcoskun/ImageSlideshow
    public void addImages(){
        // imageSliderAperitivos
        slideModelListAperitivos = new ArrayList<>();
        slideModelListAperitivos.add(new SlideModel(R.drawable.entrante_p001_yorkhuevohilado, getResources().getString(R.string.entrante_t001_yorkhuevohilado), ScaleTypes.CENTER_CROP));
        slideModelListAperitivos.add(new SlideModel(R.drawable.entrante_p002_nachosguacamole, getResources().getString(R.string.entrante_t002_nachosguacamole), ScaleTypes.CENTER_CROP));

        imageSliderAperitivos.setImageList(slideModelListAperitivos, ScaleTypes.FIT);


        // imageSliderPlato1
        slideModelListPlato1 = new ArrayList<>();
        slideModelListPlato1.add(new SlideModel(R.drawable.plato1_p001_ensalada, getResources().getString(R.string.plato1_t001_ensalada), ScaleTypes.CENTER_CROP));
        slideModelListPlato1.add(new SlideModel(R.drawable.plato1_p002_lentejas, getResources().getString(R.string.plato1_t002_lentejas), ScaleTypes.CENTER_CROP));
        slideModelListPlato1.add(new SlideModel(R.drawable.plato1_p003_garbanzos, getResources().getString(R.string.plato1_t003_garbanzos), ScaleTypes.CENTER_CROP));

        imageSliderPlato1.setImageList(slideModelListPlato1, ScaleTypes.FIT);


        // imageSliderPlato2
        slideModelListPlato2 = new ArrayList<>();
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p001_pizza, getResources().getString(R.string.plato2_t001_pizza), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p002_pasta, getResources().getString(R.string.plato2_t002_pasta), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p003_lasagna, getResources().getString(R.string.plato2_t003_lasagna), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p004_marmitako, getResources().getString(R.string.plato2_t004_marmitako), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p005_solomillo, getResources().getString(R.string.plato2_t005_solomillo), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p006_tortillapatatas, getResources().getString(R.string.plato2_t006_tortillapatatas), ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.plato2_p007_salmonvapor, getResources().getString(R.string.plato2_t007_salmonvapor), ScaleTypes.CENTER_CROP));

        imageSliderPlato2.setImageList(slideModelListPlato2, ScaleTypes.FIT);


        // imageSliderPostres
        slideModelListPostres = new ArrayList<>();
        slideModelListPostres.add(new SlideModel(R.drawable.postre_p001_tartalimon, getResources().getString(R.string.postre_t001_tartalimon), ScaleTypes.CENTER_CROP));
        slideModelListPostres.add(new SlideModel(R.drawable.postre_p002_arrozconleche, getResources().getString(R.string.postre_t002_arrozconleche), ScaleTypes.CENTER_CROP));

        imageSliderPostres.setImageList(slideModelListPostres, ScaleTypes.FIT);
    }


    public void ViewAll(View view){
        // Intent se utiliza para iniciar una nueva actividad,
        //      - El 1er parametro, context, es donde nos encontramos
        //      - El 2do parametro es la clase de actividad que queremos crear
        Intent intent = new Intent(Dashboard.this, MainActivity.class);

        // Dame el id del boton presionado y hazme un switch:
        switch (view.getId()){
            case R.id.btnDashboardTotal_aperitivos:
                intent.putExtra("condition", 1);
                break;
            case R.id.btnDashboardTotal_plato1:
                intent.putExtra("condition", 2);
                break;
            case R.id.btnDashboardTotal_plato2:
                intent.putExtra("condition", 3);
                break;
            case R.id.btnDashboardTotal_postres:
                intent.putExtra("condition", 4);
                break;
        }

        startActivity(intent);
    }
}