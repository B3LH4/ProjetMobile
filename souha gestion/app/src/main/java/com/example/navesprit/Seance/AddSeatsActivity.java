package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.navesprit.R;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddSeatsActivity extends AppCompatActivity {

    private EditText seatNumberEditText;
    private EditText seatAvailabilityEditText;
    private Button addSeatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seats);///zid cration taa btn

        seatNumberEditText = findViewById(R.id.editTextSeatNumber);
        seatAvailabilityEditText = findViewById(R.id.editTextSeatAvailability);
        addSeatButton = findViewById(R.id.buttonAddSeat);

        addSeatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //addSeatToDatabase();
            }
        });
    }


}
