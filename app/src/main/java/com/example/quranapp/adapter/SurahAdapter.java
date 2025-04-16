package com.example.quranapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.R;
import com.example.quranapp.model.Surah;
import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.SurahViewHolder> {

    private List<Surah> surahList;
    private OnSurahClickListener listener;

    public interface OnSurahClickListener {
        void onSurahClick(Surah surah);
    }

    public SurahAdapter(List<Surah> surahList, OnSurahClickListener listener) {
        this.surahList = surahList;
        this.listener = listener;
    }

    @Override
    public SurahViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah, parent, false);
        return new SurahViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SurahViewHolder holder, int position) {
        Surah surah = surahList.get(position);
        holder.surahNumber.setText(String.valueOf(surah.getNumber()));
        holder.surahName.setText(surah.getName());
        holder.surahDetails.setText(surah.getDetails());
        holder.surahArabicName.setText(surah.getName()); // Replace with actual Arabic name
        holder.itemView.setOnClickListener(v -> listener.onSurahClick(surah));
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    static class SurahViewHolder extends RecyclerView.ViewHolder {
        TextView surahNumber, surahName, surahDetails, surahArabicName;

        public SurahViewHolder(View itemView) {
            super(itemView);
            surahNumber = itemView.findViewById(R.id.surahNumber);
            surahName = itemView.findViewById(R.id.surahName);
            surahDetails = itemView.findViewById(R.id.surahDetails);
            surahArabicName = itemView.findViewById(R.id.surahArabicName);
        }
    }
}