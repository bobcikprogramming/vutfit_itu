package com.bobcikprogramming.genertorhesla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.controllers.PasswordGenerator;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class NewManualPattern extends AppCompatActivity implements View.OnClickListener{

    private SwitchMaterial sFirstAtFirst, sSecondAtFirst, sThirdAtFirst, sFourthAtFirst, sFirstAtSecond, sSecondAtSecond, sThirdAtSecond, sFourthAtSecond, sFirstAtThird, sSecondAtThird, sThirdAtThird, sFourthAtThird;
    private TextView tvPattern;
    private TextView btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterThird, btnNumberThird, btnSymbolThird;
    private ImageView btnCancel, btnSave, btnFinish;

    private int firstOption, secondOption, thirdOption;
    private int firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manual_pattern);

        setupUI();
        showSaveBtnIfLogged();
        switchSelect();

        setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtFirst, sSecondAtFirst, sThirdAtFirst, sFourthAtFirst);
        setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtSecond, sSecondAtSecond, sThirdAtSecond, sFourthAtSecond);
        setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtThird, sSecondAtThird, sThirdAtThird, sFourthAtThird);
        firstOption = 0;
        secondOption = 0;
        thirdOption = 0;

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSave:
                break;
            case R.id.btnCancel:
                break;
            case R.id.btnFinish:
                break;
            case R.id.btnLetterFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtFirst, sSecondAtFirst, sThirdAtFirst, sFourthAtFirst);
                firstOption = 0;
                break;
            case R.id.btnNumberFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnNumberFirst, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, sFirstAtFirst, sSecondAtFirst, sThirdAtFirst, sFourthAtFirst);
                firstOption = 1;
                break;
            case R.id.btnSymbolFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnSymbolFirst, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, sFirstAtFirst, sSecondAtFirst, sThirdAtFirst, sFourthAtFirst);
                firstOption = 2;
                break;
            case R.id.btnLetterSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtSecond, sSecondAtSecond, sThirdAtSecond, sFourthAtSecond);
                secondOption = 0;
                break;
            case R.id.btnNumberSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnNumberSecond, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, sFirstAtSecond, sSecondAtSecond, sThirdAtSecond, sFourthAtSecond);
                secondOption = 1;
                break;
            case R.id.btnSymbolSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnSymbolSecond, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, sFirstAtSecond, sSecondAtSecond, sThirdAtSecond, sFourthAtSecond);
                secondOption = 2;
                break;
            case R.id.btnLetterThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, sFirstAtThird, sSecondAtThird, sThirdAtThird, sFourthAtThird);
                thirdOption = 0;
                break;
            case R.id.btnNumberThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnNumberThird, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, sFirstAtThird, sSecondAtThird, sThirdAtThird, sFourthAtThird);
                thirdOption = 1;
                break;
            case R.id.btnSymbolThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnSymbolThird, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, sFirstAtThird, sSecondAtThird, sThirdAtThird, sFourthAtThird);
                thirdOption = 2;
                break;
        }
    }

    private void setupUI(){
        tvPattern = findViewById(R.id.tvPattern);

        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnFinish = findViewById(R.id.btnFinish);

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        btnLetterFirst = findViewById(R.id.btnLetterFirst);
        btnNumberFirst = findViewById(R.id.btnNumberFirst);
        btnSymbolFirst = findViewById(R.id.btnSymbolFirst);
        btnLetterSecond = findViewById(R.id.btnLetterSecond);
        btnNumberSecond = findViewById(R.id.btnNumberSecond);
        btnSymbolSecond = findViewById(R.id.btnSymbolSecond);
        btnLetterThird = findViewById(R.id.btnLetterThird);
        btnNumberThird = findViewById(R.id.btnNumberThird);
        btnSymbolThird = findViewById(R.id.btnSymbolThird);

        btnLetterFirst.setOnClickListener(this);
        btnNumberFirst.setOnClickListener(this);
        btnSymbolFirst.setOnClickListener(this);
        btnLetterSecond.setOnClickListener(this);
        btnNumberSecond.setOnClickListener(this);
        btnSymbolSecond.setOnClickListener(this);
        btnLetterThird.setOnClickListener(this);
        btnNumberThird.setOnClickListener(this);
        btnSymbolThird.setOnClickListener(this);

        sFirstAtFirst = findViewById(R.id.sFirstAtFirst);
        sSecondAtFirst = findViewById(R.id.sSecondAtFirst);
        sThirdAtFirst = findViewById(R.id.sThirdAtFirst);
        sFourthAtFirst = findViewById(R.id.sFourthAtFirst);
        sFirstAtSecond = findViewById(R.id.sFirstAtSecond);
        sSecondAtSecond = findViewById(R.id.sSecondAtSecond);
        sThirdAtSecond = findViewById(R.id.sThirdAtSecond);
        sFourthAtSecond = findViewById(R.id.sFourthAtSecond);
        sFirstAtThird = findViewById(R.id.sFirstAtThird);
        sSecondAtThird = findViewById(R.id.sSecondAtThird);
        sThirdAtThird = findViewById(R.id.sThirdAtThird);
        sFourthAtThird = findViewById(R.id.sFourthAtThird);

        sFirstAtFirst.setOnClickListener(this);
        sSecondAtFirst.setOnClickListener(this);
        sThirdAtFirst.setOnClickListener(this);
        sFourthAtFirst.setOnClickListener(this);
        sFirstAtSecond.setOnClickListener(this);
        sSecondAtSecond.setOnClickListener(this);
        sThirdAtSecond.setOnClickListener(this);
        sFourthAtSecond.setOnClickListener(this);
        sFirstAtThird.setOnClickListener(this);
        sSecondAtThird.setOnClickListener(this);
        sThirdAtThird.setOnClickListener(this);
        sFourthAtThird.setOnClickListener(this);
    }

    private void showSaveBtnIfLogged(){
        Bundle extras = getIntent().getExtras();
        boolean logged = extras.getBoolean("logged");
        if(logged){
            btnSave.setVisibility(View.VISIBLE);
        }else{
            btnSave.setVisibility(View.INVISIBLE);
        }
    }

    private void setupOption(TextView letter, TextView number, TextView symbol, TextView option, int firstOptionSettingText, int secondOptionSettingText, int thirdOptionSettingText, int fourthOptionSettingText, SwitchMaterial sFirst, SwitchMaterial sSecond, SwitchMaterial sThird, SwitchMaterial sFourth){
        resetColorOfBtns(letter, number, symbol);
        option.setTextColor(ContextCompat.getColor(this, R.color.white));

        sFirst.setText(getResources().getString(firstOptionSettingText));
        sSecond.setText(getResources().getString(secondOptionSettingText));
        sThird.setText(getResources().getString(thirdOptionSettingText));
        sFourth.setText(getResources().getString(fourthOptionSettingText));
    }

    private void resetColorOfBtns(TextView letter, TextView number, TextView symbol){
        letter.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
        number.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
        symbol.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
    }

    private void switchSelect(){
        sFirstAtFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean first) {
                if(first){
                    resetSwitchs(sSecondAtFirst, sThirdAtFirst, sFourthAtFirst);
                    firstOptionSetting = 0;
                }else{
                    firstOptionSetting = -1;
                }
            }
        });

        sSecondAtFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean second) {
                if(second){
                    resetSwitchs(sFirstAtFirst, sThirdAtFirst, sFourthAtFirst);
                    firstOptionSetting = 1;
                }
            }
        });

        sThirdAtFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean second) {
                if(second){
                    resetSwitchs(sFirstAtFirst, sSecondAtFirst, sFourthAtFirst);
                }
            }
        });

        sFourthAtFirst.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean second) {
                if(second){
                    resetSwitchs(sFirstAtFirst, sSecondAtFirst, sThirdAtFirst);
                }
            }
        });
    }

    private void resetSwitchs(SwitchMaterial sOne, SwitchMaterial sTwo, SwitchMaterial sThree){
        sOne.setChecked(false);
        sTwo.setChecked(false);
        sThree.setChecked(false);
    }
}