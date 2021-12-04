package com.bobcikprogramming.genertorhesla.controllers;

/**
 * Soubor:      AccountManagement
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import android.content.Context;

import com.bobcikprogramming.genertorhesla.model.AccountEntity;
import com.bobcikprogramming.genertorhesla.model.AppDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Pomocná třída pro práci s View pro přihlášení, registraci a správu účtu.
 */
public class AccountManagement {

    private boolean visibleFirst, visibleSecond, visibleOld;

    public AccountManagement(){
        this.visibleOld = false;
        this.visibleFirst = false;
        this.visibleSecond = false;
    }

    public boolean isVisibleFirst() {
        return visibleFirst;
    }

    public void setVisibleFirst(boolean visibleFirst) {
        this.visibleFirst = visibleFirst;
    }

    public boolean isVisibleSecond() {
        return visibleSecond;
    }

    public void setVisibleSecond(boolean visibleSecond) {
        this.visibleSecond = visibleSecond;
    }

    public boolean isVisibleOld() {
        return visibleOld;
    }

    public void setVisibleOld(boolean visibleOld) {
        this.visibleOld = visibleOld;
    }

    public boolean comparePassword(String first, String second){
        return first.equals(second);
    }

    /**
     * Metoda sloužící pro vytvoření hash hesla obdrženého v parametru.
     * @param password textový řetězec obsahující heslo k hashi
     * @return textový řětezec hashe nebo NULL v případě chyby
     *
     * Metoda byla inspirována z:
     * Zdroj:   Stack Overflow
     * Dotaz:   https://stackoverflow.com/q/3934331
     * Odpověď: https://stackoverflow.com/a/3934409
     * Autor:   Antonio
     * Autor:   https://stackoverflow.com/users/316477/antonio
     * Datum:   14. října 2010
     */
    public String getPasswordHash(String password){
        String hashPassword;
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            hashPassword = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            hashPassword = null;
        }
        return hashPassword;
    }

    /**
     * Metoda sloužící k uložení hesla do databáze, není-li již účet vytvořen.
     * @param password Zahashované heslo
     * @param context View context
     * @return pravdivostní hodnotu true v případě vytvoření, false v případě existence účtu
     */
    public boolean saveToDb(String password, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context);
        if(db.databaseDao().getPassword() == null){
            AccountEntity account = new AccountEntity();

            account.password = password;
            db.databaseDao().insertPassword(account);
            return true;
        }else{
            return false;
        }
    }

    /**
     * Metoda pro zpracování registrace
     * @param first textový řetězec hesla
     * @param second textový řetězec potvrzení hesla
     * @param context View context
     * @return číselnou hodnotu 0 v případě úspěchu nebo odpovídající chyby
     */
    public int register(String first, String second, Context context){
        if(!first.isEmpty() && !second.isEmpty()) {
            if (comparePassword(first, second)) {
                String hashPassword = getPasswordHash(first);
                if (hashPassword != null) {
                    if (saveToDb(hashPassword, context)) {
                        return 0;
                    } else {
                        return -4; // účet již existuje
                    }
                } else {
                    return -3; // chyba při hashování
                }
            } else {
                return -2; // Hesla se neshodují
            }
        }else{
            return -1; // nevyplněna pole
        }
    }

    /**
     * Metoda pro zpracování přihlášení.
     * @param password textový řetězec hesla
     * @param context View context
     * @return pravdivostní hodnotu, zda-li přihlášení proběhlo úspěšně (true), nebo neúspěšně (false)
     */
    public boolean login(String password, Context context){
        AppDatabase db = AppDatabase.getDbInstance(context);
        AccountEntity account = db.databaseDao().getPassword();

        String hashPassword = getPasswordHash(password);

        if(hashPassword != null && account != null && !account.password.isEmpty()){
            if(comparePassword(hashPassword, account.password)){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }

    /**
     * Metoda pro správu účtu.
     * @param old textový řetězec původního hesla
     * @param first textový řetězec nového hesla
     * @param second textový řetězec potvrzení nového hesla
     * @param context View context
     * @return číselnou hodnotu 0 v případě úspěchu nebo odpovídající chyby
     */
    public int changePassword(String old, String first, String second, Context context){
        if(!old.isEmpty() && !first.isEmpty() && !second.isEmpty()) {
            if(!login(old, context)){
                return -4; // Staré heslo není správné
            }else {
                if (comparePassword(first, second)) {
                    String hashPassword = getPasswordHash(first);
                    if (hashPassword != null) {
                        AppDatabase db = AppDatabase.getDbInstance(context);
                        AccountEntity account = db.databaseDao().getPassword();

                        AccountEntity newPassword = new AccountEntity();
                        newPassword.uidUser = account.uidUser;
                        newPassword.password = hashPassword;

                        db.databaseDao().updateTransaction(newPassword);
                        // update
                        return 0;
                    } else {
                        return -3; // chyba při hashování
                    }
                } else {
                    return -2; // Hesla se neshodují
                }
            }
        }else{
            return -1; // Hesla nejsou vyplněna
        }
    }
}
