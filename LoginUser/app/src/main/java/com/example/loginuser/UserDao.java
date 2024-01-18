package com.example.loginuser;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(UserEntity userEntity);

    @Query("SELECT * from users where mail=(:mail) and password=(:password)")
    UserEntity login(String mail, String password);
}
