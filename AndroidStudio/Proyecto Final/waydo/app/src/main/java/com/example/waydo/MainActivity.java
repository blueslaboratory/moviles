package com.example.waydo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

/*

PROBLEMAS CON CONNECT TO FIREBAS: PROYECTO ABANDONADO

08/03/2023
En los temas hay un NoActionBar
<style name="Theme.Waydo" parent="Theme.MaterialComponents.DayNight.NoActionBar">

09/05/2023
Proyecto de partida: P003_NavigationDrawer

ME QUEDO POR FRAGMENT_EVENTO_ADD
*/

// recuerda hacer el extends y el implements aqui
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    // cuidado al importar aqui, es el de appcompat:
    Toolbar mytoolbar;
    DrawerLayout mydrawerLayout;

    NavigationView navigationView;

    // Preferencias: guardar nombre usuario
    SharedPreferences myPrefs;
    TextView miNombre;



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


        // Escuchador de la cabecera + Preferencias
        myPrefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String username = myPrefs.getString("username", "blue");

        View header = navigationView.getHeaderView(0);
        miNombre = header.findViewById(R.id.tvMiNombre);
        miNombre.setText(username);
        miNombre.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder MyAlert = new AlertDialog.Builder(MainActivity.this);
                MyAlert.setTitle("Cambiar nombre de usuario");
                LayoutInflater myInflater = getLayoutInflater();
                View myViewInflater = myInflater.inflate(R.layout.nombre, null);
                MyAlert.setView(myViewInflater);

                MyAlert.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText myUsername = (EditText) myViewInflater.findViewById(R.id.editTextUsername);
                        String name = myUsername.getText().toString();

                        SharedPreferences.Editor editor = myPrefs.edit();
                        editor.putString("username", name);
                        editor.commit();

                        // lo seteamos
                        String username = myPrefs.getString("username", "por_defecto");
                        miNombre.setText(username);
                    }
                });

                MyAlert.setNegativeButton("No Guardar", null);

                AlertDialog dialog = MyAlert.create();
                dialog.show();


                /*
                Toast.makeText(getApplicationContext(),
                        "Si has llegado hasta aqui, enhorabuena: " +getString(R.string.mi_nombre),
                        Toast.LENGTH_SHORT).show();
                */

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
            // El home es a implementar en el futuro
            /*
            case R.id.id_home:
                titulo = "Home";
                FragmentWeb fragment1 = new FragmentWeb();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment1);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            */

            case R.id.id_login:
                titulo = "Iniciar sesión";
                FragmentLogin fragment2 = new FragmentLogin();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment2);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_registro:
                titulo = "Registro";
                FragmentRegistro fragment3 = new FragmentRegistro();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment3);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_actividades:
                titulo = "Ver actividades";
                FragmentActividades fragment4 = new FragmentActividades();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment4);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_actividades_add:
                titulo = "Añadir actividad";
                FragmentActividadesAdd fragment5 = new FragmentActividadesAdd();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment5);
                // Paso 4: Confirmar el cambio
                my_transaction.commit();
                setTitle(titulo);
                return;
            case R.id.id_usuario:
                titulo = "Mi usuario";
                FragmentUsuario fragment6 = new FragmentUsuario();
                // reemplazar el LinearLayout con el fragmento
                my_transaction.replace(R.id.linearLayout, fragment6);
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