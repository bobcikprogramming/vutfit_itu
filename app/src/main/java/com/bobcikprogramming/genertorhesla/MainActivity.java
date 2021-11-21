package com.bobcikprogramming.genertorhesla;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*private TextView tvPassword;
    private EditText etPhrase;
    private Button btnGenerate;
    private RadioButton rbLetter, rbUpperLetter, rbNumber, rbSymbol;

    boolean hasLetter, hasUpponLetter, hasNumber, hasSymbol;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_test);

        /*setupUI();

        btnGenerate.setOnClickListener(new View.OnClickListener() {
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