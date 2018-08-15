package com.example.tneagu.scorestatistics.presenters;

import android.provider.ContactsContract;

import com.example.tneagu.scorestatistics.DataManager;
import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;
import com.example.tneagu.scorestatistics.presenters.interfaces.IMainPresenter;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;

@EBean
public class MainPresenter implements IMainPresenter {

    @Override
    @Background
    public void addTeam(String teamName) {
        Team team = new Team(teamName);
        DataManager.getInstance().addTeam(team);
    }

    @Override
    @Background
    public void addMatch(Match match) {
        DataManager.getInstance().addMatch(match);
    }

    @Override
    @Background
    public void clearData() {
        DataManager.getInstance().clearData();
    }
}
