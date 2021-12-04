package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      BottomTabBarManualPatternLogged
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bobcikprogramming.genertorhesla.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BottomTabBarManualPatternLogged extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_bar_manual_pattern_logged);

        setupUI();
        onItemClick();

        getSupportFragmentManager().beginTransaction().replace(R.id.container, startCreate()).commit();
    }

    private void setupUI(){
        bottomNavigationView = findViewById(R.id.bottomNavBar);
    }

    private void onItemClick(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch(item.getItemId()){
                    case R.id.create:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, startCreate()).commit();
                        return true;
                    case R.id.load:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, startLoad()).commit();
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intentCancel = new Intent();
        intentCancel.putExtra("pattern", getIntent().getExtras().getSerializable("pattern"));
        setResult(RESULT_OK, intentCancel);
        finish();
    }

    private NewManualPatternLogged startCreate(){
        Bundle extras = getIntent().getExtras();
        Bundle bundleManual = new Bundle();
        bundleManual.putBoolean("newManual", true);
        bundleManual.putSerializable("pattern", extras.getSerializable("pattern"));

        NewManualPatternLogged manualPatternLogged = new NewManualPatternLogged();
        manualPatternLogged.setArguments(bundleManual);

        return manualPatternLogged;
    }

    private PatternList startLoad(){
        Bundle extras = getIntent().getExtras();
        Bundle bundleManual = new Bundle();
        bundleManual.putBoolean("newManual", true);
        bundleManual.putSerializable("pattern", extras.getSerializable("pattern"));

        PatternList patternNewManual = new PatternList();
        patternNewManual.setArguments(bundleManual);

        return patternNewManual;
    }
}