package com.example.navesprit.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reclamation implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "user_name")
    public String userName;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "datereclam")
    public String datereclam;

    @ColumnInfo(name = "heurereclam")
    public String heurereclam;

    @ColumnInfo(name = "etat")
    public String etat;
}
