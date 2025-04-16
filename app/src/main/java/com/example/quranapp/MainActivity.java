package com.example.quranapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: Mulai");
        try {
            setContentView(R.layout.activity_main);
            Log.d(TAG, "onCreate: Set content view selesai");
        } catch (Exception e) {
            Log.e(TAG, "onCreate: Error saat setContentView", e);
            throw e;
        }

        TextView settingTema = findViewById(R.id.settingTema);
        if (settingTema == null) {
            Log.e(TAG, "onCreate: TextView settingTema tidak ditemukan");
        } else {
            Log.d(TAG, "onCreate: TextView settingTema ditemukan, teks: " + settingTema.getText());
        }

        ImageView logoImageView = findViewById(R.id.logo);
        if (logoImageView == null) {
            Log.e(TAG, "onCreate: ImageView logo tidak ditemukan");
        } else {
            Log.d(TAG, "onCreate: ImageView logo ditemukan");
            try {
                Glide.with(this)
                        .load(R.drawable.logo)
                        .error(android.R.drawable.ic_dialog_alert)
                        .into(logoImageView);
                Log.d(TAG, "onCreate: Glide berhasil memuat gambar");
            } catch (Exception e) {
                Log.e(TAG, "onCreate: Error saat memuat gambar dengan Glide", e);
            }
        }

        Button bacaQuranButton = findViewById(R.id.bacaQuranButton);
        if (bacaQuranButton == null) {
            Log.e(TAG, "onCreate: Button bacaQuranButton tidak ditemukan");
        } else {
            Log.d(TAG, "onCreate: Button bacaQuranButton ditemukan, teks: " + bacaQuranButton.getText());
        }

        Button terakhirBacaButton = findViewById(R.id.terakhirBacaButton);
        if (terakhirBacaButton == null) {
            Log.e(TAG, "onCreate: Button terakhirBacaButton tidak ditemukan");
        } else {
            Log.d(TAG, "onCreate: Button terakhirBacaButton ditemukan, teks: " + terakhirBacaButton.getText());
        }

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.d(TAG, "onCreate: Berpindah ke QuranActivity setelah 10 detik");
            startActivity(new Intent(MainActivity.this, QuranActivity.class));
            finish();
            Log.d(TAG, "onCreate: Berhasil pindah ke QuranActivity");
        }, 10000);
    }
}