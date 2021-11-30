package com.bobcikprogramming.genertorhesla;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.controllers.PasswordGenerator;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;
import com.bobcikprogramming.genertorhesla.controllers.PatternSettingManualValues;

public class FragmentManualPattern extends Fragment implements View.OnClickListener{

    private TextView tvPassword, tvPattern, tvFirstOption, tvSecondOption, tvThirdOption, tvFirstOptionSetting, tvSecondOptionSetting, tvThirdOptionSetting;
    private EditText etPhrase;
    private ImageView btnNewManualPattern, btnCopy, btnDelete;
    private LinearLayout layoutPattern, layoutEmptyPattern, layoutBackground, layoutPassword, layoutPasswordScroll;
    private View view;

    private PatternSetting patternSetting = null;

    public FragmentManualPattern() {
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
        view = inflater.inflate(R.layout.fragment_manual_pattern, container, false);

        setupUI();
        if(patternSetting == null) {
            showHidePatternEmpty(View.VISIBLE, View.GONE);
        }

        onEditTextChange();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnNewManualPattern:
            case R.id.layoutEmptyPattern:
                Intent newManualPattern = new Intent(getContext(), NewManualPattern.class);
                newManualPattern.putExtra("logged", false);
                newManualPattern.putExtra("patternSetting", patternSetting);
                manualPattern.launch(newManualPattern);
                break;
            case R.id.btnDelete:
                AnimatedVectorDrawable animatedVectorDrawableDelete =
                        (AnimatedVectorDrawable) btnDelete.getDrawable();
                animatedVectorDrawableDelete.start();

                etPhrase.setText("");
                break;
            case R.id.btnCopy:
                AnimatedVectorDrawable animatedVectorDrawableCopy =
                        (AnimatedVectorDrawable) btnCopy.getDrawable();
                animatedVectorDrawableCopy.start();

                ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(
                        "Heslo",
                        tvPassword.getText().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(getContext(), "Heslo bylo zkopírováno", Toast.LENGTH_SHORT).show();
            case R.id.tvPassword:
            case R.id.tvPattern:
            case R.id.layoutPassword:
            case R.id.layoutPasswordScroll:
            case R.id.layoutBackground:
                hideKeyBoard();
                break;
        }

    }

