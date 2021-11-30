package com.bobcikprogramming.genertorhesla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.PasswordGenerator;
import com.bobcikprogramming.genertorhesla.controllers.PatternGenerator;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;


public class NewManualPattern extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird;
    private RadioGroup rGroupFirst, rGroupSecond, rGroupThird;
    private TextView tvPattern;
    private TextView btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterThird, btnNumberThird, btnSymbolThird, tvFirstOptionHeadline, tvSecondOptionHeadline, tvThirdOptionHeadline;
    private ImageView btnCancel, btnSave, btnFinish;

    private int firstOption, secondOption, thirdOption;
    private int firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    private GeneratePassword generate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manual_pattern);
        generate = new GeneratePassword(this);

        this.firstOption = 0;
        this.secondOption = 0;
        this.thirdOption = 0;

        this.firstOptionSetting = -1;
        this.secondOptionSetting = -1;
        this.thirdOptionSetting = -1;

        Bundle extras = getIntent().getExtras();
        generate.setPatternSetting((PatternSetting) extras.getSerializable("pattern"));

        setupUI();

        showSaveBtnIfLogged(extras);
        showSettingIfNotNull();
        radioButtonSelected();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSave:
                break;
            case R.id.btnCancel:
                Intent intentCancel = new Intent();
                intentCancel.putExtra("pattern", generate.getPatternSetting());
                setResult(RESULT_OK, intentCancel );
                finish();
                break;
            case R.id.btnFinish:
                if(generate.getPatternSetting() != null){
                    Intent intentFinish = new Intent();
                    intentFinish.putExtra("pattern", generate.getPatternSetting());
                    setResult(RESULT_OK, intentFinish );
                    finish();
                }else{
                    shakeEmpty();
                }
                break;
            case R.id.btnLetterFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                firstOption = 0;
                break;
            case R.id.btnNumberFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnNumberFirst, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                firstOption = 1;
                break;
            case R.id.btnSymbolFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnSymbolFirst, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                firstOption = 2;
                break;
            case R.id.btnLetterSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                secondOption = 0;
                break;
            case R.id.btnNumberSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnNumberSecond, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                secondOption = 1;
                break;
            case R.id.btnSymbolSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnSymbolSecond, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                secondOption = 2;
                break;
            case R.id.btnLetterThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                thirdOption = 0;
                break;
            case R.id.btnNumberThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnNumberThird, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                thirdOption = 1;
                break;
            case R.id.btnSymbolThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnSymbolThird, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                thirdOption = 2;
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("pattern", generate.getPatternSetting());
        setResult(RESULT_OK, intent );
        finish();
    }

    private void setupUI(){
        tvPattern = findViewById(R.id.tvPattern);
        tvFirstOptionHeadline = findViewById(R.id.tvFirstOptionHeadline);
        tvSecondOptionHeadline = findViewById(R.id.tvSecondOptionHeadline);
        tvThirdOptionHeadline = findViewById(R.id.tvThirdOptionHeadline);

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

        rBtnFirstAtFirst = findViewById(R.id.rBtnFirstAtFirst);
        rBtnSecondAtFirst = findViewById(R.id.rBtnSecondAtFirst);
        rBtnThirdAtFirst = findViewById(R.id.rBtnThirdAtFirst);
        rBtnFourthAtFirst = findViewById(R.id.rBtnFourthAtFirst);
        rBtnFirstAtSecond = findViewById(R.id.rBtnFirstAtSecond);
        rBtnSecondAtSecond = findViewById(R.id.rBtnSecondAtSecond);
        rBtnThirdAtSecond = findViewById(R.id.rBtnThirdAtSecond);
        rBtnFourthAtSecond = findViewById(R.id.rBtnFourthAtSecond);
        rBtnFirstAtThird = findViewById(R.id.rBtnFirstAtThird);
        rBtnSecondAtThird = findViewById(R.id.rBtnSecondAtThird);
        rBtnThirdAtThird = findViewById(R.id.rBtnThirdAtThird);
        rBtnFourthAtThird = findViewById(R.id.rBtnFourthAtThird);

        rGroupFirst = findViewById(R.id.rGroupFirst);
        rGroupSecond = findViewById(R.id.rGroupSecond);
        rGroupThird = findViewById(R.id.rGroupThird);

        rGroupFirst.setOnClickListener(this);
        rGroupSecond.setOnClickListener(this);
        rGroupThird.setOnClickListener(this);
    }

    private void showSaveBtnIfLogged(Bundle extras){
        boolean logged = extras.getBoolean("logged");
        if(logged){
            btnSave.setVisibility(View.VISIBLE);
        }else{
            btnSave.setVisibility(View.INVISIBLE);
        }
    }

    private void showSettingIfNotNull(){
        PatternSetting patternSetting = generate.getPatternSetting();

        if(patternSetting != null){
            if(patternSetting.getFirstOption() == 0){
                firstOption = 0;
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                //btnLetterFirst.setTextColor(ContextCompat.getColor(this, R.color.white));
            }else if(patternSetting.getFirstOption() == 1){
                firstOption = 1;
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnNumberFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                //btnNumberFirst.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else{
                firstOption = 2;
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnSymbolFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                //btnSymbolFirst.setTextColor(ContextCompat.getColor(this, R.color.white));
            }

            if(patternSetting.getSecondOption() == 0){
                secondOption = 0;
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                //btnLetterSecond.setTextColor(ContextCompat.getColor(this, R.color.white));
            }else if(patternSetting.getSecondOption() == 1){
                secondOption = 1;
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnNumberSecond, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                //btnNumberSecond.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else{
                secondOption = 2;
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnSymbolSecond, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                //btnSymbolSecond.setTextColor(ContextCompat.getColor(this, R.color.white));
            }

            if(patternSetting.getThirdOption() == 0){
                thirdOption = 0;
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                //btnLetterThird.setTextColor(ContextCompat.getColor(this, R.color.white));
            }else if(patternSetting.getThirdOption() == 1){
                thirdOption = 1;
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnNumberThird, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                //btnNumberThird.setTextColor(ContextCompat.getColor(this, R.color.white));
            } else{
                thirdOption = 2;
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnSymbolThird, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                //btnSymbolThird.setTextColor(ContextCompat.getColor(this, R.color.white));
            }

            firstOptionSetting = patternSetting.getFirstOptionSetting();
            secondOptionSetting = patternSetting.getSecondOptionSetting();
            thirdOptionSetting = patternSetting.getThirdOptionSetting();

            ((RadioButton) rGroupFirst.getChildAt(firstOptionSetting)).setChecked(true);
            ((RadioButton) rGroupSecond.getChildAt(secondOptionSetting)).setChecked(true);
            ((RadioButton) rGroupThird.getChildAt(thirdOptionSetting)).setChecked(true);

            tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
        }else{
            setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
            setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
            setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
        }
    }

    private void setupOption(TextView letter, TextView number, TextView symbol, TextView option, int firstOptionSettingText, int secondOptionSettingText, int thirdOptionSettingText, int fourthOptionSettingText, RadioButton rBtnFirst, RadioButton rBtnSecond, RadioButton rBtnThird, RadioButton rBtnFourth, RadioGroup rGroup){
        resetColorOfBtns(letter, number, symbol);
        option.setTextColor(ContextCompat.getColor(this, R.color.white));

        rBtnFirst.setText(getResources().getString(firstOptionSettingText));
        rBtnSecond.setText(getResources().getString(secondOptionSettingText));
        rBtnThird.setText(getResources().getString(thirdOptionSettingText));
        rBtnFourth.setText(getResources().getString(fourthOptionSettingText));

        rGroup.clearCheck();
    }

    private void resetColorOfBtns(TextView letter, TextView number, TextView symbol){
        letter.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
        number.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
        symbol.setTextColor(ContextCompat.getColor(this, R.color.notSelected));
    }

    private void radioButtonSelected(){
        rGroupFirst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnFirstId = rGroupFirst.getCheckedRadioButtonId();
                View rBtnFirst = findViewById(rBtnFirstId);
                firstOptionSetting = rGroupFirst.indexOfChild(rBtnFirst);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });

        rGroupSecond.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnSecondId = rGroupSecond.getCheckedRadioButtonId();
                View rBtnSecond = findViewById(rBtnSecondId);
                secondOptionSetting = rGroupSecond.indexOfChild(rBtnSecond);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });

        rGroupThird.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnThirdId = rGroupThird.getCheckedRadioButtonId();
                View rBtnThird = findViewById(rBtnThirdId);
                thirdOptionSetting = rGroupThird.indexOfChild(rBtnThird);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });
    }

    private void shakeEmpty(){
        Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

        tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));
        tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));
        tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));

        if(firstOptionSetting == -1){
            tvFirstOptionHeadline.startAnimation(animShake);
            tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }

        if(secondOptionSetting == -1){
            tvSecondOptionHeadline.startAnimation(animShake);
            tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }

        if(thirdOptionSetting == -1){
            tvThirdOptionHeadline.startAnimation(animShake);
            tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }
}