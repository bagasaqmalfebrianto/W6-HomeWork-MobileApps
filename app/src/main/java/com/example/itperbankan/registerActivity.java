package com.example.itperbankan;

import static com.example.itperbankan.R.*;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerActivity extends AppCompatActivity {


    private EditText no_rekeninguser, username,passworduser;

    private Button btn_daftar;

    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_register);

        username = findViewById(id.username);
        no_rekeninguser = findViewById(id.no_rekeninguser);
        passworduser = findViewById(id.passworduser);
        btn_daftar = findViewById(id.btn_daftar);

        database = FirebaseDatabase.getInstance().getReferenceFromUrl("https://itperbankan-cb666-default-rtdb.firebaseio.com/");

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameuser = username.getText().toString();
                String no_rekening = no_rekeninguser.getText().toString();
                String password = passworduser.getText().toString();


                Random random = new Random();

                Integer min = 1000; // Nilai minimal
                Integer max = 100000; // Nilai maksimal

                Integer nominalAcak = random.nextInt((max - min) + 1) + min;

                if (usernameuser.isEmpty()||no_rekening.isEmpty()||password.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Isi Semua Data",Toast.LENGTH_SHORT).show();
                }else{
                    database = FirebaseDatabase.getInstance().getReference("Users");
                    database.child(usernameuser).child("no_rekening").setValue(no_rekening);
                    database.child(usernameuser).child("username").setValue(usernameuser);
                    database.child(usernameuser).child("password").setValue(password);
                    database.child(usernameuser).child("saldo").setValue(nominalAcak);

                    Toast.makeText(getApplicationContext(),"Registrasi Berhasil",Toast.LENGTH_SHORT).show();
                    Intent register = new Intent(getApplicationContext(), loginActivity.class);
                    startActivity(register);

                }

            }
        });
    }

    public void klik_daftar(View view) {
        // Definisikan Intent untuk berpindah ke loginActivity
        Intent intent = new Intent(this, loginActivity.class);

        // Mulai aktivitas baru (loginActivity)
        startActivity(intent);
    }
}