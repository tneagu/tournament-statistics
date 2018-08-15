package com.example.tneagu.scorestatistics.presenters.interfaces;

import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;

import java.util.ArrayList;

public interface IStatsView {
    void onDataForScoreTableReady(ArrayList<String> data, int noOfColumns);
    void onGetTeams(ArrayList<Team> teams);
    void onSortTeams(ArrayList<Team> teams);
}
