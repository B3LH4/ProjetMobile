package com.example.naviagtion_interne;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import com.example.naviagtion_interne.Listedesreclamations;
import com.example.naviagtion_interne.R;

public class AjoutReclamation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_reclamation);

        // Créez un Intent pour passer à l'activité "Listedesreclamations"
        Intent intent = new Intent(this, Listedesreclamations.class);

        // Vous pouvez ajouter des données supplémentaires à l'Intent si nécessaire
        // intent.putExtra("clé", valeur);

        // Démarrer l'activité "Listedesreclamations"
        startActivity(intent);
    }
}
