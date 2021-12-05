package com.bobcikprogramming.genertorhesla.view;

/**
 * Soubor:      Login
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
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

/**
 * Třída sloužící jako View pro přihlášení.
 */
public class Login extends AppCompatActivity implements View.OnClickListener{

    private TextView btnCreateAccount;
    private ImageView btnCancel, btnShowHidePassword;
    private EditText etPassword;
    private Button btnLogin;
    private LinearLayout layoutPassword, layoutBackground;

    private AccountManagement accountManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountManagement = new AccountManagement();

        setupUI();

        etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btnLogin:
                String password = etPassword.getText().toString();
                Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);

                if(!shakeIfEmpty(password, animShake)){
                    if (accountManagement.login(password, this)) {
                        etPassword.setText("");
                        Intent intent = new Intent(Login.this, BottomTabBar.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "Neplatné heslo nebo účet.", Toast.LENGTH_LONG).show();
                        layoutPassword.startAnimation(animShake);
                        layoutPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text_error));
                    }
                }

                hideKeyBoard();
                break;
            case R.id.btnShowHidePassword:
                if(!accountManagement.isVisibleFirst()){
                    btnShowHidePassword.setImageResource(R.drawable.ic_visible);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    accountManagement.setVisibleFirst(true);
                }else{
                    btnShowHidePassword.setImageResource(R.drawable.ic_invisible);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    accountManagement.setVisibleFirst(false);
                }
                break;
            case R.id.btnCancel:
                finish();
                break;
            case R.id.btnCreateAccount:
                etPassword.setText("");
                layoutPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                break;
            case R.id.layoutBackground:
                hideKeyBoard();
                break;
        }
    }

    /**
     * Metoda sloužící pro načtení GUI prvků podle id.
     */
    private void setupUI(){
        etPassword = findViewById(R.id.etPassword);
        layoutPassword = findViewById(R.id.layoutPassword);
        layoutBackground = findViewById(R.id.layoutBackground);

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnShowHidePassword = findViewById(R.id.btnShowHidePassword);
        btnCancel = findViewById(R.id.btnCancel);
        btnLogin = findViewById(R.id.btnLogin);

        btnCreateAccount.setOnClickListener(this);
        btnShowHidePassword.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        layoutBackground.setOnClickListener(this);
    }

    /**
     * Metoda pro spuštění animace a změnu barvy prázdného políčka.
     * @param passwordFirst textový řetězec hesla
     * @param animShake animace pro zatřesení
     * @return true v případě, že je políčko prázdné, jinak false
     */
    private boolean shakeIfEmpty(String passwordFirst, Animation animShake){
        if(passwordFirst.isEmpty()){
            layoutPassword.startAnimation(animShake);
            layoutPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text_error));
            return true;
        }else{
            layoutPassword.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_edit_text));
            return false;
        }
    }

    /**
     * Metoda pro schování klávesnice.
     */
    private void hideKeyBoard(){
        InputMethodManager imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(this.getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }
}