package com.example.quranapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.AyatActivity;
import com.example.quranapp.R;
import com.example.quranapp.adapter.BookmarkAdapter;
import com.example.quranapp.util.SharedPrefManager;

public class BookmarkFragment extends Fragment {

    private RecyclerView recyclerViewBookmark;
    private BookmarkAdapter bookmarkAdapter;
    private SharedPrefManager sharedPrefManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);

        recyclerViewBookmark = view.findViewById(R.id.recyclerViewBookmark);
        recyclerViewBookmark.setLayoutManager(new LinearLayoutManager(getContext()));
        sharedPrefManager = new SharedPrefManager(getContext());

        bookmarkAdapter = new BookmarkAdapter(sharedPrefManager.getBookmarks(), bookmark -> {
            Intent intent = new Intent(getActivity(), AyatActivity.class);
            intent.putExtra("surahName", bookmark.getTitle().split(" Ayat ")[0]);
            intent.putExtra("surahNumber", 1); // Replace with actual surah number
            startActivity(intent);
        });
        recyclerViewBookmark.setAdapter(bookmarkAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bookmarkAdapter != null) {
            // Refresh daftar bookmark dari SharedPreferences
            bookmarkAdapter = new BookmarkAdapter(sharedPrefManager.getBookmarks(), bookmark -> {
                Intent intent = new Intent(getActivity(), AyatActivity.class);
                intent.putExtra("surahName", bookmark.getTitle().split(" Ayat ")[0]);
                intent.putExtra("surahNumber", 1); // Replace with actual surah number
                startActivity(intent);
            });
            recyclerViewBookmark.setAdapter(bookmarkAdapter);
            bookmarkAdapter.notifyDataSetChanged(); // Refresh UI untuk tema
        }
    }
}