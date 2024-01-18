package com.esprit.rentacar.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.rentacar.entity.Salles;

import java.util.List;

@Dao
public interface SalleDao {
    @Insert
    void insert(Salles salle);
    @Update
    void update(Salles salle);
    @Delete
    void delete(Salles salle);
    @Query("SELECT * FROM salles WHERE Idsalle = :Idsalle")
    Salles getSalleDetailsById(long Idsalle);
    @Query("SELECT * FROM salles")
    List<Salles> getAllSalles();

    @Query("UPDATE salles SET nom_salle = :nom_salle, desc_salle = :desc_salle, gps_salle = :gps_salle, capacite_salle = :capacite_salle,prix_salle = :prix_salle WHERE Idsalle = :Idsalle")
    void updateRatingAndContent(long Idsalle, String nom_salle, String desc_salle, String gps_salle, double capacite_salle,float prix_salle);

}
