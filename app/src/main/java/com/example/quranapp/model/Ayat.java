package com.example.quranapp.model;

public class Ayat {
    private int number;
    private String textArabic;
    private String translation;
    private String audioUrl;

    public Ayat(int number, String textArabic, String translation, String audioUrl) {
        this.number = number;
        this.textArabic = textArabic;
        this.translation = translation;
        this.audioUrl = audioUrl;
    }

    public int getNumber() {
        return number;
    }

    public String getTextArabic() {
        return textArabic;
    }

    public String getTranslation() {
        return translation;
    }

    public String getAudioUrl() {
        return audioUrl;
    }
}