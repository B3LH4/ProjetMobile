package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.Salles;
import com.esprit.rentacar.entity.User;

import java.util.Calendar;

public class SalleActivity extends AppCompatActivity {
    TextView tvSalle;
    RatingBar rbStars;
    Button CancelBtn, btnSend;
    EditText EditNomSalle, EditDescSalle, Editgpssalle, Editcapacite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle);

        CancelBtn = findViewById(R.id.Cancel);
        btnSend = findViewById(R.id.btnSend);
        tvSalle = findViewById(R.id.tvSalle);
        rbStars = findViewById(R.id.rbStars);
        EditNomSalle = findViewById(R.id.EditNomSalle);
        EditDescSalle = findViewById(R.id.EditDescSalle);
        Editgpssalle = findViewById(R.id.Editgpssalle);
        Editcapacite = findViewById(R.id.Editcapacite);

        tvSalle = findViewById(R.id.tvSalle);
        rbStars = findViewById(R.id.rbStars);

        rbStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if (rating == 0) {
                    tvSalle.setText("Very Dissatisfied");
                } else if (rating == 1) {
                    tvSalle.setText("Dissatisfied");
                } else if (rating == 2 || rating == 3) {
                    tvSalle.setText("OK");
                } else if (rating == 4) {
                    tvSalle.setText("Satisfied");
                } else if (rating == 5) {
                    tvSalle.setText(" Very Satisfied");
                }
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salle = new Intent(SalleActivity.this, SalleActivity.class);
                startActivity(salle);
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SalleActivity.this, "Saving Batiment...", Toast.LENGTH_SHORT).show();
                saveSalle();
                insertUser();
                Intent salle = new Intent(SalleActivity.this, ListSalle.class);
                startActivity(salle);
            }
        });
    }

    ////////ajout USER statiquement///////////

    private long insertUser() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);

        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("secure_password");
        user.setEmail("john.doe@example.com");
        user.setTelephone("1234567890");
        user.setRole("user"); // Assurez-vous d'ajouter cette méthode dans votre classe User si elle n'existe pas encore.

        return appDatabase.userDao().insert(user);

    }


    //////////////////////////////////////////






    private void saveSalle() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);

        try {
            long userId = 1;
            Salles salles = new Salles(
                    userId,
                    EditNomSalle.getText().toString(),
                    EditDescSalle.getText().toString(),
                    Calendar.getInstance().getTime().toString(),
                    Editgpssalle.getText().toString(),
                    Double.parseDouble(Editcapacite.getText().toString()),
                    rbStars.getRating()
            );
            Log.d("SalleActivity", "UserID: " + userId);
            Log.d("SalleActivity", "Nom Salle: " + EditNomSalle.getText().toString());
            Log.d("SalleActivity", "Description Salle: " + EditDescSalle.getText().toString());
            Log.d("SalleActivity", "Localisation Salle: " + Editgpssalle.getText().toString());
            Log.d("SalleActivity", "Capacité Salle: " + Editcapacite.getText().toString());
            Log.d("SalleActivity", "Prix Salle: " + rbStars.getRating());


            appDatabase.salleDao().insert(salles);
            Toast.makeText(this, "Batiment added successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error adding Batiment", Toast.LENGTH_SHORT).show();
        }
    }





}
