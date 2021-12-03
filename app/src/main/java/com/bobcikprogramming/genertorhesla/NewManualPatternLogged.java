package com.bobcikprogramming.genertorhesla;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.controllers.Convertor;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;

public class NewManualPatternLogged extends Fragment implements View.OnClickListener{

    private RadioButton rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird;
    private RadioGroup rGroupFirst, rGroupSecond, rGroupThird;
    private TextView tvPattern;
    private TextView btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterThird, btnNumberThird, btnSymbolThird, tvFirstOptionHeadline, tvSecondOptionHeadline, tvThirdOptionHeadline;
    private ImageView btnCancel, btnSave, btnFinish;
    private View view;

    private int firstOption, secondOption, thirdOption;
    private int firstOptionSetting, secondOptionSetting, thirdOptionSetting;

    private GeneratePassword generate;
    private Convertor convertor;
    
    public NewManualPatternLogged() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_new_manual_pattern_logged, container, false);

        generate = new GeneratePassword(getContext());
        convertor = new Convertor();

        this.firstOption = 0;
        this.secondOption = 0;
        this.thirdOption = 0;

        this.firstOptionSetting = -1;
        this.secondOptionSetting = -1;
        this.thirdOptionSetting = -1;

        Bundle bundle = this.getArguments();
        generate.setPatternSetting((PatternSetting) bundle.getSerializable("pattern"));

        setupUI();

        showSettingIfNotNull();
        radioButtonSelected();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnSave:
                AnimatedVectorDrawable animatedVectorDrawableSave =
                        (AnimatedVectorDrawable) btnSave.getDrawable();
                animatedVectorDrawableSave.start();

                if(generate.getPatternSetting() != null){
                    openDialogWindow();
                }else{
                    Toast.makeText(getContext(), "Nebyl vytvořen žádný vzor.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnCancel:
                Intent intentCancel = new Intent();
                intentCancel.putExtra("pattern", generate.getPatternSetting());
                getActivity().setResult(RESULT_OK, intentCancel);
                getActivity().finish();
                break;
            case R.id.btnFinish:
                if(generate.getPatternSetting() != null){
                    Intent intentFinish = new Intent();
                    intentFinish.putExtra("pattern", generate.getPatternSetting());
                    getActivity().setResult(RESULT_OK, intentFinish);
                    getActivity().finish();
                }else{
                    AnimatedVectorDrawable animatedVectorDrawableFinish =
                            (AnimatedVectorDrawable) btnFinish.getDrawable();
                    animatedVectorDrawableFinish.start();

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

    private void setupUI(){
        tvPattern = view.findViewById(R.id.tvPattern);
        tvFirstOptionHeadline = view.findViewById(R.id.tvFirstOptionHeadline);
        tvSecondOptionHeadline = view.findViewById(R.id.tvSecondOptionHeadline);
        tvThirdOptionHeadline = view.findViewById(R.id.tvThirdOptionHeadline);

        btnCancel = view.findViewById(R.id.btnCancel);
        btnSave = view.findViewById(R.id.btnSave);
        btnFinish = view.findViewById(R.id.btnFinish);

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        btnLetterFirst = view.findViewById(R.id.btnLetterFirst);
        btnNumberFirst = view.findViewById(R.id.btnNumberFirst);
        btnSymbolFirst = view.findViewById(R.id.btnSymbolFirst);
        btnLetterSecond = view.findViewById(R.id.btnLetterSecond);
        btnNumberSecond = view.findViewById(R.id.btnNumberSecond);
        btnSymbolSecond = view.findViewById(R.id.btnSymbolSecond);
        btnLetterThird = view.findViewById(R.id.btnLetterThird);
        btnNumberThird = view.findViewById(R.id.btnNumberThird);
        btnSymbolThird = view.findViewById(R.id.btnSymbolThird);

        btnLetterFirst.setOnClickListener(this);
        btnNumberFirst.setOnClickListener(this);
        btnSymbolFirst.setOnClickListener(this);
        btnLetterSecond.setOnClickListener(this);
        btnNumberSecond.setOnClickListener(this);
        btnSymbolSecond.setOnClickListener(this);
        btnLetterThird.setOnClickListener(this);
        btnNumberThird.setOnClickListener(this);
        btnSymbolThird.setOnClickListener(this);

        rBtnFirstAtFirst = view.findViewById(R.id.rBtnFirstAtFirst);
        rBtnSecondAtFirst = view.findViewById(R.id.rBtnSecondAtFirst);
        rBtnThirdAtFirst = view.findViewById(R.id.rBtnThirdAtFirst);
        rBtnFourthAtFirst = view.findViewById(R.id.rBtnFourthAtFirst);
        rBtnFirstAtSecond = view.findViewById(R.id.rBtnFirstAtSecond);
        rBtnSecondAtSecond = view.findViewById(R.id.rBtnSecondAtSecond);
        rBtnThirdAtSecond = view.findViewById(R.id.rBtnThirdAtSecond);
        rBtnFourthAtSecond = view.findViewById(R.id.rBtnFourthAtSecond);
        rBtnFirstAtThird = view.findViewById(R.id.rBtnFirstAtThird);
        rBtnSecondAtThird = view.findViewById(R.id.rBtnSecondAtThird);
        rBtnThirdAtThird = view.findViewById(R.id.rBtnThirdAtThird);
        rBtnFourthAtThird = view.findViewById(R.id.rBtnFourthAtThird);

        rGroupFirst = view.findViewById(R.id.rGroupFirst);
        rGroupSecond = view.findViewById(R.id.rGroupSecond);
        rGroupThird = view.findViewById(R.id.rGroupThird);

        rGroupFirst.setOnClickListener(this);
        rGroupSecond.setOnClickListener(this);
        rGroupThird.setOnClickListener(this);
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
        option.setTextColor(ContextCompat.getColor(getContext(), R.color.white));

        rBtnFirst.setText(getResources().getString(firstOptionSettingText));
        rBtnSecond.setText(getResources().getString(secondOptionSettingText));
        rBtnThird.setText(getResources().getString(thirdOptionSettingText));
        rBtnFourth.setText(getResources().getString(fourthOptionSettingText));

        rGroup.clearCheck();
    }

    private void resetColorOfBtns(TextView letter, TextView number, TextView symbol){
        letter.setTextColor(ContextCompat.getColor(getContext(), R.color.notSelected));
        number.setTextColor(ContextCompat.getColor(getContext(), R.color.notSelected));
        symbol.setTextColor(ContextCompat.getColor(getContext(), R.color.notSelected));
    }

    private void radioButtonSelected(){
        rGroupFirst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnFirstId = rGroupFirst.getCheckedRadioButtonId();
                View rBtnFirst = view.findViewById(rBtnFirstId);
                firstOptionSetting = rGroupFirst.indexOfChild(rBtnFirst);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });

        rGroupSecond.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnSecondId = rGroupSecond.getCheckedRadioButtonId();
                View rBtnSecond = view.findViewById(rBtnSecondId);
                secondOptionSetting = rGroupSecond.indexOfChild(rBtnSecond);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });

        rGroupThird.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnThirdId = rGroupThird.getCheckedRadioButtonId();
                View rBtnThird = view.findViewById(rBtnThirdId);
                thirdOptionSetting = rGroupThird.indexOfChild(rBtnThird);

                tvPattern.setText(generate.showPattern(firstOption, secondOption, thirdOption, firstOptionSetting, secondOptionSetting, thirdOptionSetting));
            }
        });
    }

    private void shakeEmpty(){
        Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));
        tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));
        tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));

        if(firstOptionSetting == -1){
            tvFirstOptionHeadline.startAnimation(animShake);
            tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        if(secondOptionSetting == -1){
            tvSecondOptionHeadline.startAnimation(animShake);
            tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        if(thirdOptionSetting == -1){
            tvThirdOptionHeadline.startAnimation(animShake);
            tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }
    }

    private void openDialogWindow(){
        // https://stackoverflow.com/a/20761703
        final EditText edittext = new EditText(getContext());
        // https://www.codegrepper.com/code-examples/java/set+layout+margin+programmatically+android
        final LinearLayout layout = new LinearLayout(getContext());
        layout.setOrientation(LinearLayout.VERTICAL);

        edittext.setHint("Název vzoru");
        edittext.setHintTextColor(ContextCompat.getColor(getContext(), R.color.lightGray));
        LinearLayout.LayoutParams margin = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        margin.setMargins(convertor.getDp(100, getContext()), 0, convertor.getDp(100, getContext()), 0);
        edittext.setLayoutParams(margin);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.MyDialog);
        alert.setMessage("Vložte prosím název vzoru");
        alert.setTitle("Uložit vzor");

        layout.addView(edittext, margin);
        alert.setView(layout);

        alert.setPositiveButton("Uložit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String name = edittext.getText().toString();
                generate.savePatternToDatabase(name);
                Toast.makeText(getContext(), "Vzor uložen.", Toast.LENGTH_SHORT).show();
            }
        });

        alert.setNegativeButton("Zrušit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });

        alert.show();
    }
}