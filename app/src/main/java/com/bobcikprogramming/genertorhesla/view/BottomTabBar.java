package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      BottomTabBar
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;

import com.bobcikprogramming.genertorhesla.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

/**
 * Pomocná třída vykreslující spodní lištu u View.
 */
public class BottomTabBar extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_tab_bar);

        setupUI();
        onItemClick();

        Bundle extras = getIntent().getExtras();

        GeneratorLogged generatorLogged = new GeneratorLogged();

        if(extras != null) {
            Bundle bundleManual = new Bundle();
            bundleManual.putBoolean("logged", true);
            bundleManual.putSerializable("pattern", extras.getSerializable("pattern"));
            generatorLogged.setArguments(bundleManual);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.container, generatorLogged).commit();
    }

    @Override
    public void onBackPressed() {
        openDialogWindow();
    }

    /**
     * Načtení GUI komponent dle jejich id.
     */
    private void setupUI(){
        bottomNavigationView = findViewById(R.id.bottomNavBar);
    }

    private void onItemClick(){
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId()){
                    case R.id.generator:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new GeneratorLogged()).commit();
                        return true;
                    case R.id.patternList:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, new PatternList()).commit();
                        return true;
                }
                return false;
            }
        });
    }

    /**
     * Vytvoření dialogového hesla pro potvrzení odhlášení.
     */
    private void openDialogWindow(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.MyDialog);
        alert.setTitle("Odhlásit se");
        alert.setMessage("Opravdu se chcete odhlásit?");

        alert.setPositiveButton("Odhlásit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        alert.setNegativeButton("Zůstat", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
}