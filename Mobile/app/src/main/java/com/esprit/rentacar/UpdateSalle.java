package com.esprit.rentacar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.esprit.rentacar.dao.SalleDao;
import com.esprit.rentacar.database.AppDataBase;
import com.esprit.rentacar.entity.Salles;

public class UpdateSalle extends AppCompatActivity {
    private  long salleId;
    private SalleDao salleDao;
    private ImageView imageView;
    TextView tvSalle;
    RatingBar rbStars;
    Button CancelBtn, btnSend;
    EditText EditNomSalle, EditDescSalle, Editgpssalle, Editcapacite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_salle);

        // Initialize views and DAO
        salleDao = AppDataBase.getAppDatabase(this).salleDao();
        imageView = findViewById(R.id.imageView);
        tvSalle = findViewById(R.id.tvSalle);
        rbStars = findViewById(R.id.rbStars);
        EditNomSalle = findViewById(R.id.EditNomSalle);
        EditDescSalle = findViewById(R.id.EditDescSalle);
        Editgpssalle = findViewById(R.id.Editgpssalle);
        Editcapacite = findViewById(R.id.Editcapacite);
        btnSend = findViewById(R.id.btnSend);
        CancelBtn = findViewById(R.id.Cancel);

        salleId = getIntent().getLongExtra("SALLE_ID", -1);
        Salles salle = salleDao.getSalleDetailsById(salleId);

        if (salle != null) {
            rbStars.setRating(salle.getPrix_salle());
            EditNomSalle.setText(salle.getNom_salle());
            EditDescSalle.setText(salle.getDesc_salle());
            Editgpssalle.setText(salle.getGps_salle());
            Editcapacite.setText(String.valueOf(salle.getCapacite_salle()));
        }


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSalleInDatabase();
            }
        });

        CancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void updateSalleInDatabase() {
        Log.d("UpdateSalle", "Avant la mise à jour");
        float updatedprix = rbStars.getRating();
        String updatedNom = EditNomSalle.getText().toString();
        String updatedDesc = EditDescSalle.getText().toString();
        String updatedGps = Editgpssalle.getText().toString();
        Double updatedcapacite = Double.valueOf(Editcapacite.getText().toString());

        salleDao.updateRatingAndContent(salleId, updatedNom, updatedDesc,updatedGps,updatedcapacite,updatedprix);
        Log.d("UpdateSalle", "Après la mise à jour");
        finish();
    }
}