package com.example.tneagu.scorestatistics.presenters.interfaces;

import com.example.tneagu.scorestatistics.models.Match;

import org.androidannotations.annotations.Background;

public interface IMainPresenter {

    void addTeam(String teamName);
    void addMatch(Match match);
    void clearData();
}