    private void setupUI(){
        tvPassword = view.findViewById(R.id.tvPassword);
        tvPattern = view.findViewById(R.id.tvPattern);
        tvFirstOption = view.findViewById(R.id.tvFirstOption);
        tvSecondOption = view.findViewById(R.id.tvSecondOption);
        tvThirdOption = view.findViewById(R.id.tvThirdOption);
        tvFirstOptionSetting = view.findViewById(R.id.tvFirstOptionSetting);
        tvSecondOptionSetting = view.findViewById(R.id.tvSecondOptionSetting);
        tvThirdOptionSetting = view.findViewById(R.id.tvThirdOptionSetting);
        etPhrase = view.findViewById(R.id.etPhrase);
        layoutBackground = view.findViewById(R.id.layoutBackground);
        layoutPassword = view.findViewById(R.id.layoutPassword);
        layoutPasswordScroll = view.findViewById(R.id.layoutPasswordScroll);
        layoutEmptyPattern = view.findViewById(R.id.layoutEmptyPattern);
        layoutPattern = view.findViewById(R.id.layoutPattern);
        btnNewManualPattern = view.findViewById(R.id.btnNewManualPattern);
        btnCopy = view.findViewById(R.id.btnCopy);
        btnDelete = view.findViewById(R.id.btnDelete);

        tvPassword.setOnClickListener(this);
        tvPattern.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
        layoutPassword.setOnClickListener(this);
        layoutPasswordScroll.setOnClickListener(this);
        layoutEmptyPattern.setOnClickListener(this);
        btnNewManualPattern.setOnClickListener(this);
        btnCopy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    ActivityResultLauncher<Intent> manualPattern = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    patternSetting = (PatternSetting) data.getSerializableExtra("pattern");

                    if(patternSetting == null){
                        showHidePatternEmpty(View.VISIBLE, View.GONE);
                        tvPattern.setText("Vzor nenastaven");
                        tvPassword.setText("");
                    }else {
                        showHidePatternEmpty(View.GONE, View.VISIBLE);

                        PatternSettingManualValues manualValues = getManualSettingValues();
                        tvFirstOption.setText(manualValues.getFirstOption());
                        tvSecondOption.setText(manualValues.getSecondOption());
                        tvThirdOption.setText(manualValues.getThirdOption());

                        tvFirstOptionSetting.setText(manualValues.getFirstOptionSetting());
                        tvSecondOptionSetting.setText(manualValues.getSecondOptionSetting());
                        tvThirdOptionSetting.setText(manualValues.getThirdOptionSetting());

                        PasswordGenerator generatorPattern = new PasswordGenerator("Motocykl", patternSetting, getContext());
                        tvPattern.setText(generatorPattern.genereta());

                        PasswordGenerator generatorPassword = new PasswordGenerator(etPhrase.getText().toString(), patternSetting, getContext());
                        if (!generatorPassword.editPhrase()) {
                            tvPassword.setText("");
                        } else {

                            tvPassword.setText(generatorPassword.genereta());
                        }
                    }
                }
            });

    private void showHidePatternEmpty(int emptyPatternVisibility, int patternVisibility){
        layoutEmptyPattern.setVisibility(emptyPatternVisibility);
        layoutPattern.setVisibility(patternVisibility);
    }

    private PatternSettingManualValues getManualSettingValues(){
        PatternSettingManualValues manualSettingValues = new PatternSettingManualValues();
        if(patternSetting.getFirstOption() == 0){
            manualSettingValues.setFirstOption("Písmena");
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }else if(patternSetting.getFirstOption() == 1){
            manualSettingValues.setFirstOption("Číslice");
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }else{
            manualSettingValues.setFirstOption("Znaky");
            manualSettingValues.setFirstOptionSetting(getManualOptionSetting(patternSetting.getFirstOption(), patternSetting.getFirstOptionSetting()));
        }

        if(patternSetting.getSecondOption() == 0){
            manualSettingValues.setSecondOption("Písmena");
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }else if(patternSetting.getSecondOption() == 1){
            manualSettingValues.setSecondOption("Číslice");
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }else{
            manualSettingValues.setSecondOption("Znaky");
            manualSettingValues.setSecondOptionSetting(getManualOptionSetting(patternSetting.getSecondOption(), patternSetting.getSecondOptionSetting()));
        }

        if(patternSetting.getThirdOption() == 0){
            manualSettingValues.setThirdOption("Písmena");
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }else if(patternSetting.getThirdOption() == 1){
            manualSettingValues.setThirdOption("Číslice");
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }else{
            manualSettingValues.setThirdOption("Znaky");
            manualSettingValues.setThirdOptionSetting(getManualOptionSetting(patternSetting.getThirdOption(), patternSetting.getThirdOptionSetting()));
        }

        return manualSettingValues;
    }

    private String getManualOptionSetting(int option, int setting){
        if(option == 0){
            if(setting == 0){
                return getString(R.string.letterFirst);
            }else if(setting == 1){
                return getString(R.string.letterSecond);
            }else if(setting == 2){
                return getString(R.string.letterThird);
            }else{
                return getString(R.string.letterFourth);
            }
        }else if(option == 1){
            if(setting == 0){
                return getString(R.string.numberFirst);
            }else if(setting == 1){
                return getString(R.string.numberSecond);
            }else if(setting == 2){
                return getString(R.string.numberThird);
            }else{
                return getString(R.string.numberFourth);
            }
        }else{
            if(setting == 0){
                return getString(R.string.symbolFirst);
            }else if(setting == 1){
                return getString(R.string.symbolSecond);
            }else if(setting == 2){
                return getString(R.string.symbolThird);
            }else{
                return getString(R.string.symbolFourth);
            }
        }
    }

    private void onEditTextChange(){
        etPhrase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(patternSetting == null){
                    tvPassword.setText("");
                }else {
                    PasswordGenerator generator = new PasswordGenerator(etPhrase.getText().toString(), patternSetting, getContext());
                    if (!generator.editPhrase()) {
                        tvPassword.setText("");
                    } else {
                        tvPassword.setText(generator.genereta());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(getActivity().getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}