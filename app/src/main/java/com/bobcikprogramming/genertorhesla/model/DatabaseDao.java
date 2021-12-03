package com.bobcikprogramming.genertorhesla.model;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DatabaseDao {

    @Transaction
    @Query("SELECT * FROM AccountEntity")
    AccountEntity getPassword();

    @Insert
    void insertPassword(AccountEntity account);

    @Transaction
    @Query("SELECT * FROM PatternEntity")
    List<PatternEntity> getPattern();

    @Transaction
    @Query("SELECT * FROM PatternEntity WHERE pattern_id = :id")
   PatternEntity getPatternById(String id);

    @Insert
    void insertPattern(PatternEntity pattern);

    @Update
    void updateTransaction(AccountEntity account);

    @Query("DELETE FROM PatternEntity WHERE pattern_id = :id")
    void deletePatternById(String id);
}
