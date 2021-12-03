package com.bobcikprogramming.genertorhesla.view;

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

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;
import com.bobcikprogramming.genertorhesla.controllers.PatternSettingManualValues;

public class FragmentManualPattern extends Fragment implements View.OnClickListener{

    private TextView tvPassword, tvPattern, tvFirstOption, tvSecondOption, tvThirdOption, tvFirstOptionSetting, tvSecondOptionSetting, tvThirdOptionSetting;
    private EditText etPhrase;
    private ImageView btnNewManualPattern, btnCopy, btnDelete;
    private LinearLayout layoutPattern, layoutEmptyPattern, layoutBackground, layoutPassword, layoutPasswordScroll;
    private View view;

    private GeneratePassword generate;
    private boolean logged;

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
        generate = new GeneratePassword(getContext());

        Bundle bundle = this.getArguments();
        logged = bundle != null && bundle.getBoolean("logged", false);

        setupUI();
        setPatternFromList(bundle);

        if(generate.getPatternSetting() == null) {
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
                if(!logged) {
                    Intent newManualPattern = new Intent(getContext(), NewManualPattern.class);
                    newManualPattern.putExtra("logged", false);
                    newManualPattern.putExtra("pattern", generate.getPatternSetting());
                    manualPattern.launch(newManualPattern);
                }else{
                    Intent newManualPatternLogged = new Intent(getContext(), BottomTabBarManualPatternLogged.class);
                    newManualPatternLogged.putExtra("pattern", generate.getPatternSetting());
                    manualPattern.launch(newManualPatternLogged);
                }
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
                Toast.makeText(getContext(), getString(R.string.passCopied), Toast.LENGTH_SHORT).show();
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
                    generate.setPatternSetting((PatternSetting) data.getSerializableExtra("pattern"));

                    if(generate.getPatternSetting() == null){
                        showHidePatternEmpty(View.VISIBLE, View.GONE);
                        tvPattern.setText(getString(R.string.emptyPattern));
                        tvPassword.setText("");
                    }else {
                        showHidePatternEmpty(View.GONE, View.VISIBLE);

                        PatternSettingManualValues manualValues = generate.getManualSettingValues();
                        setupUIForPattern(manualValues);
                    }
                }
            });

    private void showHidePatternEmpty(int emptyPatternVisibility, int patternVisibility){
        layoutEmptyPattern.setVisibility(emptyPatternVisibility);
        layoutPattern.setVisibility(patternVisibility);
    }

    private void onEditTextChange(){
        etPhrase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                tvPassword.setText(generate.getPassword(etPhrase.getText().toString()));
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

    private void setupUIForPattern(PatternSettingManualValues manualValues){
        tvFirstOption.setText(manualValues.getFirstOption());
        tvSecondOption.setText(manualValues.getSecondOption());
        tvThirdOption.setText(manualValues.getThirdOption());

        tvFirstOptionSetting.setText(manualValues.getFirstOptionSetting());
        tvSecondOptionSetting.setText(manualValues.getSecondOptionSetting());
        tvThirdOptionSetting.setText(manualValues.getThirdOptionSetting());

        tvPattern.setText(generate.getPatternExample());
        tvPassword.setText(generate.getPassword(etPhrase.getText().toString()));
    }

    private void setPatternFromList(Bundle bundle){
        PatternSetting patternSetting = (bundle != null) ? (PatternSetting) bundle.getSerializable("pattern") : null;
        generate.setPatternSetting(patternSetting);

        if(patternSetting != null){
            PatternSettingManualValues manualValues = generate.getManualSettingValues();
            setupUIForPattern(manualValues);
        }
    }
}