package com.example.navesprit.Seance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.navesprit.R;
import com.example.navesprit.Seance.adapter.ChaiseAdapter;
import com.example.navesprit.db.Chaise;
import com.example.navesprit.db.SeanceWithChaises;

import java.util.List;

// SeanceDetailActivity.java
public class SeanceDetailActivity extends AppCompatActivity  implements ChaiseAdapter.ChaiseClickListener {

    SeanceWithChaises seanceWithChaises;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seance_detail);

         seanceWithChaises = (SeanceWithChaises) getIntent().getSerializableExtra("seanceWithChaises");

        TextView numeroSeanceTextView = findViewById(R.id.numeroSeanceTextView);
        TextView nomMatiereTextView = findViewById(R.id.nomMatiereTextView);

        RecyclerView chaisesRecyclerView = findViewById(R.id.chaisesRecyclerView);
        chaisesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        numeroSeanceTextView.setText("Date Seance: " + seanceWithChaises.seance.date_seance);
        nomMatiereTextView.setText("Matiere: " + seanceWithChaises.seance.nom_matiere);

        List<Chaise> chaises = seanceWithChaises.chaises;
        ChaiseAdapter chaiseAdapter = new ChaiseAdapter(chaises, this,this);
        chaisesRecyclerView.setAdapter(chaiseAdapter);
    }

    @Override
    public void onChaiseClick(Chaise chaise) {
        Intent intent = new Intent(this, DetailSeanceActivity.class);
        intent.putExtra("chaise", chaise);
        intent.putExtra("seanceWithChaises", seanceWithChaises);

        startActivity(intent);
    }

}
