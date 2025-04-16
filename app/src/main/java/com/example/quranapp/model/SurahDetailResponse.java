package com.example.quranapp.model;

public class SurahDetailResponse {
    public SurahDetail data;

    public static class SurahDetail {
        public int number;
        public String name;
        public String englishName;
        public String revelationType;
        public int numberOfAyahs;
        public Ayah[] ayahs;

        public static class Ayah {
            public int numberInSurah;
            public String text;
            public String audio;
        }
    }
}