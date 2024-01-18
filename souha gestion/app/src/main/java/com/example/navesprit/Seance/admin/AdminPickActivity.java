package com.example.navesprit.Seance.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.navesprit.LoginActivity;
import com.example.navesprit.R;
import com.example.navesprit.Seance.ViewBookingActivity;

public class AdminPickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pick);

        CardView ajouter = findViewById(R.id.home);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminPickActivity.this, AdminSeancesActivity.class), 100);
            }
        });

        CardView afficher = findViewById(R.id.time);
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminPickActivity.this, AdminBookingsActivity.class), 100);
            }
        });


        CardView acceuil = findViewById(R.id.rendez);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminPickActivity.this, LoginActivity.class), 100);
            }
        });
    }
}