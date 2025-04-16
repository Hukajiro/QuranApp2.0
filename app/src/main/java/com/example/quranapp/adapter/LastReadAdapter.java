package com.example.quranapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.R;
import com.example.quranapp.model.Bookmark;
import java.util.List;

public class LastReadAdapter extends RecyclerView.Adapter<LastReadAdapter.LastReadViewHolder> {

    private List<Bookmark> lastReadList;
    private OnLastReadClickListener listener;

    public interface OnLastReadClickListener {
        void onLastReadClick(Bookmark bookmark);
    }

    public LastReadAdapter(List<Bookmark> lastReadList, OnLastReadClickListener listener) {
        this.lastReadList = lastReadList;
        this.listener = listener;
    }

    @Override
    public LastReadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_last_read, parent, false);
        return new LastReadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LastReadViewHolder holder, int position) {
        Bookmark bookmark = lastReadList.get(position);
        holder.lastReadTitle.setText(bookmark.getTitle());
        holder.lastReadDetails.setText(bookmark.getDetails());
        holder.lastReadDate.setText(bookmark.getDate());
        holder.itemView.setOnClickListener(v -> listener.onLastReadClick(bookmark));
    }

    @Override
    public int getItemCount() {
        return lastReadList.size();
    }

    static class LastReadViewHolder extends RecyclerView.ViewHolder {
        TextView lastReadTitle, lastReadDetails, lastReadDate;

        public LastReadViewHolder(View itemView) {
            super(itemView);
            lastReadTitle = itemView.findViewById(R.id.lastReadTitle);
            lastReadDetails = itemView.findViewById(R.id.lastReadDetails);
            lastReadDate = itemView.findViewById(R.id.lastReadDate);
        }
    }
}