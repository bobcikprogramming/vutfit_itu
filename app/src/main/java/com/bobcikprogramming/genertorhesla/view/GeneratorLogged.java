package com.bobcikprogramming.genertorhesla.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.PatternSetting;

public class GeneratorLogged extends Fragment implements View.OnClickListener{

    private LinearLayout btnRandomPattern, btnOwnPattern;
    private ImageView btnLogout, btnAccSetting;
    private TextView tvRandomPattern, tvOwnPattern;
    private View view;

    public GeneratorLogged() {
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
        view = inflater.inflate(R.layout.fragment_generator_logged, container, false);

        setupUI();

        Bundle bundle = this.getArguments();
        if(bundle != null){
            openManualFromList(bundle);
        }else{
            openRandom();
        }

        return view;
    }

    private void setupUI(){
        btnRandomPattern = view.findViewById(R.id.tabButtonRandomPattern);
        btnOwnPattern = view.findViewById(R.id.tabButtonOwnPattern);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnAccSetting = view.findViewById(R.id.btnAccSetting);
        tvRandomPattern = view.findViewById(R.id.tabTextViewRandomPattern);
        tvOwnPattern = view.findViewById(R.id.tabTextViewOwnPattern);


        btnRandomPattern.setOnClickListener(this);
        btnOwnPattern.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnAccSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabButtonRandomPattern:
                openRandom();
                break;
            case R.id.tabButtonOwnPattern:
                openManual();
                break;
            case R.id.btnLogout:
                getActivity().finish();
                break;
            case R.id.btnAccSetting:
                Intent intent = new Intent(getContext(), AccountSetting.class);
                startActivity(intent);
                break;
        }
    }

    private void resetColor(){
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    private void openRandom(){
        FragmentRandomPattern randomPatternLogged = new FragmentRandomPattern();
        Bundle bundleRandom = new Bundle();
        bundleRandom.putBoolean("logged", true);
        randomPatternLogged.setArguments(bundleRandom);

        getChildFragmentManager().beginTransaction().replace(R.id.layout, randomPatternLogged).commit();
        resetColor();
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
    }

    private void openManual(){
        FragmentManualPattern randomManualLogged = new FragmentManualPattern();
        Bundle bundleManual = new Bundle();
        bundleManual.putBoolean("logged", true);
        randomManualLogged.setArguments(bundleManual);

        getChildFragmentManager().beginTransaction().replace(R.id.layout, randomManualLogged).commit();
        resetColor();
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
    }

    private void openManualFromList(Bundle bundle){
        FragmentManualPattern randomManualLogged = new FragmentManualPattern();
        Bundle bundleManual = new Bundle();
        bundleManual.putBoolean("logged", true);
        bundleManual.putSerializable("pattern", bundle.getSerializable("pattern"));
        randomManualLogged.setArguments(bundleManual);

        getChildFragmentManager().beginTransaction().replace(R.id.layout, randomManualLogged).commit();
        resetColor();
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
    }
}