package com.esprit.rentacar.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "salles",
        foreignKeys = @ForeignKey(
                entity = User.class,
                parentColumns = "id",
                childColumns = "userId",
                onDelete = ForeignKey.CASCADE
        )
)
public class Salles {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Idsalle")
    private long Idsalle;
    @ColumnInfo(name = "userId")
    private long userId;
    @ColumnInfo(name = "nom_salle")
    private String nom_salle;
    @ColumnInfo(name = "desc_salle")
    private String desc_salle;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "gps_salle")
    private String gps_salle;
    @ColumnInfo(name = "capacite_salle")
    private double capacite_salle;
    @ColumnInfo(name = "prix_salle")
    private float prix_salle;


    //Getters
    public long getIdsalle() {
        return Idsalle;
    }

    public long getUserId() {
        return userId;
    }

    public String getNom_salle() {
        return nom_salle;
    }

    public String getDesc_salle() {
        return desc_salle;
    }

    public String getGps_salle() {
        return gps_salle;
    }

    public double getCapacite_salle() {
        return capacite_salle;
    }

    public float getPrix_salle() {
        return prix_salle;
    }

    //Setter


    public void setIdsalle(long idsalle) {
        Idsalle = idsalle;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
    public void setPrix_salle(float prix_salle) {
        this.prix_salle = prix_salle;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //constructor
    public Salles(String nom_salle, String desc_salle,String date, String gps_salle, double capacite_salle, float prix_salle) {
        this.nom_salle = nom_salle;
        this.desc_salle = desc_salle;
        this.date = date;
        this.gps_salle = gps_salle;
        this.capacite_salle = capacite_salle;
        this.prix_salle = prix_salle;
    }

    public Salles(long userId, String nomSalle, String description, String date, String gps_salle, double capacite_salle, float prix_salle) {
        this.userId = userId;
        this.nom_salle = nomSalle;
        this.desc_salle = description;
        this.date = date;
        this.gps_salle = gps_salle;
        this.capacite_salle = capacite_salle;
        this.prix_salle = prix_salle;
    }
}
