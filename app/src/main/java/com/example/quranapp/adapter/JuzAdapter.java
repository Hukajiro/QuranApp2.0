package com.example.quranapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quranapp.R;
import com.example.quranapp.model.Juz;
import java.util.List;

public class JuzAdapter extends RecyclerView.Adapter<JuzAdapter.JuzViewHolder> {

    private List<Juz> juzList;
    private OnJuzClickListener listener;

    public interface OnJuzClickListener {
        void onJuzClick(Juz juz);
    }

    public JuzAdapter(List<Juz> juzList, OnJuzClickListener listener) {
        this.juzList = juzList;
        this.listener = listener;
    }

    @Override
    public JuzViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juz, parent, false);
        return new JuzViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JuzViewHolder holder, int position) {
        Juz juz = juzList.get(position);
        holder.juzNumber.setText("Juz " + juz.getNumber());
        holder.juzDetails.setText(juz.getDetails());
        holder.itemView.setOnClickListener(v -> listener.onJuzClick(juz));
    }

    @Override
    public int getItemCount() {
        return juzList.size();
    }

    static class JuzViewHolder extends RecyclerView.ViewHolder {
        TextView juzNumber, juzDetails;

        public JuzViewHolder(View itemView) {
            super(itemView);
            juzNumber = itemView.findViewById(R.id.juzNumber);
            juzDetails = itemView.findViewById(R.id.juzDetails);
        }
    }
}