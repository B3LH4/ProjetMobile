package com.esprit.rentacar.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "Email")
    private String Email;
    @ColumnInfo(name = "Role")
    private String Role;

    @ColumnInfo(name = "Telephone")
    private String Telephone;



    public User(String Email, String password) {
        this.Email = Email;
        this.password = password;
    }

    public User(String username, String password, String email, String telephone) {
        this.username = username;
        this.password = password;
        this.Email = email;
        this.Telephone = telephone;
    }
    public User(String username, String password, String email, String telephone, String Role) {
        this.username = username;
        this.password = password;
        this.Email = email;
        this.Telephone = telephone;
        this.Role= Role;
    }



    @Ignore
    public User() {
    }
    // Getter and setter for fingerprintDataId


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }


    @Override
    public String toString() {
        return "User{" +

                ", username='" + username + '\'' +
                ", Email='" + Email + '\'' +
                ", password='" + password + '\'' +
                ", Telephone='" + Telephone + '\'' +
                ", Role='" + Role + '\'' +
                '}';
    }
}
