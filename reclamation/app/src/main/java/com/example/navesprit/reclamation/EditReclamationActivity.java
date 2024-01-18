package com.example.navesprit.reclamation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.ReclamationDao;

import java.util.Calendar;
import java.util.Locale;

public class EditReclamationActivity extends AppCompatActivity {

    private EditText dateInput;
    private EditText heureInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reclamation);



        Intent intent = getIntent();
        String name_user = intent.getStringExtra("username");
        String desc_reclamation = intent.getStringExtra("desc");
        String etat_reclamation = intent.getStringExtra("etat");
        int id_reclamation = intent.getIntExtra("id",0);
        String img = intent.getStringExtra("img");
        String date = intent.getStringExtra("date");
        String heure = intent.getStringExtra("heure");

        final EditText firstNameInput = findViewById(R.id.firstNameInput);
        final EditText lastNameInput = findViewById(R.id.lastNameInput);
        dateInput = findViewById(R.id.dateInput);
        dateInput.setText(date);
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        heureInput = findViewById(R.id.heureInput);
        heureInput.setText(heure);
        heureInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        firstNameInput.setText(name_user);
        lastNameInput.setText(desc_reclamation);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reclamationIdToUpdate = id_reclamation; // Replace with the actual ID
                String updatedEtat = "non traitèe";

                ReclamationDao reclamationDao = AppDatabase.getDbInstance(EditReclamationActivity.this).reclamationDao();

                reclamationDao.updateById(reclamationIdToUpdate, firstNameInput.getText().toString(), lastNameInput.getText().toString(), updatedEtat,img,dateInput.getText().toString(),heureInput.getText().toString());
                Intent intent = new Intent(EditReclamationActivity.this, MainRecActivity.class);
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