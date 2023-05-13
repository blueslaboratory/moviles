package com.example.mammamia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.mammamia.R;
import com.example.mammamia.fragments.FragmentBuscarReceta;
import com.google.android.material.navigation.NavigationView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class Dashboard extends AppCompatActivity {

    List<SlideModel> slideModelListAperitivos, slideModelListPlato1, slideModelListPlato2, slideModelListPostres;
    ImageSlider imageSliderAperitivos, imageSliderPlato1, imageSliderPlato2, imageSliderPostres;


    // Navigation Drawer
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;


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


        // NavigationDrawer
        navigationView = (NavigationView) findViewById(R.id.navigationViewDashboard);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayoutDashboard);

        // ActionBarDrawerToggle se utiliza para vincular el Navigation Drawer con la Action Bar (barra de acciones) de la actividad.
        //      - 1er parametro: contexto de la actividad actual
        //      - 2do parametro: objeto DrawerLayout al que se vinculará el ActionBarDrawerToggle
        //                       es el contenedor principal que contiene el contenido principal de la actividad y el Navigation Drawer.
        //      - 3er parametro: texto que se mostrará en la acción de abrir el Navigation Drawer en la Action Bar
        //      - 4to parametro: texto que se mostrará en la acción de cerrar el Navigation Drawer en la Action Bar.
        actionBarDrawerToggle = new ActionBarDrawerToggle(Dashboard.this, drawerLayout, R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();

        // Listener para el drawerLayout
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // Intente meterle fragments sustituyendo el (gridView) (linearLayout)
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                String titulo;
                // Paso 1: Obtener la instancia del administrador de fragmentos
                FragmentManager my_fragmentManager = getSupportFragmentManager();
                // Paso 2: Crear una nueva transaccion
                FragmentTransaction my_transaction = my_fragmentManager.beginTransaction();
                // Paso 3: Crear un nuevo fragmento y aniadirlo


                // estan en /res/menu/main_menu
                switch (item.getItemId()){
                    /*
                    case (R.id.iInicio):
                        break;

                    case (R.id.iBuscarReceta):
                        // Por alguna razon, al meter fragments se queda pillado en el fragmento y no puedo volver atras

                        titulo = "Buscar Receta";
                        FragmentBuscarReceta fragment0 = new FragmentBuscarReceta();
                        // reemplazar el drawerLayoutDashboard con el fragmento
                        my_transaction.replace(R.id.drawerLayoutDashboard, fragment0);
                        // Paso 4: Confirmar el cambio
                        my_transaction.commit();
                        setTitle(titulo);

                        break;


                    case (R.id.iFavoritos):
                        break;

                    case (R.id.iCrearReceta):
                        break;
                    */

                    case (R.id.iMasApps):
                        // https://stackoverflow.com/questions/11753000/how-to-open-the-google-play-store-directly-from-my-android-application
                        final String playstore = "recetas"; // getPackageName() from Context or Activity object
                        final String browser = "recetas"; // para palabras compuestas: +
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:" + playstore)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=" + browser)));
                        }
                        break;

                    case (R.id.iCalificanos):
                        final String appname = "mammamia";
                        Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appname));
                        startActivity(rateIntent);
                        break;

                    case (R.id.iPrivacidad):
                        // https://app-privacy-policy-generator.firebaseapp.com/
                        // ver carpeta assets: privacidad.html: https://app-privacy-policy-generator.firebaseapp.com/
                        startActivity(new Intent(Dashboard.this, Privacidad.class));
                        break;

                    case (R.id.iCompartir):
                        // https://www.javatpoint.com/android-share-app-data
                        Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Download Mamma Mia Recipes");
                        String app_url = " https://play.google.com/store/apps/details?id=" +getPackageName();
                        shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
                        startActivity(Intent.createChooser(shareIntent, "Share via"));
                        break;

                    /*
                    case (R.id.iContacto):
                        break;
                    */

                    case (R.id.iSalir):
                        finishAffinity();
                        finishAndRemoveTask();
                        System.exit(0);
                        break;

                    default:
                        throw new IllegalArgumentException("menu option not implemented");



                }

                return true;
            }
        });



        // https://github.com/denzcoskun/ImageSlideshow
        imageSliderAperitivos = (ImageSlider) findViewById(R.id.image_slider_aperitivos);
        imageSliderPlato1 = (ImageSlider) findViewById(R.id.image_slider_plato1);
        imageSliderPlato2 = (ImageSlider) findViewById(R.id.image_slider_plato2);
        imageSliderPostres = (ImageSlider) findViewById(R.id.image_slider_postres);

        addImages();
    }





    // Devolver el item seleccionado del NavigationDrawer
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        return actionBarDrawerToggle.onOptionsItemSelected(item) ||
               super.onOptionsItemSelected(item);
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