package com.example.quranapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quranapp.adapter.LastReadAdapter;
import com.example.quranapp.model.Bookmark;
import com.example.quranapp.util.SharedPrefManager;
import java.util.List;

public class LastReadActivity extends AppCompatActivity {

    private RecyclerView recyclerViewLastRead;
    private LastReadAdapter lastReadAdapter;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_read);

        recyclerViewLastRead = findViewById(R.id.recyclerViewLastRead);
        recyclerViewLastRead.setLayoutManager(new LinearLayoutManager(this));
        sharedPrefManager = new SharedPrefManager(this);

        List<Bookmark> lastReadList = sharedPrefManager.getLastRead();
        lastReadAdapter = new LastReadAdapter(lastReadList, bookmark -> {
            Intent intent = new Intent(LastReadActivity.this, AyatActivity.class);
            intent.putExtra("surahName", bookmark.getTitle().split(" Ayat ")[0]);
            intent.putExtra("surahNumber", 1); // Replace with actual surah number
            startActivity(intent);
        });
        recyclerViewLastRead.setAdapter(lastReadAdapter);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
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