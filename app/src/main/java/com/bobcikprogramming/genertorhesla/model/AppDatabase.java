package com.bobcikprogramming.genertorhesla.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AccountEntity.class, PatternEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DatabaseDao databaseDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "passwordGeneratorDb")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
