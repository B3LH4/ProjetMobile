package com.example.navesprit.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDao {

    @Query("SELECT * FROM booking")
    List<Booking> getAllBooking();

    @Insert
    void insertBooking(Booking... bookings);

    @Delete
    void delete(Booking booking);

    @Query("UPDATE booking SET etat_booking= :etat_booking WHERE uid = :bookingId")
    void updateById(int bookingId,String etat_booking);

   /* @Query("UPDATE rendezvous SET ancien_user = :ancienuser WHERE uid = :rendezvousId")
    void updateState(int rendezvousId,String ancienuser);*/
}
