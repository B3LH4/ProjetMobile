package com.example.navesprit.reclamation.admin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.navesprit.LoginActivity;
import com.example.navesprit.MainActivity;
import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Reclamation;
import com.example.navesprit.reclamation.MainRecActivity;
import com.example.navesprit.reclamation.ReclamationListAdapter;

import java.util.List;

public class ReclamationAdminActivity extends AppCompatActivity {
    private ReclamationListAdminAdapter reclamationListAdminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation_admin);

        ImageView logoutIM = findViewById(R.id.homeImageView);
        logoutIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ReclamationAdminActivity.this, LoginActivity.class), 100);
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
        reclamationListAdminAdapter = new ReclamationListAdminAdapter(this);
        recyclerView.setAdapter(reclamationListAdminAdapter);
    }

    private void loadUserList() {
        AppDatabase db = AppDatabase.getDbInstance(this.getApplicationContext());
        List<Reclamation> recList =db.reclamationDao().getAllReclamations();
        reclamationListAdminAdapter.setReclamationList(recList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 100) {
            loadUserList();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}