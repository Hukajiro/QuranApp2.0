package com.example.quranapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.example.quranapp.fragment.SurahFragment;
import com.example.quranapp.fragment.JuzFragment;
import com.example.quranapp.fragment.BookmarkFragment;
import com.example.quranapp.util.SharedPrefManager;
import java.util.ArrayList;
import java.util.List;

public class QuranActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPrefManager = new SharedPrefManager(this);
        AppCompatDelegate.setDefaultNightMode(sharedPrefManager.getThemeMode());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quran);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new SurahFragment());
        fragments.add(new JuzFragment());
        fragments.add(new BookmarkFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(this, fragments);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.surah);
                    break;
                case 1:
                    tab.setText(R.string.juz);
                    break;
                case 2:
                    tab.setText(R.string.bookmark);
                    break;
            }
        }).attach();

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}

class ViewPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
    private final List<Fragment> fragments;

    public ViewPagerAdapter(AppCompatActivity activity, List<Fragment> fragments) {
        super(activity);
        this.fragments = fragments;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}