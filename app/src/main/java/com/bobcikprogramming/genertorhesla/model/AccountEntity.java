package com.bobcikprogramming.genertorhesla.model;

/**
 * Soubor:      AccountEntity
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AccountEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    public long uidUser;

    @ColumnInfo(name = "password")
    public String password;
}
