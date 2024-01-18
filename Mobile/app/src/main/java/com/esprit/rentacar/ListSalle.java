package com.esprit.rentacar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.Salles;

import java.util.List;

public class ListSalle extends AppCompatActivity {

    Button btnBack, btn_addOne;
    private RecyclerView recyclerView;
    private SalleAdapter salleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_salle);

        btnBack = findViewById(R.id.BackF);
        btn_addOne =findViewById(R.id.btn_addOne);
        recyclerView = findViewById(R.id.ListViewF);

        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        salleAdapter = new SalleAdapter(appDatabase.salleDao());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(salleAdapter);

        getAllSalle();
        btn_addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListSalle.this,SalleActivity.class);
                startActivity(intent);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListSalle.this, HomeSalle.class));
            }
        });
    }

    private void getAllSalle() {
        AppDataBase appDatabase = AppDataBase.getAppDatabase(this);
        List<Salles> salles = appDatabase.salleDao().getAllSalles();
        salleAdapter.setSalles(salles);
    }



}