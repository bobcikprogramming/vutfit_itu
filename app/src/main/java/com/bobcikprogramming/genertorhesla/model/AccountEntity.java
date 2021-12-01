package com.bobcikprogramming.genertorhesla.model;

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
