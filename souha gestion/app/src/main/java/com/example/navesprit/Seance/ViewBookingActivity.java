package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.navesprit.R;
import com.example.navesprit.Seance.adapter.BookingAdapter;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Booking;
import com.example.navesprit.db.BookingDao;

import java.util.ArrayList;
import java.util.List;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

public class ViewBookingActivity extends AppCompatActivity implements BookingAdapter.OnBookingClickListener {
    private RecyclerView recyclerView;
    private BookingAdapter bookingAdapter;
    private BookingDao bookingDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_booking);


        ImageView logoutIM = findViewById(R.id.homeImageView);
        logoutIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ViewBookingActivity.this, PickActivity.class), 100);
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        bookingDao = appDatabase.bookingDao();

        // Instantiate SeanceAdapter and set its click listener
        bookingAdapter = new BookingAdapter(new ArrayList<>()); // Pass an initial empty list or fetch data from the database

        bookingAdapter.setOnBookingClickListener(this);

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(bookingAdapter);


        new RetrieveavTask().execute();
    }

    @Override
    public void onBookingClick(Booking booking) {
        Intent intent = new Intent(this, DetailBookingActivity.class);
        intent.putExtra("bookingData", booking); // Pass the booking data to the new activity
        startActivity(intent);
    }

    private class RetrieveavTask extends AsyncTask<Void, Void, List<Booking>> {
        @Override
        protected List<Booking> doInBackground(Void... voids) {
            return bookingDao.getAllBooking();
        }

        @Override
        protected void onPostExecute(List<Booking> seances) {
            bookingAdapter.setData(seances);
            bookingAdapter.notifyDataSetChanged(); // Notify the adapter of the data change
        }



    }
}
