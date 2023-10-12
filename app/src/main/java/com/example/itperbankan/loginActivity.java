package com.example.itperbankan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    private Button btn_login;

    private EditText username, passworduser;



    private DatabaseReference database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        passworduser = findViewById(R.id.passworduser);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameuser = username.getText().toString();
                String password = passworduser.getText().toString();

                database = FirebaseDatabase.getInstance().getReference("Users");



                if(usernameuser.isEmpty()||password.isEmpty()){
                    Log.d("LoginActivity", "Email atau Password Kosong"); // Tambahkan log
                    Toast.makeText(getApplicationContext(),"Email atau Password Salah",Toast.LENGTH_SHORT).show();

                }else{
                    database.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(usernameuser).exists()){
                                if(snapshot.child(usernameuser).child("password").getValue(String.class).equals(password)){
                                    Log.d("LoginActivity", "Login Berhasil"); // Tambahkan log
                                    Toast.makeText(getApplicationContext(),"Login Berhasil",Toast.LENGTH_SHORT).show();

                                    int userSaldo = snapshot.child(usernameuser).child("saldo").getValue(Integer.class);
                                    String userNomorRekening = snapshot.child(usernameuser).child("no_rekening").getValue(String.class);


                                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("username", usernameuser); // Simpan nama pengguna
                                    editor.apply();

                                    Intent masuk = new Intent(getApplicationContext(), dashboardActivity.class);
                                    startActivity(masuk);

//                                    Intent masuk = new Intent(getApplicationContext(), dashboardActivity.class);
//                                    masuk.putExtra("saldo",userSaldo);
//                                    masuk.putExtra("no_rekening",userNomorRekening);
//                                    startActivity(masuk);
                                }else {
                                    Log.d("LoginActivity", "Password Salah"); // Tambahkan log
                                    Toast.makeText(getApplicationContext(), "Password Salah", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Log.d("LoginActivity", "Password Salah"); // Tambahkan log
                                Toast.makeText(getApplicationContext(),"Data tidak ada",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Log.e("LoginActivity", "Error: " + error.getMessage()); // Tambahkan log kesalahan
                        }
                    });
                }
            }
        });





    }
    public void klik_daftar(View view){
        // Definisikan Intent untuk berpindah ke registerActivity
        Intent intent = new Intent(this, registerActivity.class);

        // Mulai aktivitas baru (registerActivity)
        startActivity(intent);
    }
}