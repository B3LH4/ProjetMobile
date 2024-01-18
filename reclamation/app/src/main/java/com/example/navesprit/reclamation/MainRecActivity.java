package com.example.navesprit.reclamation;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.example.navesprit.LoginActivity;
import com.example.navesprit.MainActivity;
import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Reclamation;

import java.util.List;

public class MainRecActivity extends AppCompatActivity {
    private ReclamationListAdapter reclamationListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rec_main);


        ImageView logoutIM = findViewById(R.id.homeImageView);
        logoutIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainRecActivity.this, MainActivity.class), 100);
            }
        });

        initRecyclerView();

        loadUserList();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        reclamationListAdapter = new ReclamationListAdapter(this);
        recyclerView.setAdapter(reclamationListAdapter);
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Reclamation> recList =db.reclamationDao().getAllReclamations();
        reclamationListAdapter.setReclamationList(recList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}