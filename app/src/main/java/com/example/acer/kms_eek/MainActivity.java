package com.example.acer.kms_eek;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.acer.kms_eek.history.HistoryActivity;
import com.example.acer.kms_eek.kategori.KategoriActivity;
import com.example.acer.kms_eek.musik.MusikActivity;
import com.example.acer.kms_eek.peta.PetaActivity;

public class MainActivity extends AppCompatActivity {
    private Button ButtonHistory;
    private Button ButtonKategori;
    private Button ButtonMaps;
    private Button ButtonMusik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
        }

        ButtonHistory = (Button) findViewById(R.id.ButtonHistory);
        ButtonKategori = (Button) findViewById(R.id.ButtonKategori);
        ButtonMaps = (Button) findViewById(R.id.ButtonMaps);
        ButtonMusik = (Button) findViewById(R.id.ButtonMUSIC);

        ButtonHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intent);
            }
        });

        ButtonKategori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KategoriActivity.class);
                startActivity(intent);
            }
        });

        ButtonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PetaActivity.class);
                startActivity(intent);
            }
        });

        ButtonMusik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusikActivity.class);
                startActivity(intent);
            }
        });
    }

    public void admin(View view) {
        Intent intent = new Intent(MainActivity.this, Admin.class);
        startActivity(intent);
    }


}
