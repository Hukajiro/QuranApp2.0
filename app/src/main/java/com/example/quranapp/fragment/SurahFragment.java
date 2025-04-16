package com.example.quranapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.AyatActivity;
import com.example.quranapp.R;
import com.example.quranapp.adapter.SurahAdapter;
import com.example.quranapp.api.QuranApiService;
import com.example.quranapp.api.RetrofitClient;
import com.example.quranapp.model.SurahResponse;
import com.example.quranapp.model.Surah;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SurahFragment extends Fragment {

    private RecyclerView recyclerViewSurah;
    private SurahAdapter surahAdapter;
    private List<Surah> surahList;
    private QuranApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_surah, container, false);

        recyclerViewSurah = view.findViewById(R.id.recyclerViewSurah);
        recyclerViewSurah.setLayoutManager(new LinearLayoutManager(getContext()));
        surahList = new ArrayList<>();
        apiService = RetrofitClient.getApiService();

        // Ambil data surah dari API
        apiService.getAllSurahs().enqueue(new Callback<SurahResponse>() {
            @Override
            public void onResponse(Call<SurahResponse> call, Response<SurahResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    SurahResponse.SurahData data = response.body().data;
                    for (SurahResponse.SurahDetail surahDetail : data.surahs) {
                        surahList.add(new Surah(
                                surahDetail.number,
                                surahDetail.englishName,
                                surahDetail.revelationType + " | " + surahDetail.numberOfAyahs + " AYAT"
                        ));
                    }
                    surahAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getContext(), "Failed to load surahs", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SurahResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        surahAdapter = new SurahAdapter(surahList, surah -> {
            Intent intent = new Intent(getActivity(), AyatActivity.class);
            intent.putExtra("surahNumber", surah.getNumber());
            intent.putExtra("surahName", surah.getName());
            startActivity(intent);
        });
        recyclerViewSurah.setAdapter(surahAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (surahAdapter != null) {
            surahAdapter.notifyDataSetChanged();
        }
    }
}