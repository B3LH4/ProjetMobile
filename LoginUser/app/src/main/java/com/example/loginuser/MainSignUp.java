package com.example.loginuser;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainSignUp extends AppCompatActivity {

    EditText full_NameEdt, emailEdt, passwordEdt;
    TextView save, backlogin;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);


        full_NameEdt = findViewById(R.id.Name);
        emailEdt = findViewById(R.id.email);
        passwordEdt = findViewById(R.id.psswd);
        backlogin = findViewById(R.id.retour);
        save = findViewById(R.id.Save);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating User Entity
                final UserEntity userEntity = new UserEntity();
                userEntity.setFullnom(full_NameEdt.getText().toString());
                userEntity.setMail(emailEdt.getText().toString());
                userEntity.setPassword(passwordEdt.getText().toString());
                if(validateInput(userEntity)) {
                    // Do insert operation
                    UserDatabase userDatabase = UserDatabase.getUserDatabase(getApplicationContext());
                    final UserDao userDao = userDatabase.userDao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // Register User
                            userDao.registerUser(userEntity);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(),"User Registered!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }).start();

                }else{
                    Toast.makeText(MainSignUp.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        backlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainSignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private Boolean validateInput(UserEntity userEntity){
        if(userEntity.getFullnom().isEmpty() ||
        userEntity.getMail().isEmpty() ||
        userEntity.getPassword().isEmpty()){
            return false;
        }
        return true;
    }
}