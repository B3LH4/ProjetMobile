package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.navesprit.MainActivity;
import com.example.navesprit.R;

public class PickActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick);

        CardView ajouter = findViewById(R.id.home);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PickActivity.this, ShowSeancesActivity.class), 100);
            }
        });

        CardView afficher = findViewById(R.id.time);
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PickActivity.this, ViewBookingActivity.class), 100);
            }
        });


        CardView acceuil = findViewById(R.id.rendez);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(PickActivity.this, MainActivity.class), 100);
            }
        });
    }
}