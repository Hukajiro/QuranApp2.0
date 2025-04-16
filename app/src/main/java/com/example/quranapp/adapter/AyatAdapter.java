package com.example.quranapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.R;
import com.example.quranapp.model.Ayat;
import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.AyatViewHolder> {

    private List<Ayat> ayatList;
    private OnAyatActionListener listener;

    public interface OnAyatActionListener {
        void onPlayClick(Ayat ayat);
        void onBookmarkClick(Ayat ayat);
    }

    public AyatAdapter(List<Ayat> ayatList, OnAyatActionListener listener) {
        this.ayatList = ayatList;
        this.listener = listener;
    }

    @Override
    public AyatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat, parent, false);
        return new AyatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AyatViewHolder holder, int position) {
        Ayat ayat = ayatList.get(position);
        holder.ayatNumber.setText(String.valueOf(ayat.getNumber()));
        holder.ayatTextArabic.setText(ayat.getTextArabic());
        holder.ayatTranslation.setText(ayat.getTranslation());
        holder.playButton.setOnClickListener(v -> listener.onPlayClick(ayat));
        holder.bookmarkButton.setOnClickListener(v -> listener.onBookmarkClick(ayat));
    }

    @Override
    public int getItemCount() {
        return ayatList.size();
    }

    static class AyatViewHolder extends RecyclerView.ViewHolder {
        TextView ayatNumber, ayatTextArabic, ayatTranslation;
        Button playButton, bookmarkButton;

        public AyatViewHolder(View itemView) {
            super(itemView);
            ayatNumber = itemView.findViewById(R.id.ayatNumber);
            ayatTextArabic = itemView.findViewById(R.id.ayatTextArabic);
            ayatTranslation = itemView.findViewById(R.id.ayatTranslation);
            playButton = itemView.findViewById(R.id.playButton);
            bookmarkButton = itemView.findViewById(R.id.bookmarkButton);
        }
    }
}