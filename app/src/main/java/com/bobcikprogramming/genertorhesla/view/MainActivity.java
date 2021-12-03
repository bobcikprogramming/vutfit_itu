package com.bobcikprogramming.genertorhesla.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bobcikprogramming.genertorhesla.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btnRandomPattern, btnOwnPattern;
    private ImageView btnLogin, btnHelp;
    private TextView tvRandomPattern, tvOwnPattern;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, new FragmentRandomPattern()).commit();
        resetColor();
        tvRandomPattern.setTextColor(ContextCompat.getColor(this, R.color.navBarSelect));

    }

    private void setupUI(){
        btnRandomPattern = findViewById(R.id.tabButtonRandomPattern);
        btnOwnPattern = findViewById(R.id.tabButtonOwnPattern);
        btnLogin = findViewById(R.id.btnLogin);
        btnHelp = findViewById(R.id.btnHelp);
        tvRandomPattern = findViewById(R.id.tabTextViewRandomPattern);
        tvOwnPattern = findViewById(R.id.tabTextViewOwnPattern);


        btnRandomPattern.setOnClickListener(this);
        btnOwnPattern.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabButtonRandomPattern:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new FragmentRandomPattern()).commit();
                resetColor();
                tvRandomPattern.setTextColor(ContextCompat.getColor(this, R.color.navBarSelect));
                break;
            case R.id.tabButtonOwnPattern:
                getSupportFragmentManager().beginTransaction().replace(R.id.layout, new FragmentManualPattern()).commit();
                resetColor();
                tvOwnPattern.setTextColor(ContextCompat.getColor(this, R.color.navBarSelect));
                break;
            case R.id.btnLogin:
                Intent intentLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentLogin);
                break;
            case R.id.btnHelp:
                Intent intentHelp = new Intent(MainActivity.this, HelperViewer.class);
                intentHelp.putExtra("logged", false);
                startActivity(intentHelp);
                break;
        }
    }

    private void resetColor(){
        tvRandomPattern.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvOwnPattern.setTextColor(ContextCompat.getColor(this, R.color.white));
    }
}