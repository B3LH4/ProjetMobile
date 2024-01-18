package com.example.navesprit.db;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReclamationDao {

    @Query("SELECT * FROM reclamation")
    List<Reclamation> getAllReclamations();

    @Insert
    void insertReclamation(Reclamation... reclamations);

    @Delete
    void delete(Reclamation reclamation);

    @Query("UPDATE reclamation SET user_name = :userName, description = :description, etat = :etat, image = :img, datereclam = :daterec, heurereclam = :heurerec WHERE uid = :reclamationId")
    void updateById(int reclamationId, String userName, String description, String etat, String img, String daterec, String heurerec);

    @Query("UPDATE reclamation SET etat = :etat WHERE uid = :reclamationId")
    void updateState(int reclamationId,String etat);
}

