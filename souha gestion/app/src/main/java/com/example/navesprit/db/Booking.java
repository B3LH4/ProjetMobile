package com.example.navesprit.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Booking implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "nom_user")
    public String nom_user;
    @ColumnInfo(name = "num_seance")
    public String num_seance;

    @ColumnInfo(name = "num_chaise")
    public String num_chaise;
    @ColumnInfo(name = "date_seance")
    public String date_seance;
    @ColumnInfo(name = "heure_seance")
    public String heure_seance;

    @ColumnInfo(name = "etat_booking")
    public String etat_booking;

}