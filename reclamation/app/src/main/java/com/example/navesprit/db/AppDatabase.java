package com.example.navesprit.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Reclamation.class}, version  = 2 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ReclamationDao reclamationDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "DB_NAME")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}
