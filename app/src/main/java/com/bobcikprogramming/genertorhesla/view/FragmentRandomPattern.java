package com.bobcikprogramming.genertorhesla.view;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.GeneratePassword;
import com.bobcikprogramming.genertorhesla.controllers.PasswordGenerator;
import com.bobcikprogramming.genertorhesla.controllers.PatternGenerator;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class FragmentRandomPattern extends Fragment implements View.OnClickListener {

    private TextView tvPassword, tvPattern;
    private EditText etPhrase;
    private ImageView btnNewRandomPattern, btnCopy, btnDelete;
    private SwitchMaterial sLetter, sCapLetter, sNumber, sSymbol;
    private LinearLayout layoutBackground, layoutPassword, layoutPasswordScroll;
    private View view;

    private GeneratePassword generate;

    public FragmentRandomPattern() {
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
        view = inflater.inflate(R.layout.fragment_random_pattern, container, false);
        generate = new GeneratePassword(getContext());

        setupUI();
        switchSelect();
        onEditTextChange();

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNewRandomPattern:
                AnimatedVectorDrawable animatedVectorDrawableRefresh =
                        (AnimatedVectorDrawable) btnNewRandomPattern.getDrawable();
                animatedVectorDrawableRefresh.start();

                boolean letter = sLetter.isChecked();
                boolean capLetter = sCapLetter.isChecked();
                boolean number = sNumber.isChecked();
                boolean symbol = sSymbol.isChecked();

                getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
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
        etPhrase = view.findViewById(R.id.etPhrase);
        sLetter = view.findViewById(R.id.sLetter);
        sCapLetter = view.findViewById(R.id.sCapLetter);
        sNumber = view.findViewById(R.id.sNumber);
        sSymbol = view.findViewById(R.id.sSymbol);
        layoutBackground = view.findViewById(R.id.layoutBackground);
        layoutPassword = view.findViewById(R.id.layoutPassword);
        layoutPasswordScroll = view.findViewById(R.id.layoutPasswordScroll);
        btnNewRandomPattern = view.findViewById(R.id.btnNewRandomPattern);
        btnCopy = view.findViewById(R.id.btnCopy);
        btnDelete = view.findViewById(R.id.btnDelete);

        tvPassword.setOnClickListener(this);
        tvPattern.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
        layoutPassword.setOnClickListener(this);
        layoutPasswordScroll.setOnClickListener(this);
        btnNewRandomPattern.setOnClickListener(this);
        btnCopy.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void switchSelect(){
        sLetter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean letter) {
                boolean capLetter = sCapLetter.isChecked();
                boolean number = sNumber.isChecked();
                boolean symbol = sSymbol.isChecked();
                if(letter){
                    getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                }else{
                    sCapLetter.setChecked(false);

                    if(!capLetter && !number && !symbol){
                        tvPattern.setText(getString(R.string.emptyPattern));
                        tvPassword.setText("");
                    }else{
                        getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                    }
                }
            }
        });

        sCapLetter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean capLetter) {
                boolean letter = sLetter.isChecked();
                boolean number = sNumber.isChecked();
                boolean symbol = sSymbol.isChecked();
                if(capLetter){
                    sLetter.setChecked(true);
                    letter = sLetter.isChecked();
                    getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                }else{
                    if(!letter && !number && !symbol){
                        tvPattern.setText(getString(R.string.emptyPattern));
                        tvPassword.setText("");
                    }else{
                        getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                    }
                }
            }
        });

        sNumber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean number) {
                boolean letter = sLetter.isChecked();
                boolean capLetter = sCapLetter.isChecked();
                boolean symbol = sSymbol.isChecked();
                if(number){
                    getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                }else{
                    if(!letter && !number && !symbol){
                        tvPattern.setText(getString(R.string.emptyPattern));
                        tvPassword.setText("");
                    }else{
                        getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                    }
                }
            }
        });

        sSymbol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean symbol) {
                boolean letter = sLetter.isChecked();
                boolean capLetter = sCapLetter.isChecked();
                boolean number = sNumber.isChecked();
                if(symbol){
                    getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                }else{
                    if(!letter && !number && !symbol){
                        tvPattern.setText(getString(R.string.emptyPattern));
                        tvPassword.setText("");
                    }else{
                        getRandomPatternExampleAndPassword(letter, capLetter, number, symbol);
                    }
                }
            }
        });
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

    private void getRandomPatternExampleAndPassword(boolean letter, boolean capLetter, boolean number, boolean symbol){
        generate.setterForRandomPatternValues(letter, capLetter, number, symbol);
        generate.createRandomPatternSetting();
        tvPattern.setText(generate.getPatternExample());
        tvPassword.setText(generate.getPassword(etPhrase.getText().toString()));
    }

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(getActivity().getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}