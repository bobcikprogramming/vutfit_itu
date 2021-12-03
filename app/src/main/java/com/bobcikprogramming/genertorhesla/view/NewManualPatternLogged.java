package com.bobcikprogramming.genertorhesla.view;

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

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.Convertor;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.HelperController;
import com.bobcikprogramming.genertorhesla.controllers.HelperHolder;
import com.bobcikprogramming.genertorhesla.controllers.ManualPattern;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;

public class NewManualPatternLogged extends Fragment implements View.OnClickListener{

    private RadioButton rBtnFirstAtFirst, rBtnSecondAtFirst, rBtnThirdAtFirst, rBtnFourthAtFirst, rBtnFirstAtSecond, rBtnSecondAtSecond, rBtnThirdAtSecond, rBtnFourthAtSecond, rBtnFirstAtThird, rBtnSecondAtThird, rBtnThirdAtThird, rBtnFourthAtThird;
    private RadioGroup rGroupFirst, rGroupSecond, rGroupThird;
    private TextView tvPattern;
    private TextView btnLetterFirst, btnNumberFirst, btnSymbolFirst, btnLetterSecond, btnNumberSecond, btnSymbolSecond, btnLetterThird, btnNumberThird, btnSymbolThird, tvFirstOptionHeadline, tvSecondOptionHeadline, tvThirdOptionHeadline;
    private ImageView btnCancel, btnSave, btnFinish;
    private ImageView btnInfoFirstAtFirst, btnInfoSecondAtFirst, btnInfoThirdAtFirst, btnInfoFourthAtFirst, btnInfoFirstAtSecond, btnInfoSecondAtSecond, btnInfoThirdAtSecond, btnInfoFourthAtSecond, btnInfoFirstAtThird, btnInfoSecondAtThird, btnInfoThirdAtThird, btnInfoFourthAtThird;
    private View view;

    private GeneratePassword generate;
    private Convertor convertor;
    private ManualPattern manual;
    private HelperController helper;
    
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
        manual = new ManualPattern();
        helper = new HelperController(getContext());

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

        btnInfoFirstAtFirst = view.findViewById(R.id.btnInfoFirstAtFirst);
        btnInfoSecondAtFirst = view.findViewById(R.id.btnInfoSecondAtFirst);
        btnInfoThirdAtFirst = view.findViewById(R.id.btnInfoThirdAtFirst);
        btnInfoFourthAtFirst = view.findViewById(R.id.btnInfoFourthAtFirst);
        btnInfoFirstAtSecond = view.findViewById(R.id.btnInfoFirstAtSecond);
        btnInfoSecondAtSecond = view.findViewById(R.id.btnInfoSecondAtSecond);
        btnInfoThirdAtSecond = view.findViewById(R.id.btnInfoThirdAtSecond);
        btnInfoFourthAtSecond = view.findViewById(R.id.btnInfoFourthAtSecond);
        btnInfoFirstAtThird = view.findViewById(R.id.btnInfoFirstAtThird);
        btnInfoSecondAtThird = view.findViewById(R.id.btnInfoSecondAtThird);
        btnInfoThirdAtThird = view.findViewById(R.id.btnInfoThirdAtThird);
        btnInfoFourthAtThird = view.findViewById(R.id.btnInfoFourthAtThird);

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

            tvPattern.setText(generate.showPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
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
                manual.setFirstOptionSetting(rGroupFirst.indexOfChild(rBtnFirst));

                tvPattern.setText(generate.showPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });

        rGroupSecond.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnSecondId = rGroupSecond.getCheckedRadioButtonId();
                View rBtnSecond = view.findViewById(rBtnSecondId);
                manual.setSecondOptionSetting(rGroupSecond.indexOfChild(rBtnSecond));

                tvPattern.setText(generate.showPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });

        rGroupThird.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int rBtnThirdId = rGroupThird.getCheckedRadioButtonId();
                View rBtnThird = view.findViewById(rBtnThirdId);
                manual.setThirdOptionSetting(rGroupThird.indexOfChild(rBtnThird));

                tvPattern.setText(generate.showPattern(manual.getFirstOption(), manual.getSecondOption(), manual.getThirdOption(), manual.getFirstOptionSetting(), manual.getSecondOptionSetting(), manual.getThirdOptionSetting()));
            }
        });
    }

    private void shakeEmpty(){
        Animation animShake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);

        tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));
        tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));
        tvThirdOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.description));

        if(manual.getFirstOptionSetting() == -1){
            tvFirstOptionHeadline.startAnimation(animShake);
            tvFirstOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        if(manual.getSecondOptionSetting() == -1){
            tvSecondOptionHeadline.startAnimation(animShake);
            tvSecondOptionHeadline.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
        }

        if(manual.getThirdOptionSetting() == -1){
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

    private void openDialogWindow(HelperHolder info){
        AlertDialog.Builder alert = new AlertDialog.Builder(getContext(), R.style.MyDialog);
        alert.setTitle(info.getHeadline());
        alert.setMessage(info.getText());
        alert.show();
    }
}