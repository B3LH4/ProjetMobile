package com.example.navesprit.reclamation.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navesprit.R;
import com.example.navesprit.db.AppDatabase;
import com.example.navesprit.db.Reclamation;
import com.example.navesprit.db.ReclamationDao;
import com.example.navesprit.reclamation.DetailReclamationActivity;
import com.example.navesprit.reclamation.EditReclamationActivity;
import com.example.navesprit.reclamation.MainRecActivity;

public class DetailAdminActivity extends AppCompatActivity {

    private TextView textView2, textView4, textView6,textView8,textView10;
    private Button button,button2;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_admin);

        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        textView10 = findViewById(R.id.textView10);
        button = findViewById(R.id.button);
        iv=findViewById(R.id.imageView);

        Intent intent = getIntent();
        String name_user = intent.getStringExtra("username");
        String desc_reclamation = intent.getStringExtra("desc");
        String etat_reclamation = intent.getStringExtra("etat");
        String img = intent.getStringExtra("img");
        String date = intent.getStringExtra("date");
        String heure = intent.getStringExtra("heure");



        int id_reclamation = intent.getIntExtra("id",0);

        Reclamation reclamation = (Reclamation) getIntent().getSerializableExtra("reclamation");

        textView2.setText(name_user);
        textView4.setText(desc_reclamation);
        textView6.setText(etat_reclamation);
        textView8.setText(date);
        textView10.setText(heure);
        Bitmap bitmap = BitmapFactory.decodeFile(img);
        iv.setImageBitmap(bitmap);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int reclamationIdToUpdate = id_reclamation; // Replace with the actual ID
                String updatedEtat = "traitèe";

                ReclamationDao reclamationDao = AppDatabase.getDbInstance(DetailAdminActivity.this).reclamationDao();

                reclamationDao.updateById(reclamationIdToUpdate, name_user, desc_reclamation, updatedEtat,img,date,heure);



                Intent intent = new Intent(DetailAdminActivity.this, ReclamationAdminActivity.class);
                startActivity(intent);
            }
        });


    }
}