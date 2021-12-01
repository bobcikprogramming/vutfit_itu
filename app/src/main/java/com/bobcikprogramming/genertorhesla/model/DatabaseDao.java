package com.bobcikprogramming.genertorhesla.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

@Dao
public interface DatabaseDao {

    @Transaction
    @Query("SELECT * FROM AccountEntity")
    AccountEntity getPassword();

    @Insert
    void insertPassword(AccountEntity account);
}
