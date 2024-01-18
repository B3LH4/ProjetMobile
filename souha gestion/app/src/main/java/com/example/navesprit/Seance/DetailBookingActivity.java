package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Booking;
import com.example.navesprit.db.BookingDao;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.SeanceWithChaises;

import java.lang.ref.WeakReference;

public class DetailBookingActivity extends AppCompatActivity {

    Booking booking;

    private TextView textView2, textView4, textView6, textView8, textView10,textView12;
    private Button button, button2;

    private ImageView imageView;

    int abc = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_booking);


        booking = (Booking) getIntent().getSerializableExtra("bookingData");//

        textView2 = findViewById(R.id.textView2);//relier textview f java maa xml
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        textView10 = findViewById(R.id.textView10);
        textView12 = findViewById(R.id.textView12);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.baseline_checklist_24);
        button = findViewById(R.id.button);

        Intent intent = getIntent();


            int bookingId = booking.uid;


            textView2.setText("test");
            textView4.setText(booking.num_chaise);
            textView6.setText(booking.num_seance);
            textView8.setText(booking.date_seance);
            textView10.setText(booking.heure_seance);
            textView12.setText(booking.etat_booking);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteBookingTask(DetailBookingActivity.this, booking).execute();

            }
        });
    }



    private static class DeleteBookingTask extends AsyncTask<Void, Void, Void> {
        private WeakReference<Context> contextRef;
        private Booking booking;

        DeleteBookingTask(Context context, Booking booking) {
            contextRef = new WeakReference<>(context);
            this.booking = booking;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Context context = contextRef.get();
            if (context != null) {
                AppDatabase.getInstance(context).bookingDao().delete(booking);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            // Handle any UI updates after the database operation is complete
            // For example, starting a new activity
            Context context = contextRef.get();
            if (context != null) {
                Intent intent = new Intent(context, ViewBookingActivity.class);
                context.startActivity(intent);
            }
        }
    }

}
