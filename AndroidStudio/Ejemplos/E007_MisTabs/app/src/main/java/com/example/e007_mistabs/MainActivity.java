package com.example.e007_mistabs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

// Hay que poner un tema que no tenga action bar
// <style name="Theme.E007_MisTabs" parent="Theme.MaterialComponents.DayNight.NoActionBar">

public class MainActivity extends AppCompatActivity {

    Toolbar my_apb;
    TabLayout my_tabLayout;
    ViewPager2 my_viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        my_apb = findViewById(R.id.my_toolbar);
        setSupportActionBar(my_apb);

        my_tabLayout  = (TabLayout) findViewById(R.id.my_tabs);
        my_viewPager = (ViewPager2) findViewById(R.id.my_viewpager);

        MiAdaptador adaptater = new MiAdaptador(this);
        my_viewPager.setAdapter(adaptater);
        new TabLayoutMediator(my_tabLayout, my_viewPager,
                (tab, position) -> tab.setText(adaptater.getTabTitle(position))
        ).attach();


    }
}