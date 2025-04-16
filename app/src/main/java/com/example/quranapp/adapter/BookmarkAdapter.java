package com.example.quranapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.R;
import com.example.quranapp.model.Bookmark;
import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {

    private List<Bookmark> bookmarkList;
    private OnBookmarkClickListener listener;

    public interface OnBookmarkClickListener {
        void onBookmarkClick(Bookmark bookmark);
    }

    public BookmarkAdapter(List<Bookmark> bookmarkList, OnBookmarkClickListener listener) {
        this.bookmarkList = bookmarkList;
        this.listener = listener;
    }

    @Override
    public BookmarkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        return new BookmarkViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, int position) {
        Bookmark bookmark = bookmarkList.get(position);
        holder.bookmarkTitle.setText(bookmark.getTitle());
        holder.bookmarkDetails.setText(bookmark.getDetails());
        holder.bookmarkDate.setText(bookmark.getDate());
        holder.itemView.setOnClickListener(v -> listener.onBookmarkClick(bookmark));
    }

    @Override
    public int getItemCount() {
        return bookmarkList.size();
    }

    static class BookmarkViewHolder extends RecyclerView.ViewHolder {
        TextView bookmarkTitle, bookmarkDetails, bookmarkDate;

        public BookmarkViewHolder(View itemView) {
            super(itemView);
            bookmarkTitle = itemView.findViewById(R.id.bookmarkTitle);
            bookmarkDetails = itemView.findViewById(R.id.bookmarkDetails);
            bookmarkDate = itemView.findViewById(R.id.bookmarkDate);
        }
    }
}