package com.bobcikprogramming.genertorhesla.model;

/**
 * Soubor:      PatternEntity
 * Autor:       Pavel Bobčík (xbobci03)
 * Předmět:     ITU - Tvorba uživatelských rozhraní
 * Organizace:  Vysoké učení technické v Brně
 */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PatternEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pattern_id")
    public long uidPattern;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "example")
    public String example;

    @ColumnInfo(name = "first_opetion")
    public int firstOption;

    @ColumnInfo(name = "first_opetion_setting")
    public int firstOptionSetting;

    @ColumnInfo(name = "second_opetion")
    public int secondOption;

    @ColumnInfo(name = "second_opetion_setting")
    public int secondOptionSetting;

    @ColumnInfo(name = "third_opetion")
    public int thirdOption;

    @ColumnInfo(name = "third_opetion_setting")
    public int thirdOptionSetting;
}
