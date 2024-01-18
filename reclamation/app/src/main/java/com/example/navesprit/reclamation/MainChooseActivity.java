package com.example.navesprit.reclamation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.navesprit.MainActivity;
import com.example.navesprit.R;

public class MainChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_choose);


        CardView ajouter = findViewById(R.id.home);
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainChooseActivity.this, AddNewReclamationActivity.class), 100);
            }
        });

        CardView afficher = findViewById(R.id.time);
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainChooseActivity.this, MainRecActivity.class), 100);
            }
        });


        CardView acceuil = findViewById(R.id.rendez);
        acceuil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainChooseActivity.this, MainActivity.class), 100);
            }
        });
    }
}