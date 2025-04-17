package com.example.quranapp20;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Inisialisasi Tombol
        Button btnBacaQuran = findViewById(R.id.btn_baca_quran);
        Button btnTerakhirBaca = findViewById(R.id.btn_terakhir_baca);

        // Klik tombol Baca Quran untuk pindah ke QuranActivity
        btnBacaQuran.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, QuranActivity.class);
            startActivity(intent);
        });

        // Klik tombol Terakhir Baca untuk pindah ke LastReadActivity
        btnTerakhirBaca.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LastReadActivity.class);
            startActivity(intent);
        });
    }
}