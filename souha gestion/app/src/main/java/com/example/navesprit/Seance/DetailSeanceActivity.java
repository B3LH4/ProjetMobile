package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Booking;
import com.example.navesprit.db.BookingDao;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.SeanceWithChaises;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.navesprit.R;
import com.example.navesprit.Seance.ViewBookingActivity;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Booking;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.SeanceWithChaises;
import java.lang.ref.WeakReference;

public class DetailSeanceActivity extends AppCompatActivity {

    SeanceWithChaises seanceWithChaises;

    private TextView textView2, textView4, textView6, textView8, textView10;
    private Button button, button2;

    private ImageView imageView;

    String abc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_seance);

        seanceWithChaises = (SeanceWithChaises) getIntent().getSerializableExtra("seanceWithChaises");

        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        textView10 = findViewById(R.id.textView10);
        imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.baseline_checklist_24);

        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("chaise")) {
            Chaise selectedChaise = (Chaise) intent.getSerializableExtra("chaise");//orsque vous démarrez cette activité à partir d'une autre activité et que vous souhaitez transmettre des informations sur une chaise sélectionnée

            String chaiseId = selectedChaise.numero_chaise;
            abc = chaiseId;
            String disponibility = selectedChaise.disponibilite_chaise;

            textView2.setText("test");
            textView4.setText(chaiseId);
            textView6.setText(seanceWithChaises.seance.numero_seance);
            textView8.setText(seanceWithChaises.seance.date_seance);
            textView10.setText(seanceWithChaises.seance.heure_seance);

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNewRdv("test", seanceWithChaises.seance.numero_seance, seanceWithChaises.seance.date_seance,
                        seanceWithChaises.seance.heure_seance, abc);
            }




        });
    }

    private void saveNewRdv(String docName, String description, String etat, String specialite, String abc) {
        new SaveRdvAsyncTask(this).execute(docName, description, etat, specialite, abc);
    }

    private static class SaveRdvAsyncTask extends AsyncTask<String, Void, Void> {
        private WeakReference<Context> contextRef;

        SaveRdvAsyncTask(Context context) {
            contextRef = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(String... params) {
            Context context = contextRef.get();
            if (context == null) {
                return null;
            }

            String docName = params[0];
            String description = params[1];
            String etat = params[2];
            String specialite = params[3];
            String abc = params[4];

            AppDatabase db = AppDatabase.getInstance(context.getApplicationContext());

            Booking booking = new Booking();
            booking.nom_user = docName;
            booking.num_seance = description;
            booking.date_seance = etat;
            booking.heure_seance = specialite;
            booking.num_chaise = abc;
            booking.etat_booking="non traitèe";

            db.bookingDao().insertBooking(booking);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Context context = contextRef.get();
            if (context != null) {
                Intent intent = new Intent(context, PickActivity.class);
                context.startActivity(intent);
            }        }
    }
}
