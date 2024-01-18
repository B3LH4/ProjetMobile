package com.example.navesprit.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface SeanceDao {

    @Insert
    long insertSeance(Seance seance);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertChaises(List<Chaise> chaises);


    @Transaction
    default void insertSeanceWithChaises(Seance seance, List<Chaise> chaises) {
        long seanceId = insertSeance(seance);
        for (Chaise chaise : chaises) {
            chaise.seanceId = (int) seanceId;
        }
        insertChaises(chaises);
    }


    @Transaction
    @Query("SELECT * FROM Seance")
    List<SeanceWithChaises> getAllSeancesWithChaises();


    @Query("UPDATE Chaise SET disponibilite_chaise = :newDisponibilite WHERE seanceId = :seanceId")
    void updateChaiseDisponibilite(int seanceId, String newDisponibilite);
}
