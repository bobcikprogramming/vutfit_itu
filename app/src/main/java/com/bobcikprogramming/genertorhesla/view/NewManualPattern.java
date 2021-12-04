package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      NewManualPattern
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.HelperController;
import com.bobcikprogramming.genertorhesla.controllers.HelperHolder;
import com.bobcikprogramming.genertorhesla.controllers.ManualPattern;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;


public class NewManualPattern extends AppCompatActivity implements View.OnClickListener{

    private RadioButton rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird;
    private RadioGroup rGroupFirst, rGroupSecond, rGroupThird;
    private TextView tvPattern;
    private TextView btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterThird, btnNumberThird, btnSymbolThird, tvFirstOptionHeadline, tvSecondOptionHeadline, tvThirdOptionHeadline;
    private ImageView btnCancel, btnFinish;
    private ImageView btnInfoFirstAtFirst, btnInfoSecondAtFirst, btnInfoThirdAtFirst, btnInfoFourthAtFirst, btnInfoFirstAtSecond, btnInfoSecondAtSecond, btnInfoThirdAtSecond, btnInfoFourthAtSecond, btnInfoFirstAtThird, btnInfoSecondAtThird, btnInfoThirdAtThird, btnInfoFourthAtThird;

    private GeneratePassword generate;
    private ManualPattern manual;
    private HelperController helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_manual_pattern);
        generate = new GeneratePassword(this);
        manual = new ManualPattern();
        helper = new HelperController(this);

        Bundle extras = getIntent().getExtras();
        generate.setPatternSetting((PatternSetting) extras.getSerializable("pattern"));

        setupUI();

        showSettingIfNotNull();
        radioButtonSelected();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
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
                    AnimatedVectorDrawable animatedVectorDrawableFinish =
                            (AnimatedVectorDrawable) btnFinish.getDrawable();
                    animatedVectorDrawableFinish.start();

                    shakeEmpty();
                }
                break;
            case R.id.btnLetterFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                manual.setFirstOption(0);
                break;
            case R.id.btnNumberFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnNumberFirst, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                manual.setFirstOption(1);
                break;
            case R.id.btnSymbolFirst:
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnSymbolFirst, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
                manual.setFirstOption(2);
                break;
            case R.id.btnLetterSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                manual.setSecondOption(0);
                break;
            case R.id.btnNumberSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnNumberSecond, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                manual.setSecondOption(1);
                break;
            case R.id.btnSymbolSecond:
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnSymbolSecond, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
                manual.setSecondOption(2);
                break;
            case R.id.btnLetterThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                manual.setThirdOption(0);
                break;
            case R.id.btnNumberThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnNumberThird, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                manual.setThirdOption(1);
                break;
            case R.id.btnSymbolThird:
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnSymbolThird, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
                manual.setThirdOption(2);
                break;
            case R.id.btnInfoFirstAtFirst:
                openDialogWindow(helper.getInformation(manual.getFirstOption(),0));
                break;
            case R.id.btnInfoSecondAtFirst:
                openDialogWindow(helper.getInformation(manual.getFirstOption(),1));
                break;
            case R.id.btnInfoThirdAtFirst:
                openDialogWindow(helper.getInformation(manual.getFirstOption(),2));
                break;
            case R.id.btnInfoFourthAtFirst:
                openDialogWindow(helper.getInformation(manual.getFirstOption(),3));
                break;
        case R.id.btnInfoFirstAtSecond:
                openDialogWindow(helper.getInformation(manual.getSecondOption(),0));
                break;
            case R.id.btnInfoSecondAtSecond:
                openDialogWindow(helper.getInformation(manual.getSecondOption(),1));
                break;
            case R.id.btnInfoThirdAtSecond:
                openDialogWindow(helper.getInformation(manual.getSecondOption(),2));
                break;
            case R.id.btnInfoFourthAtSecond:
                openDialogWindow(helper.getInformation(manual.getSecondOption(),3));
                break;
            case R.id.btnInfoFirstAtThird:
                openDialogWindow(helper.getInformation(manual.getThirdOption(),0));
                break;
            case R.id.btnInfoSecondAtThird:
                openDialogWindow(helper.getInformation(manual.getThirdOption(),1));
                break;
            case R.id.btnInfoThirdAtThird:
                openDialogWindow(helper.getInformation(manual.getThirdOption(),2));
                break;
            case R.id.btnInfoFourthAtThird:
                openDialogWindow(helper.getInformation(manual.getThirdOption(),3));
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
        btnFinish = findViewById(R.id.btnFinish);

        btnCancel.setOnClickListener(this);
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

        btnInfoFirstAtFirst = findViewById(R.id.btnInfoFirstAtFirst);
        btnInfoSecondAtFirst = findViewById(R.id.btnInfoSecondAtFirst);
        btnInfoThirdAtFirst = findViewById(R.id.btnInfoThirdAtFirst);
        btnInfoFourthAtFirst = findViewById(R.id.btnInfoFourthAtFirst);
        btnInfoFirstAtSecond = findViewById(R.id.btnInfoFirstAtSecond);
        btnInfoSecondAtSecond = findViewById(R.id.btnInfoSecondAtSecond);
        btnInfoThirdAtSecond = findViewById(R.id.btnInfoThirdAtSecond);
        btnInfoFourthAtSecond = findViewById(R.id.btnInfoFourthAtSecond);
        btnInfoFirstAtThird = findViewById(R.id.btnInfoFirstAtThird);
        btnInfoSecondAtThird = findViewById(R.id.btnInfoSecondAtThird);
        btnInfoThirdAtThird = findViewById(R.id.btnInfoThirdAtThird);
        btnInfoFourthAtThird = findViewById(R.id.btnInfoFourthAtThird);

        btnInfoFirstAtFirst.setOnClickListener(this);
        btnInfoSecondAtFirst.setOnClickListener(this);
        btnInfoThirdAtFirst.setOnClickListener(this);
        btnInfoFourthAtFirst.setOnClickListener(this);
        btnInfoFirstAtSecond.setOnClickListener(this);
        btnInfoSecondAtSecond.setOnClickListener(this);
        btnInfoThirdAtSecond.setOnClickListener(this);
        btnInfoFourthAtSecond.setOnClickListener(this);
        btnInfoFirstAtThird.setOnClickListener(this);
        btnInfoSecondAtThird.setOnClickListener(this);
        btnInfoThirdAtThird.setOnClickListener(this);
        btnInfoFourthAtThird.setOnClickListener(this);
    }

    private void showSettingIfNotNull(){
        PatternSetting patternSetting = generate.getPatternSetting();

        if(patternSetting != null){
            if(patternSetting.getFirstOption() == 0){
                manual.setFirstOption(0);
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
            }else if(patternSetting.getFirstOption() == 1){
                manual.setFirstOption(1);
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnNumberFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
            } else{
                manual.setFirstOption(2);
                setupOption(btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnSymbolFirst, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rGroupFirst);
            }

            if(patternSetting.getSecondOption() == 0){
                manual.setSecondOption(0);
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterSecond, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
            }else if(patternSetting.getSecondOption() == 1){
                manual.setSecondOption(1);
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnNumberSecond, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
            } else{
                manual.setSecondOption(2);
                setupOption(btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnSymbolSecond, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rGroupSecond);
            }

            if(patternSetting.getThirdOption() == 0){
                manual.setThirdOption(0);
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnLetterThird, R.string.letterFirst, R.string.letterSecond, R.string.letterThird, R.string.letterFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
            }else if(patternSetting.getThirdOption() == 1){
                manual.setThirdOption(1);
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnNumberThird, R.string.numberFirst, R.string.numberSecond, R.string.numberThird, R.string.numberFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
            } else{
                manual.setThirdOption(2);
                setupOption(btnLetterThird, btnNumberThird, btnSymbolThird, btnSymbolThird, R.string.symbolFirst, R.string.symbolSecond, R.string.symbolThird, R.string.symbolFourth, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird, rGroupThird);
            }

            manual.setFirstOptionSetting(patternSetting.getFirstOptionSetting());
            manual.setSecondOptionSetting(patternSetting.getSecondOptionSetting());
            manual.setThirdOptionSetting(patternSetting.getThirdOptionSetting());

            ((RadioButton) rGroupFirst.getChildAt(manual.getFirstOptionSetting())).setChecked(true);
            ((RadioButton) rGroupSecond.getChildAt(manual.getSecondOptionSetting())).setChecked(true);
            ((RadioButton) rGroupThird.getChildAt(manual.getThirdOptionSetting())).setChecked(true);

            tvPattern.setText(generate.showManualPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
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
                manual.setFirstOptionSetting(rGroupFirst.indexOfChild(rBtnFirst));

                tvPattern.setText(generate.showManualPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });

        rGroupSecond.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnSecondId = rGroupSecond.getCheckedRadioButtonId();
                View rBtnSecond = findViewById(rBtnSecondId);
                manual.setSecondOptionSetting(rGroupSecond.indexOfChild(rBtnSecond));

                tvPattern.setText(generate.showManualPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });

        rGroupThird.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnThirdId = rGroupThird.getCheckedRadioButtonId();
                View rBtnThird = findViewById(rBtnThirdId);
                manual.setThirdOptionSetting(rGroupThird.indexOfChild(rBtnThird));

                tvPattern.setText(generate.showManualPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });
    }

    private void shakeEmpty(){
        Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

        tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));
        tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));
        tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.description));

        if(manual.getFirstOptionSetting() == -1){
            tvFirstOptionHeadline.startAnimation(animShake);
            tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }

        if(manual.getSecondOptionSetting() == -1){
            tvSecondOptionHeadline.startAnimation(animShake);
            tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }

        if(manual.getThirdOptionSetting() == -1){
            tvThirdOptionHeadline.startAnimation(animShake);
            tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(this, R.color.red));
        }
    }

    private void openDialogWindow(HelperHolder info){
        AlertDialog.Builder alert = new AlertDialog.Builder(this, R.style.MyDialog);
        alert.setTitle(info.getHeadline());
        alert.setMessage(info.getText());
        alert.show();
    }
}