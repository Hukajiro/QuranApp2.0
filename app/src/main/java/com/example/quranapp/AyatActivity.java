package com.example.quranapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.adapter.AyatAdapter;
import com.example.quranapp.api.QuranApiService;
import com.example.quranapp.api.RetrofitClient;
import com.example.quranapp.model.SurahDetailResponse;
import com.example.quranapp.model.Ayat;
import com.example.quranapp.util.SharedPrefManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AyatActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAyat;
    private AyatAdapter ayatAdapter;
    private List<Ayat> ayatList;
    private SharedPrefManager sharedPrefManager;
    private QuranApiService apiService;
    private int surahNumber;
    private String surahName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(this);
        AppCompatDelegate.setDefaultNightMode(sharedPrefManager.getThemeMode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat);

        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));
        ayatList = new ArrayList<>();
        apiService = RetrofitClient.getApiService();

        surahNumber = getIntent().getIntExtra("surahNumber", 1);
        surahName = getIntent().getStringExtra("surahName");

        // Ambil data ayat dari API
        apiService.getSurahDetail(surahNumber).enqueue(new Callback<SurahDetailResponse>() {
            @Override
            public void onResponse(Call<SurahDetailResponse> call, Response<SurahDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SurahDetailResponse.SurahDetail surahDetail = response.body().data;
                    for (SurahDetailResponse.SurahDetail.Ayah ayah : surahDetail.ayahs) {
                        ayatList.add(new Ayat(ayah.numberInSurah, ayah.text, "Translation not available", ayah.audio));
                    }
                    ayatAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(AyatActivity.this, "Failed to load ayat", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SurahDetailResponse> call, Throwable t) {
                Toast.makeText(AyatActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ayatAdapter = new AyatAdapter(ayatList, new AyatAdapter.OnAyatActionListener() {
            @Override
            public void onPlayClick(Ayat ayat) {
                sharedPrefManager.saveLastRead(surahName + " Ayat " + ayat.getNumber(), "QS. " + surahName + ": Ayat " + ayat.getNumber(), surahNumber);
                MediaPlayer mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(ayat.getAudioUrl());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Toast.makeText(AyatActivity.this, "Error playing audio: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onBookmarkClick(Ayat ayat) {
                sharedPrefManager.addBookmark(surahName + " Ayat " + ayat.getNumber(), "QS. " + surahName + ": Ayat " + ayat.getNumber(), surahNumber);
                Toast.makeText(AyatActivity.this, "Bookmarked", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerViewAyat.setAdapter(ayatAdapter);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(surahName);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}