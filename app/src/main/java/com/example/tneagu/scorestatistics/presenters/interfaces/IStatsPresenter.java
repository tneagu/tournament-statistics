package com.example.tneagu.scorestatistics.presenters.interfaces;

import com.example.tneagu.scorestatistics.models.Team;

import java.util.ArrayList;

public interface IStatsPresenter {
    void setCallback(IStatsView callback);
    void getDataForScoreTable();
    void getTeams();
    void sortTeamsBy(int sortType);
}
