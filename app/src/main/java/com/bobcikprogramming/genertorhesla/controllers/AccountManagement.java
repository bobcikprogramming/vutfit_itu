package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.model.AccountEntity;
import com.bobcikprogramming.genertorhesla.model.AppDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public String getPasswordHash(String password){
        String hashPassword;
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(password.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            hashPassword = hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            hashPassword = null;
        }
        return hashPassword;
    }

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
            return -1; // Vyplněna
        }
    }

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
