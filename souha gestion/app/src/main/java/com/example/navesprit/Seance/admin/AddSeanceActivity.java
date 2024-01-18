package com.example.navesprit.Seance.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.navesprit.R;
import com.example.navesprit.Seance.DetailSeanceActivity;
import com.example.navesprit.Seance.PickActivity;
import com.example.navesprit.Seance.ShowSeancesActivity;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.Seance;
import com.example.navesprit.db.SeanceDao;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.List;

import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.Seance;
import com.example.navesprit.db.SeanceDao;
import com.example.navesprit.db.SeanceWithChaises;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddSeanceActivity extends AppCompatActivity {

    private EditText numeroSeanceEditText;
    private EditText nomMatiereEditText;
    private EditText dateSeanceEditText;
    private EditText heureSeanceEditText;
    private EditText disponibiliteSeanceEditText;
    private Button addSeanceButton;
    private SeanceDao seanceDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_seance);

        // Initialize your views
        numeroSeanceEditText = findViewById(R.id.editTextNumeroSeance);
        nomMatiereEditText = findViewById(R.id.editTextNomMatiere);

        dateSeanceEditText = findViewById(R.id.editTextDateSeance);
        dateSeanceEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        heureSeanceEditText = findViewById(R.id.editTextHeureSeance);
        heureSeanceEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        addSeanceButton = findViewById(R.id.buttonAddSeance);

        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "your-database-name")
                .allowMainThreadQueries() // Note: You should not do this on the main thread in a real app
                .build();

        seanceDao = appDatabase.seanceDao();

        addSeanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSeance();
            }
        });
    }

    private void addSeance() {
        // Retrieve data from EditText fields
        String numeroSeance = numeroSeanceEditText.getText().toString();
        String nomMatiere = nomMatiereEditText.getText().toString();
        String dateSeance = dateSeanceEditText.getText().toString();
        String heureSeance = heureSeanceEditText.getText().toString();
        // Create a Seance object
        Seance seance = new Seance();
        seance.numero_seance = numeroSeance;
        seance.nom_matiere = nomMatiere;
        seance.date_seance = dateSeance;
        seance.heure_seance = heureSeance;
        seance.disponibilite_seance = "disponible";

        // Create a list of Chaise objects (modify as needed)
        List<Chaise> chaises = new ArrayList<>();
        Chaise chaise1 = new Chaise();
        chaise1.numero_chaise = "1";
        chaise1.disponibilite_chaise = "Available";
        chaises.add(chaise1);

        Chaise chaise2 = new Chaise();
        chaise2.numero_chaise = "2";
        chaise2.disponibilite_chaise = "Available";
        chaises.add(chaise2);

        Chaise chaise3 = new Chaise();
        chaise3.numero_chaise = "3";
        chaise3.disponibilite_chaise = "Available";
        chaises.add(chaise3);

        Chaise chaise4 = new Chaise();
        chaise4.numero_chaise = "4";
        chaise4.disponibilite_chaise = "Available";
        chaises.add(chaise4);

        Chaise chaise5 = new Chaise();
        chaise5.numero_chaise = "5";
        chaise5.disponibilite_chaise = "Available";
        chaises.add(chaise5);

        Chaise chaise6 = new Chaise();
        chaise6.numero_chaise = "6";
        chaise6.disponibilite_chaise = "Available";
        chaises.add(chaise6);

        // Set the list of chaises to the SeanceWithChaises object
        SeanceWithChaises seanceWithChaises = new SeanceWithChaises();
        seanceWithChaises.seance = seance;
        seanceWithChaises.chaises = chaises;

        // Insert the Seance and associated Chaises into the database
        seanceDao.insertSeanceWithChaises(seance, chaises);

     //   seanceDao.insertChaises(chaises);

        // Optionally, you can navigate to another activity or update the UI as needed
        // For example, you can use Toast to show a message indicating success


        Intent intent = new Intent(this, AdminSeancesActivity.class);
        startActivity(intent);

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
                        dateSeanceEditText.setText(selectedDate);
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
                        heureSeanceEditText.setText(selectedTime);
                    }
                },
                hour, minute, true);

        // Show the time picker dialog
        timePickerDialog.show();
    }

}

