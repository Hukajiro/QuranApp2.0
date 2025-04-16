package com.example.quranapp.model;

public class Bookmark {
    private String title;
    private String details;
    private String date;
    private int surahNumber;

    public Bookmark(String title, String details, String date, int surahNumber) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.surahNumber = surahNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    public int getSurahNumber() {
        return surahNumber;
    }
}