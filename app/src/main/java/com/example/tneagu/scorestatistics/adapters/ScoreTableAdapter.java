package com.example.tneagu.scorestatistics.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tneagu.scorestatistics.R;
import com.example.tneagu.scorestatistics.models.Match;

import java.util.ArrayList;

public class ScoreTableAdapter extends RecyclerView.Adapter<ScoreTableAdapter.ViewHolder> {
    private ArrayList<String> items;
    private int noElementsInARow;


    public void setItems(ArrayList<String> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score_table, parent, false);
        return new ScoreTableAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String text = items.get(position);
        holder.text.setText(text);
        if(text.equalsIgnoreCase("")){
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.notAvailable));
        }else{
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.white));
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.tv_score_table);
        }
    }
}
