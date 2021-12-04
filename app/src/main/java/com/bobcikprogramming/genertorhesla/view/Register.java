package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      Register
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.R;
import com.bobcikprogramming.genertorhesla.controllers.AccountManagement;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private EditText etPasswordFirst, etPasswordSecond;
    private Button btnRegister;
    private LinearLayout layoutFirstPassword, layoutSecondPassword, layoutBackground;
    private ImageView btnShowHidePasswordFirst, btnShowHidePasswordSecond;
    private TextView btnLogin;

    private AccountManagement accountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        accountManagement = new AccountManagement();

        setupUI();

        etPasswordFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPasswordSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegister:
                String passwordFirst = etPasswordFirst.getText().toString();
                String passwordSecond = etPasswordSecond.getText().toString();
                Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

                int returnValue = accountManagement.register(passwordFirst, passwordSecond, this);
                if(returnValue == 0) {
                    Toast.makeText(this, "Účet úspěšně vytvořen", Toast.LENGTH_LONG).show();
                    finish();
                } else if (returnValue == -1) {
                    shakeIfEmpty(passwordFirst, passwordSecond, animShake);
                }else if(returnValue == -2){
                    shake(layoutSecondPassword, animShake);
                }else if(returnValue == -3){
                    layoutFirstPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
                    etPasswordFirst.setText("");

                    layoutSecondPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
                    etPasswordSecond.setText("");
                    Toast.makeText(this, "Účet se nepodařilo vytvořit.", Toast.LENGTH_SHORT).show();
                }else if(returnValue == -4){
                    Toast.makeText(this, "Účet již existuje!", Toast.LENGTH_LONG).show();
                    shake(layoutFirstPassword, animShake);
                    shake(layoutSecondPassword, animShake);
                }

                hideKeyBoard();
                break;
            case R.id.btnShowHidePasswordFirst:
                if(!accountManagement.isVisibleFirst()){
                    btnShowHidePasswordFirst.setImageResource(R.drawable.ic_visible);
                    etPasswordFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleFirst(true);
                }else{
                    btnShowHidePasswordFirst.setImageResource(R.drawable.ic_invisible);
                    etPasswordFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleFirst(false);
                }
                break;
            case R.id.btnShowHidePasswordSecond:
                if(!accountManagement.isVisibleSecond()){
                    btnShowHidePasswordSecond.setImageResource(R.drawable.ic_visible);
                    etPasswordSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleSecond(true);
                }else{
                    btnShowHidePasswordSecond.setImageResource(R.drawable.ic_invisible);
                    etPasswordSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleSecond(false);
                }
                break;
            case R.id.btnLogin:
                finish();
                break;
            case R.id.layoutBackground:
                hideKeyBoard();
                break;
        }
    }

    private void setupUI(){
        etPasswordFirst = findViewById(R.id.etPasswordFirst);
        etPasswordSecond = findViewById(R.id.etPasswordSecond);
        layoutSecondPassword = findViewById(R.id.layoutSecondPassword);
        layoutFirstPassword = findViewById(R.id.layoutFirstPassword);
        layoutBackground = findViewById(R.id.layoutBackground);

        btnRegister = findViewById(R.id.btnRegister);
        btnShowHidePasswordFirst = findViewById(R.id.btnShowHidePasswordFirst);
        btnShowHidePasswordSecond = findViewById(R.id.btnShowHidePasswordSecond);
        btnLogin = findViewById(R.id.btnLogin);

        btnRegister.setOnClickListener(this);
        btnShowHidePasswordFirst.setOnClickListener(this);
        btnShowHidePasswordSecond.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
    }

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void shakeIfEmpty(String passwordFirst, String passwordSecond, Animation animShake){
        if(passwordFirst.isEmpty()){
            shake(layoutFirstPassword, animShake);
        }else{
            layoutFirstPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
        }

        if(passwordSecond.isEmpty()){
            shake(layoutSecondPassword, animShake);
        }else{
            layoutSecondPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
        }
    }

    private void shake(LinearLayout toShake, Animation animShake){
        toShake.startAnimation(animShake);
        toShake.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text_error));
    }
}