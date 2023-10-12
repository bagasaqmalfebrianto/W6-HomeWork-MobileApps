package com.example.itperbankan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboardActivity extends AppCompatActivity {

    private TextView tampilrek;
    private TextView tampilSaldo;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tampilrek = findViewById(R.id.tampilrek);
        tampilSaldo = findViewById(R.id.tampilsaldo);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        String usernameuser = sharedPreferences.getString("username", "");

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(usernameuser);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String nomorRekening = snapshot.child("no_rekening").getValue(String.class);
                    int saldo = snapshot.child("saldo").getValue(Integer.class);

                    tampilrek.setText(nomorRekening);
                    tampilSaldo.setText(String.valueOf(saldo));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("DashboardActivity", "Error: " + error.getMessage());
            }
        });

        TextView klik_keluar = findViewById(R.id.klik_keluar);

        klik_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jalankan fungsi logout
                logout();
            }
        });
    }

    private void logout() {
        // Hapus data sesi pengguna dari SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("username");
        editor.apply();

        // Arahkan pengguna ke halaman login
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
        finish(); // Hapus aktivitas saat ini dari tumpukan aktivitas
    }
}
