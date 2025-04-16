package com.example.quranapp.model;

public class Juz {
    private int number;
    private String details;

    public Juz(int number, String details) {
        this.number = number;
        this.details = details;
    }

    public int getNumber() {
        return number;
    }

    public String getDetails() {
        return details;
    }
}