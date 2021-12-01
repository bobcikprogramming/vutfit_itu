package com.bobcikprogramming.genertorhesla;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bobcikprogramming.genertorhesla.view.FragmentManualPattern;
import com.bobcikprogramming.genertorhesla.view.FragmentRandomPattern;

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
        getChildFragmentManager().beginTransaction().replace(R.id.layout, new FragmentRandomPatternLogged()).commit();
        resetColor();
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));

        return view;
    }

    private void setupUI(){
        btnRandomPattern = view.findViewById(R.id.tabButtonRandomPattern);
        btnOwnPattern = view.findViewById(R.id.tabButtonOwnPattern);
        btnLogout = view.findViewById(R.id.btnLogout);
        tvRandomPattern = view.findViewById(R.id.tabTextViewRandomPattern);
        tvOwnPattern = view.findViewById(R.id.tabTextViewOwnPattern);


        btnRandomPattern.setOnClickListener(this);
        btnOwnPattern.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabButtonRandomPattern:
                getChildFragmentManager().beginTransaction().replace(R.id.layout, new FragmentRandomPatternLogged()).commit();
                resetColor();
                tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
                break;
            case R.id.tabButtonOwnPattern:
                getChildFragmentManager().beginTransaction().replace(R.id.layout, new FragmentManualPatternLogged()).commit();
                resetColor();
                tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
                break;
            case R.id.btnLogout:
                getActivity().finish();//TODO
                break;
        }
    }

    private void resetColor(){
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }
}