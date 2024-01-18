package com.example.navesprit.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        foreignKeys = @ForeignKey(
                entity = Seance.class,
                parentColumns = "uid",
                childColumns = "seanceId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Chaise implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "numero_chaise")
    public String numero_chaise;

    @ColumnInfo(name = "disponibilite_chaise")
    public String disponibilite_chaise;

    @ColumnInfo(name = "seanceId")
    public int seanceId; // Foreign key to link with Seance
}
