package com.example.navesprit.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

// AppDatabase.java
@Database(entities = {Seance.class, Chaise.class, Booking.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BookingDao bookingDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "your-database-name")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract SeanceDao seanceDao();


}
