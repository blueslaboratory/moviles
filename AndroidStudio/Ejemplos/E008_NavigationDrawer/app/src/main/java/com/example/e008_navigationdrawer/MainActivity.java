package com.example.e008_navigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

// 13/03/2023
import com.google.android.material.navigation.NavigationView;

/*

08/03/2023
En los temas hay un NoActionBar
<style name="Theme.E008_NavigationDrawer" parent="Theme.MaterialComponents.DayNight.NoActionBar">

*/

// recuerda hacer el extends y el implements aqui
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // cuidado al importar aqui, es el de appcompat:
    Toolbar mytoolbar;
    DrawerLayout mydrawerLayout;

    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytoolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(findViewById(R.id.materialToolbar));

        mydrawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mydrawerLayout, mytoolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mydrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        // 13/03/2023
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        // Para que aparezca una opcion por defecto

        // 14/03/2023
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        onNavigationItemSelected(menuItem);
        menuItem.setChecked(true);

        // Escuchador de la cabecera
        View header = navigationView.getHeaderView(0);
        TextView myNombre = header.findViewById(R.id.tvMiNombre);
        myNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getApplicationContext(),
                        "Si has llegado hasta aqui, eres un monstruo: " +getString(R.string.mi_nombre),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed(){
        if(mydrawerLayout.isDrawerOpen(GravityCompat.START)){
            mydrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // 13/03/2023
    // UT 4.4  Navegacion drawer
    private void showFragment(@NonNull MenuItem menuItem){
        String titulo;
        // Paso 1: Obtener la instancia del administrador de fragmentos
        FragmentManager my_fragmentManager = getSupportFragmentManager();
        // Paso 2: Crear una nueva transaccion
        FragmentTransaction my_transaction = my_fragmentManager.beginTransaction();
        // Paso 3: Crear un nuevo fragmento y aniadirlo
        switch (menuItem.getItemId()){
            case R.id.id_inicio:
                titulo = "Home";
                WebFragment fragment1 = new WebFragment();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment1);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_horario:
                titulo = "Horario";
                HorarioFragment fragment2 = new HorarioFragment();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment2);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_calendario:
                titulo = "Calendario";
                EventosFragment fragment3 = new EventosFragment();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment3);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_add:
                titulo = "Añadir Eventos";
                AddFragment fragment4 = new AddFragment();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment4);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_salir:
                System.exit(0);
            default:
                throw new IllegalArgumentException("menu option not implemented");
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        showFragment(item);
        return true;
    }
}