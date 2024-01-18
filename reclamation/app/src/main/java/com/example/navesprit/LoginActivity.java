package com.example.navesprit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navesprit.reclamation.admin.ReclamationAdminActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button adminButton = findViewById(R.id.button3);
        Button userButton = findViewById(R.id.button4);

        adminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle admin button click
                // For example, start an activity for admin login
                startActivity(new Intent(LoginActivity.this, ReclamationAdminActivity.class));
            }
        });

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle user button click
                // For example, start an activity for user login
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }
}
