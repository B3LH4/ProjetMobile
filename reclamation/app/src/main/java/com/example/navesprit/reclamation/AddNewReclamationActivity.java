package com.example.navesprit.reclamation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.Calendar;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Reclamation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

public class AddNewReclamationActivity extends AppCompatActivity {



    private ImageView imageView;
    private EditText dateInput;
    private EditText heureInput;
    private Button phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        final Button phone = findViewById(R.id.PHONE);
        phone.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:21108700"));
            startActivity(callIntent);
        });

        final EditText firstNameInput = findViewById(R.id.firstNameInput);
        final EditText lastNameInput = findViewById(R.id.lastNameInput);
        dateInput = findViewById(R.id.dateInput);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
       heureInput = findViewById(R.id.heureInput);
        heureInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        //imgInput.setEnabled(false);

        imageView = findViewById(R.id.img);



        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AddNewReclamationActivity.this, MainCameraActivity.class);
                intent.putExtra("username", firstNameInput.getText().toString());
                intent.putExtra("desc", lastNameInput.getText().toString());
                intent.putExtra("date", dateInput.getText().toString());
                intent.putExtra("heure", heureInput.getText().toString());
                startActivity(intent);
            }
        });
    }






    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int monthOfYear, int dayOfMonth) {
                        // Update the EditText with the selected date
                        String selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + selectedYear;
                        dateInput.setText(selectedDate);
                    }
                },
                year, month, day);

        // Show the date picker dialog
        datePickerDialog.show();
    }


    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                        // Update the EditText with the selected time
                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", selectedHour, selectedMinute);
                        heureInput.setText(selectedTime);
                    }
                },
                hour, minute, true);

        // Show the time picker dialog
        timePickerDialog.show();
    }



}
