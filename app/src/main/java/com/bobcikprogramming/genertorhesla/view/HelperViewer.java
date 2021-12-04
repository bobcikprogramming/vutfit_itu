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

    private void setViewPagerAdapter(){
        if(help.isLogged()){
            viewPagerAdapter = new ViewPagerAdapter(HelperViewer.this, help.getLoggedHelp());
            viewPagerHelp.setAdapter(viewPagerAdapter);
        }else {
            viewPagerAdapter = new ViewPagerAdapter(HelperViewer.this, help.getUnloggedHelp());
            viewPagerHelp.setAdapter(viewPagerAdapter);
        }
    }

    // https://stackoverflow.com/a/40047719
    private void setTablayoutDots(){
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPagerHelp, true);
    }
}