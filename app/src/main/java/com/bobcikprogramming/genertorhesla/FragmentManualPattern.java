package com.bobcikprogramming.genertorhesla;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FragmentManualPattern extends Fragment implements View.OnClickListener{

    private TextView tvPassword, tvPattern;
    private EditText etPhrase;
    private ImageView btnNewManualPattern, btnCopy, btnDelete;
    private LinearLayout layoutPattern, layoutEmptyPattern, layoutBackground, layoutPassword, layoutPasswordScroll;
    private View view;

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

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnNewManualPattern:
                Intent newManualPattern = new Intent(getContext(), NewManualPattern.class);
                newManualPattern.putExtra("logged", false);
                manualPattern.launch(newManualPattern);
        }

    }

    private void setupUI(){
        tvPassword = view.findViewById(R.id.tvPassword);
        tvPattern = view.findViewById(R.id.tvPattern);
        etPhrase = view.findViewById(R.id.etPhrase);
        layoutBackground = view.findViewById(R.id.layoutBackground);
        layoutPassword = view.findViewById(R.id.layoutPassword);
        layoutPasswordScroll = view.findViewById(R.id.layoutPasswordScroll);
        btnNewManualPattern = view.findViewById(R.id.btnNewManualPattern);
        btnCopy = view.findViewById(R.id.btnCopy);
        btnDelete = view.findViewById(R.id.btnDelete);

        tvPassword.setOnClickListener(this);
        tvPattern.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
        layoutPassword.setOnClickListener(this);
        layoutPasswordScroll.setOnClickListener(this);
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
                    /*boolean close = data.getBooleanExtra("close", false);
                    boolean changed = data.getBooleanExtra("changed", false);
                    if(close){
                        Intent intent = new Intent();
                        intent.putExtra("changed", changed);
                        setResult(RESULT_OK, intent );
                        finish();
                    }*/
                }
            });

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(getActivity().getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        }
    }
}