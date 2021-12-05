package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      HelperViewer
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.HelperController;
import com.bobcikprogramming.genertorhesla.controllers.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

/**
 * Třída pro zobrazení nápovědy ve viewpageru.
 */
public class HelperViewer extends AppCompatActivity implements View.OnClickListener{

    private ViewPager viewPagerHelp;
    private ImageView btnCancel;

    private ViewPagerAdapter viewPagerAdapter;

    private HelperController help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper_viewer);

        help = new HelperController(this);

        Bundle bundle = getIntent().getExtras();
        help.setLogged(bundle.getBoolean("logged"));

        setupUI();
        setTablayoutDots();
        setViewPagerAdapter();
    }

    /**
     * Metoda pro načtení GUI podle id.
     */
    private void setupUI(){
        viewPagerHelp = findViewById(R.id.viewPagerHelp);

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCancel:
                finish();
        }
    }

    /**
     * Metoda pro zobrazení obsahu nápovědy podle stavu přihlášení.
     */
    private void setViewPagerAdapter(){
        if(help.isLogged()){
            viewPagerAdapter = new ViewPagerAdapter(HelperViewer.this, help.getLoggedHelp());
            viewPagerHelp.setAdapter(viewPagerAdapter);
        }else {
            viewPagerAdapter = new ViewPagerAdapter(HelperViewer.this, help.getUnloggedHelp());
            viewPagerHelp.setAdapter(viewPagerAdapter);
        }
    }

    /**
     * Metoda pro zobrazení teček indikujících pořadí okna nápovědy.
     *
     * Metoda byla inspirována z:
     * Zdroj:   Stack Overflow
     * Dotaz:   https://stackoverflow.com/q/20586619
     * Odpověď: https://stackoverflow.com/a/40047719
     * Autor:   Junaid
     * Autor:   https://stackoverflow.com/users/2382667/junaid
     * Datum:   14. října 2016
     */
    private void setTablayoutDots(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPagerHelp, true);
    }
}