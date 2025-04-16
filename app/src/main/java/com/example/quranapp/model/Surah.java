package com.example.quranapp.model;

public class Surah {
    private int number;
    private String name;
    private String details;

    public Surah(int number, String name, String details) {
        this.number = number;
        this.name = name;
        this.details = details;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }
}