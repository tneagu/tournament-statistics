package com.example.tneagu.scorestatistics.adapters;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tneagu.scorestatistics.R;
import com.example.tneagu.scorestatistics.models.Team;

import java.util.ArrayList;

public class StatsTableAdapter extends RecyclerView.Adapter<StatsTableAdapter.ViewHolder> {

    private ArrayList<Team> items;

    public void setItems(ArrayList<Team> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stats, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Team team = items.get(position);

        holder.teamName.setText(team.getTeamName());
        holder.matches.setText(String.valueOf(team.getMatchsPlayed()));
        holder.wins.setText(String.valueOf(team.getWins()));
        holder.scored.setText(String.valueOf(team.getGoalsScored()));
        holder.conceived.setText(String.valueOf(team.getGoalsConceived()));

        int backgroundColor = position%2 == 0 ?
                ContextCompat.getColor(holder.itemView.getContext(), R.color.firstRecyclerColor) : ContextCompat.getColor(holder.itemView.getContext(), R.color.secondRecyclerColor);
        holder.itemView.setBackgroundColor(backgroundColor);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView teamName;
        TextView matches;
        TextView wins;
        TextView scored;
        TextView conceived;

        public ViewHolder(View itemView) {
            super(itemView);

            teamName = itemView.findViewById(R.id.name);
            matches = itemView.findViewById(R.id.matches);
            wins = itemView.findViewById(R.id.wins);
            scored = itemView.findViewById(R.id.scored);
            conceived = itemView.findViewById(R.id.conceived);
        }
    }
}
