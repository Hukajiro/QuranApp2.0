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
import com.example.quranapp.adapter.JuzAdapter;
import com.example.quranapp.api.QuranApiService;
import com.example.quranapp.api.RetrofitClient;
import com.example.quranapp.model.JuzResponse;
import com.example.quranapp.model.Juz;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JuzFragment extends Fragment {

    private RecyclerView recyclerViewJuz;
    private JuzAdapter juzAdapter;
    private List<Juz> juzList;
    private QuranApiService apiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juz, container, false);

        recyclerViewJuz = view.findViewById(R.id.recyclerViewJuz);
        recyclerViewJuz.setLayoutManager(new LinearLayoutManager(getContext()));
        juzList = new ArrayList<>();
        apiService = RetrofitClient.getApiService();

        // Ambil data juz dari API (1-30)
        for (int i = 1; i <= 30; i++) {
            final int juzNumber = i;
            apiService.getJuz(juzNumber).enqueue(new Callback<JuzResponse>() {
                @Override
                public void onResponse(Call<JuzResponse> call, Response<JuzResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        JuzResponse.JuzData data = response.body().data;
                        juzList.add(new Juz(juzNumber, "MULAI DI: " + data.start));
                        juzAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<JuzResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Error loading Juz " + juzNumber, Toast.LENGTH_SHORT).show();
                }
            });
        }

        juzAdapter = new JuzAdapter(juzList, juz -> {
            Intent intent = new Intent(getActivity(), AyatActivity.class);
            String surahName = juz.getDetails().split(" AYAT ")[0].replace("MULAI DI: ", "");
            intent.putExtra("surahNumber", juz.getNumber());
            intent.putExtra("surahName", surahName);
            startActivity(intent);
        });
        recyclerViewJuz.setAdapter(juzAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (juzAdapter != null) {
            juzAdapter.notifyDataSetChanged();
        }
    }
}