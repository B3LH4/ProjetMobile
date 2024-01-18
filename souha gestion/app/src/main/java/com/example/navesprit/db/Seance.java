package com.example.navesprit.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

@Entity
public class Seance implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "numero_seance")
    public String numero_seance;

    @ColumnInfo(name = "nom_matiere")
    public String nom_matiere;

    @ColumnInfo(name = "date_seance")
    public String date_seance;

    @ColumnInfo(name = "heure_seance")
    public String heure_seance;

    @ColumnInfo(name = "disponibilite_seance")
    public String disponibilite_seance;
}
