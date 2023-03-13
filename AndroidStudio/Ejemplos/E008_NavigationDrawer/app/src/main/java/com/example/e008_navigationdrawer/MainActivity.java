package com.example.e008_navigationdrawer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

/*

08/03/2023
En los temas hay un NoActionBar
<style name="Theme.E008_NavigationDrawer" parent="Theme.MaterialComponents.DayNight.NoActionBar">

*/

public class MainActivity extends AppCompatActivity {

    // cuidado al importar aqui, es el de appcompat:
    Toolbar mytoolbar;
    DrawerLayout mydrawerLayout;


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

    }

    @Override
    public void onBackPressed(){
        if(mydrawerLayout.isDrawerOpen(GravityCompat.START)){
            mydrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}