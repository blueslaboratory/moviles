package com.example.mammamia.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mammamia.R;

import android.os.Build;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


public class Dashboard extends AppCompatActivity {

    List<SlideModel> slideModelListPlato1, slideModelListPlato2;
    ImageSlider imageSliderPlato1, imageSliderPlato2;

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
        imageSliderPlato1 = (ImageSlider) findViewById(R.id.image_slider_plato1);
        imageSliderPlato2 = (ImageSlider) findViewById(R.id.image_slider_plato2);

        addImages();
    }

    void addImages(){
        // image_slider_n0
        // https://github.com/denzcoskun/ImageSlideshow
        slideModelListPlato1 = new ArrayList<>();
        slideModelListPlato1.add(new SlideModel(R.drawable.p004_ensalada, "Ensalada con Pollo", ScaleTypes.CENTER_CROP));
        slideModelListPlato1.add(new SlideModel(R.drawable.p006_lentejas, "Lentejas", ScaleTypes.CENTER_CROP));
        slideModelListPlato1.add(new SlideModel(R.drawable.p007_garbanzos, "Garbanzos", ScaleTypes.CENTER_CROP));



        imageSliderPlato1.setImageList(slideModelListPlato1, ScaleTypes.FIT);


        //image_slider_n1
        slideModelListPlato2 = new ArrayList<>();
        slideModelListPlato2.add(new SlideModel(R.drawable.p001_pizza, "Pizza Funghi", ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.p002_pasta, "Pasta Carbonara", ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.p003_lasagna, "Lasagna", ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.p005_marmitako, "Marmitako", ScaleTypes.CENTER_CROP));
        slideModelListPlato2.add(new SlideModel(R.drawable.p009_tortillapatatas, "Tortilla de Patatas", ScaleTypes.CENTER_CROP));

        imageSliderPlato2.setImageList(slideModelListPlato2, ScaleTypes.FIT);
    }
}