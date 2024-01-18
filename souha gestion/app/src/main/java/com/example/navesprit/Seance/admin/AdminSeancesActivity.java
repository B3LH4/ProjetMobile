package com.example.navesprit.Seance.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.navesprit.MainActivity;
import com.example.navesprit.R;
import com.example.navesprit.Seance.SeanceDetailActivity;
import com.example.navesprit.Seance.ShowSeancesActivity;
import com.example.navesprit.Seance.adapter.SeanceAdapter;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.SeanceDao;
import com.example.navesprit.db.SeanceWithChaises;

import java.util.ArrayList;
import java.util.List;

public class AdminSeancesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SeanceAdapter seanceAdapter;
    private SeanceDao seanceDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_seances);



        Button btnadd = findViewById(R.id.addNewUserButton);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminSeancesActivity.this, AddSeanceActivity.class), 100);
            }
        });


        ImageView logoutIM = findViewById(R.id.homeImageView);
        logoutIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(AdminSeancesActivity.this, AdminPickActivity.class), 100);
            }
        });


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase appDatabase = AppDatabase.getInstance(getApplicationContext());
        seanceDao = appDatabase.seanceDao();

        // Instantiate SeanceAdapter and set its click listener
        seanceAdapter = new SeanceAdapter(new ArrayList<>()); // Pass an initial empty list or fetch data from the database
        seanceAdapter.setOnSeanceClickListener(seanceWithChaises -> {
            // Handle click event, for example, start a new activity

        });

        // Set the adapter to the RecyclerView
        recyclerView.setAdapter(seanceAdapter);

        // Use AsyncTask to retrieve data from the database in the background
        new RetrieveSeancesTask().execute();
    }

    private class RetrieveSeancesTask extends AsyncTask<Void, Void, List<SeanceWithChaises>> {
        @Override
        protected List<SeanceWithChaises> doInBackground(Void... voids) {
            return seanceDao.getAllSeancesWithChaises();
        }

        @Override
        protected void onPostExecute(List<SeanceWithChaises> seances) {
            // Update the adapter with the retrieved data
            seanceAdapter.setData(seances);
            seanceAdapter.notifyDataSetChanged(); // Notify the adapter of the data change
        }
    }
}
