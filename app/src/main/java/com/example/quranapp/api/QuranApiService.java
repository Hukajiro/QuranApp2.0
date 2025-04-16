package com.example.quranapp.api;

import com.example.quranapp.model.JuzResponse;
import com.example.quranapp.model.SurahDetailResponse;
import com.example.quranapp.model.SurahResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface QuranApiService {

    @GET("surah")
    Call<SurahResponse> getAllSurahs();

    @GET("juz/{juzNumber}")
    Call<JuzResponse> getJuz(@Path("juzNumber") int juzNumber);

    @GET("surah/{surahNumber}")
    Call<SurahDetailResponse> getSurahDetail(@Path("surahNumber") int surahNumber);
}