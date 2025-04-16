package com.example.quranapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatDelegate;
import com.example.quranapp.model.Bookmark;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SharedPrefManager {

    private static final String PREF_NAME = "QuranAppPrefs";
    private static final String KEY_BOOKMARKS = "bookmarks";
    private static final String KEY_LAST_READ = "last_read";
    private static final String KEY_THEME_MODE = "theme_mode";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void addBookmark(String title, String details, int surahNumber) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String bookmarkEntry = title + "|" + details + "|" + date + "|" + surahNumber;
        String bookmarks = sharedPreferences.getString(KEY_BOOKMARKS, "");
        if (!bookmarks.isEmpty()) {
            bookmarks += ";";
        }
        bookmarks += bookmarkEntry;
        editor.putString(KEY_BOOKMARKS, bookmarks);
        editor.apply();
    }

    public List<Bookmark> getBookmarks() {
        List<Bookmark> bookmarkList = new ArrayList<>();
        String bookmarks = sharedPreferences.getString(KEY_BOOKMARKS, "");
        if (!bookmarks.isEmpty()) {
            String[] bookmarkArray = bookmarks.split(";");
            for (String bookmark : bookmarkArray) {
                String[] parts = bookmark.split("\\|");
                if (parts.length == 4) {
                    bookmarkList.add(new Bookmark(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        }
        return bookmarkList;
    }

    public void saveLastRead(String title, String details, int surahNumber) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        String lastReadEntry = title + "|" + details + "|" + date + "|" + surahNumber;
        String lastRead = sharedPreferences.getString(KEY_LAST_READ, "");
        if (!lastRead.isEmpty()) {
            lastRead += ";";
        }
        lastRead += lastReadEntry;
        editor.putString(KEY_LAST_READ, lastRead);
        editor.apply();
    }

    public List<Bookmark> getLastRead() {
        List<Bookmark> lastReadList = new ArrayList<>();
        String lastRead = sharedPreferences.getString(KEY_LAST_READ, "");
        if (!lastRead.isEmpty()) {
            String[] lastReadArray = lastRead.split(";");
            for (String entry : lastReadArray) {
                String[] parts = entry.split("\\|");
                if (parts.length == 4) {
                    lastReadList.add(new Bookmark(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        }
        return lastReadList;
    }

    public void saveThemeMode(int mode) {
        editor.putInt(KEY_THEME_MODE, mode);
        editor.apply();
    }

    public int getThemeMode() {
        return sharedPreferences.getInt(KEY_THEME_MODE, AppCompatDelegate.MODE_NIGHT_NO);
    }
}