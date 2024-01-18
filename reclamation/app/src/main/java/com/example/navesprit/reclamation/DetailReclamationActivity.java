package com.example.navesprit.reclamation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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

public class DetailReclamationActivity extends AppCompatActivity {


    private TextView  textView2, textView4, textView6,textView8,textView10;
    private Button button,button2;

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_reclamation);



        textView2 = findViewById(R.id.textView2);
        textView4 = findViewById(R.id.textView4);
        textView6 = findViewById(R.id.textView6);
        textView8 = findViewById(R.id.textView8);
        textView10 = findViewById(R.id.textView10);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        iv=findViewById(R.id.imageView);


        Intent intent = getIntent();
        String name_user = intent.getStringExtra("username");
        String desc_reclamation = intent.getStringExtra("desc");
        String etat_reclamation = intent.getStringExtra("etat");
        int id_reclamation = intent.getIntExtra("id",0);
        String img = intent.getStringExtra("img");
        String date = intent.getStringExtra("date");
        String heure = intent.getStringExtra("heure");

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



                Intent intent = new Intent(DetailReclamationActivity.this, EditReclamationActivity.class);
                intent.putExtra("username", name_user);
                intent.putExtra("desc", desc_reclamation);
                intent.putExtra("etat", etat_reclamation);
                intent.putExtra("id", id_reclamation);
                intent.putExtra("img", img);
                intent.putExtra("date", date);
                intent.putExtra("heure", heure);
                startActivity(intent);
            }
        });


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReclamationDao reclamationDao = AppDatabase.getDbInstance(DetailReclamationActivity.this).reclamationDao();
                reclamationDao.delete(reclamation);

                Intent intent = new Intent(DetailReclamationActivity.this, MainRecActivity.class);
                startActivity(intent);
            }
        });

    }
}