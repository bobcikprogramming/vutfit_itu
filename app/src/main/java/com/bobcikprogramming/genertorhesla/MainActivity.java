package com.bobcikprogramming.genertorhesla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout btnRandomPattern, btnOwnPattern;
    private TextView tvRandomPattern, tvOwnPattern;

    /*private TextView tvPassword;
    private EditText etPhrase;
    private Button btnGenerate;
    private RadioButton rbLetter, rbUpperLetter, rbNumber, rbSymbol;

    boolean hasLetter, hasUpponLetter, hasNumber, hasSymbol;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_test);

        setupUI();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout, new FragmentRandomPattern()).commit();
        resetColor();
        tvRandomPattern.setTextColor(ContextCompat.getColor(this, R.color.navBarSelect));

        /*btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPatternSetting();

                PatternGenerator pattern = new PatternGenerator();

                PatternSetting patternSetting = pattern.generatePattern(hasLetter, hasUpponLetter, hasNumber, hasSymbol);
                if(patternSetting == null){
                    Toast.makeText(MainActivity.this, "Nastavte prosím parametry pro generování vzoru", Toast.LENGTH_SHORT).show();
                }else {
                    PasswordGenerator generator = new PasswordGenerator(etPhrase.getText().toString(), patternSetting, MainActivity.this);

                    if (!generator.editPhrase()) {
                        Toast.makeText(MainActivity.this, "Vyplňtě prosím slovo/frázi", Toast.LENGTH_SHORT).show();
                    } else {

                        tvPassword.setText(generator.genereta());
                    }
                }
            }
        });*/

    }

    private void setupUI(){
        btnRandomPattern = findViewById(R.id.tabButtonRandomPattern);
        btnOwnPattern = findViewById(R.id.tabButtonOwnPattern);
        tvRandomPattern = findViewById(R.id.tabTextViewRandomPattern);
        tvOwnPattern = findViewById(R.id.tabTextViewOwnPattern);


        btnRandomPattern.setOnClickListener(this);
        btnOwnPattern.setOnClickListener(this);
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
                //getSupportFragmentManager().beginTransaction().replace(R.id.layout, new AddTransactionTabSell(shortName, longName)).commit();
                resetColor();
                tvOwnPattern.setTextColor(ContextCompat.getColor(this, R.color.navBarSelect));
                break;
        }
    }

    private void resetColor(){
        tvRandomPattern.setTextColor(ContextCompat.getColor(this, R.color.white));
        tvOwnPattern.setTextColor(ContextCompat.getColor(this, R.color.white));
    }

    /*private void setupUI(){
        tvPassword = findViewById(R.id.tvPwd);
        etPhrase = findViewById(R.id.etPhrase);
        btnGenerate = findViewById(R.id.btnGenerate);
        rbLetter = findViewById(R.id.rbLetter);
        rbUpperLetter = findViewById(R.id.rbUpperLetter);
        rbNumber = findViewById(R.id.rbNumber);
        rbSymbol = findViewById(R.id.rbSymbol);
    }

    private void getPatternSetting(){
        this.hasLetter = rbLetter.isChecked();
        this.hasUpponLetter = rbUpperLetter.isChecked();
        this.hasNumber = rbNumber.isChecked();
        this.hasSymbol = rbSymbol.isChecked();
    }*/
}