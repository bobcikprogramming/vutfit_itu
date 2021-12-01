package com.bobcikprogramming.genertorhesla.controllers;

import android.content.Context;
import android.widget.Toast;

import com.bobcikprogramming.genertorhesla.model.AccountEntity;
import com.bobcikprogramming.genertorhesla.model.AppDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AccountManagement {

    public AccountManagement(){}

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
        String hashPassword = getPasswordHash(password);

        AccountEntity account = db.databaseDao().getPassword();

        if(hashPassword != null && account != null && !account.password.isEmpty()){
            System.out.println(">>>>>>>>>>>>>>>>>>>>sutu");
            System.out.println("zadal: "+ hashPassword);
            System.out.println("z db: "+ account.password);
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>");
            if(comparePassword(hashPassword, account.password)){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
