package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeSalle extends AppCompatActivity {
    Button salleadd, viewsalle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_salle);

        salleadd = findViewById(R.id.salleadd);
        viewsalle = findViewById(R.id.viewsalle);

        salleadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent salleIntent = new Intent(HomeSalle.this, SalleActivity.class);
                startActivity(salleIntent);
            }
        });

        viewsalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent salleIntent = new Intent(HomeSalle.this, ListSalle.class);
                startActivity(salleIntent);
            }
        });


    }





}