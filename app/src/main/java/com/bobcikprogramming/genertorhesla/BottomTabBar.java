package com.bobcikprogramming.genertorhesla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomTabBar extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_bar);

        bottomNavigationView = findViewById(R.id.bottomNavBar);

        GeneratorLogged generatorLogged = new GeneratorLogged();
        /*FragmentTransactions fragmentTransactions = new FragmentTransactions();
        FragmentPDF fragmentPDF = new FragmentPDF();*/

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new GeneratorLogged()).commit();
        selectBottomMenu(R.id.generator);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.generator:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new GeneratorLogged()).commit();
                        return true;
                    case R.id.patternList:
                        //getSupportFragmentManager().beginTransaction().replace(R.id.container, fragmentTransactions).commit();
                        return true;
                }
                return false;
            }
        });
    }

    public void selectBottomMenu(final int position) {
        Handler uiHandler = new Handler();
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                bottomNavigationView.setSelectedItemId(position);
            }
        });
    }
}