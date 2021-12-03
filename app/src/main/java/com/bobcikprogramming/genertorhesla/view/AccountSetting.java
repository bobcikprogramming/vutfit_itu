package com.bobcikprogramming.genertorhesla.view;

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

public class AccountSetting extends AppCompatActivity implements View.OnClickListener{

    private EditText etPasswordOld, etPasswordNewFirst, etPasswordNewSecond;
    private Button btnChange;
    private LinearLayout layoutOldPassword, layoutNewFirstPassword, layoutNewSecondPassword, layoutBackground;
    private ImageView btnShowHidePasswordOld, btnShowHidePasswordNewFirst, btnShowHidePasswordNewSecond, btnCancel;

    private AccountManagement accountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        accountManagement = new AccountManagement();

        setupUI();

        etPasswordOld.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPasswordNewFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        etPasswordNewSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnChange:
                String passwordOld = etPasswordOld.getText().toString();
                String passwordNewFirst = etPasswordNewFirst.getText().toString();
                String passwordNewSecond = etPasswordNewSecond.getText().toString();
                Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

                shakeIfEmpty(passwordOld, passwordNewFirst, passwordNewSecond, animShake);

                int returnValue = accountManagement.changePassword(passwordOld ,passwordNewFirst, passwordNewSecond, this);
                if(returnValue == 0) {
                    Toast.makeText(this, "Heslo úspěšně změněno", Toast.LENGTH_LONG).show();
                    finish();
                } else if (returnValue == -1) {
                    shakeIfEmpty(passwordOld, passwordNewFirst, passwordNewSecond, animShake);
                }else if(returnValue == -2){
                    shake(layoutNewSecondPassword, animShake);
                }else if(returnValue == -3){
                    layoutNewFirstPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
                    etPasswordNewFirst.setText("");

                    layoutNewSecondPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
                    etPasswordNewSecond.setText("");
                    Toast.makeText(this, "Heslo se nepodařilo změnit.", Toast.LENGTH_SHORT).show();
                }else if(returnValue == -4){
                    shake(layoutOldPassword, animShake);
                }

                hideKeyBoard();
                break;
            case R.id.btnShowHidePasswordOld:
                if(!accountManagement.isVisibleOld()){
                    btnShowHidePasswordOld.setImageResource(R.drawable.ic_visible);
                    etPasswordOld.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleOld(true);
                }else{
                    btnShowHidePasswordOld.setImageResource(R.drawable.ic_invisible);
                    etPasswordOld.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleOld(false);
                }
                break;
            case R.id.btnShowHidePasswordNewFirst:
                if(!accountManagement.isVisibleFirst()){
                    btnShowHidePasswordNewFirst.setImageResource(R.drawable.ic_visible);
                    etPasswordNewFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleFirst(true);
                }else{
                    btnShowHidePasswordNewFirst.setImageResource(R.drawable.ic_invisible);
                    etPasswordNewFirst.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleFirst(false);
                }
                break;
            case R.id.btnShowHidePasswordNewSecond:
                if(!accountManagement.isVisibleSecond()){
                    btnShowHidePasswordNewSecond.setImageResource(R.drawable.ic_visible);
                    etPasswordNewSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleSecond(true);
                }else{
                    btnShowHidePasswordNewSecond.setImageResource(R.drawable.ic_invisible);
                    etPasswordNewSecond.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleSecond(false);
                }
                break;
            case R.id.layoutBackground:
                hideKeyBoard();
                break;
            case R.id.btnCancel:
                finish();
                break;
        }
    }

    private void setupUI(){
        etPasswordOld = findViewById(R.id.etPasswordOld);
        etPasswordNewFirst = findViewById(R.id.etPasswordNewFirst);
        etPasswordNewSecond = findViewById(R.id.etPasswordNewSecond);
        layoutOldPassword = findViewById(R.id.layoutOldPassword);
        layoutNewFirstPassword = findViewById(R.id.layoutNewFirstPassword);
        layoutNewSecondPassword = findViewById(R.id.layoutNewSecondPassword);
        layoutBackground = findViewById(R.id.layoutBackground);

        btnChange = findViewById(R.id.btnChange);
        btnShowHidePasswordOld = findViewById(R.id.btnShowHidePasswordOld);
        btnShowHidePasswordNewFirst = findViewById(R.id.btnShowHidePasswordNewFirst);
        btnShowHidePasswordNewSecond = findViewById(R.id.btnShowHidePasswordNewSecond);
        btnCancel = findViewById(R.id.btnCancel);

        btnChange.setOnClickListener(this);
        btnShowHidePasswordOld.setOnClickListener(this);
        btnShowHidePasswordNewFirst.setOnClickListener(this);
        btnShowHidePasswordNewSecond.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
    }

    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    private void shakeIfEmpty(String passwordOld, String passwordNewFirst, String passwordNewSecond, Animation animShake){
        if(passwordOld.isEmpty()){
            shake(layoutOldPassword, animShake);
        }else{
            layoutOldPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
        }

        if(passwordNewFirst.isEmpty()){
            shake(layoutNewFirstPassword, animShake);
        }else{
            layoutNewFirstPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
        }

        if(passwordNewSecond.isEmpty()){
            shake(layoutNewSecondPassword, animShake);
        }else{
            layoutNewSecondPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
        }
    }

    private void shake(LinearLayout toShake, Animation animShake){
        toShake.startAnimation(animShake);
        toShake.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text_error));
    }
}