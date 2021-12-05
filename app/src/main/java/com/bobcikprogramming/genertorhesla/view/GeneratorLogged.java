package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      GeneratorLogged
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

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

import com.bobcikprogramming.genertorhesla.R;

/**
 * Hlavní view přihlášeného uživatele.
 */
public class GeneratorLogged extends Fragment implements View.OnClickListener{

    private LinearLayout btnRandomPattern, btnOwnPattern;
    private ImageView btnLogout, btnAccSetting, btnHelp;
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

    /**
     * Metoda pro načtení GUI prvký na základe id.
     */
    private void setupUI(){
        btnRandomPattern = view.findViewById(R.id.tabButtonRandomPattern);
        btnOwnPattern = view.findViewById(R.id.tabButtonOwnPattern);
        btnLogout = view.findViewById(R.id.btnLogout);
        btnAccSetting = view.findViewById(R.id.btnAccSetting);
        btnHelp = view.findViewById(R.id.btnHelp);
        tvRandomPattern = view.findViewById(R.id.tabTextViewRandomPattern);
        tvOwnPattern = view.findViewById(R.id.tabTextViewOwnPattern);


        btnRandomPattern.setOnClickListener(this);
        btnOwnPattern.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
        btnAccSetting.setOnClickListener(this);
        btnHelp.setOnClickListener(this);
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
                Intent intentAcc = new Intent(getContext(), AccountSetting.class);
                startActivity(intentAcc);
                break;
            case R.id.btnHelp:
                Intent intentHelp = new Intent(getContext(), HelperViewer.class);
                intentHelp.putExtra("logged", true);
                startActivity(intentHelp);
                break;
        }
    }

    /**
     * Metoda pro obnovení barev tlačítek spodní lišty.
     */
    private void resetColor(){
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    /**
     * Metoda pro otevření fragmentu okna náhodného vzoru.
     */
    private void openRandom(){
        FragmentRandomPattern randomPatternLogged = new FragmentRandomPattern();
        Bundle bundleRandom = new Bundle();
        bundleRandom.putBoolean("logged", true);
        randomPatternLogged.setArguments(bundleRandom);

        getChildFragmentManager().beginTransaction().replace(R.id.layout, randomPatternLogged).commit();
        resetColor();
        tvRandomPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
    }

    /**
     * Metoda pro otevření fragmentu okna vlastního vzoru.
     */
    private void openManual(){
        FragmentManualPattern randomManualLogged = new FragmentManualPattern();
        Bundle bundleManual = new Bundle();
        bundleManual.putBoolean("logged", true);
        randomManualLogged.setArguments(bundleManual);

        getChildFragmentManager().beginTransaction().replace(R.id.layout, randomManualLogged).commit();
        resetColor();
        tvOwnPattern.setTextColor(ContextCompat.getColor(getContext(), R.color.navBarSelect));
    }

    /**
     * Metoda pro otevření fragmentu okna vlastního vzoru inicilizovananého ze seznamu uložených vzorů.
     * @param bundle data poskytnuté při vytváření view
     */
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