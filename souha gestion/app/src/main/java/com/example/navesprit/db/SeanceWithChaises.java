package com.example.navesprit.db;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class SeanceWithChaises implements Serializable {

    @Embedded
    public Seance seance;

    @Relation(parentColumn = "uid", entityColumn = "seanceId")
    public List<Chaise> chaises;
}
