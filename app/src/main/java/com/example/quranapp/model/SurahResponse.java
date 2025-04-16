package com.example.quranapp.model;

public class SurahResponse {
    public SurahData data;

    public static class SurahData {
        public SurahDetail[] surahs;
    }

    public static class SurahDetail {
        public int number;
        public String name;
        public String englishName;
        public String revelationType;
        public int numberOfAyahs;
    }
}