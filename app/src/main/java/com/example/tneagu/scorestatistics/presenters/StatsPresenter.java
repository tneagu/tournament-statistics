package com.example.tneagu.scorestatistics.presenters;

import android.provider.ContactsContract;

import com.example.tneagu.scorestatistics.DataManager;
import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;
import com.example.tneagu.scorestatistics.presenters.interfaces.IStatsPresenter;
import com.example.tneagu.scorestatistics.presenters.interfaces.IStatsView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@EBean
public class StatsPresenter implements IStatsPresenter {
    public static final int SORT_NAME = 1;
    public static final int SORT_MATCHES = 2;
    public static final int SORT_WINS = 3;
    public static final int SORT_SCORED = 4;
    public static final int SORT_CONCEIVED = 5;

    private IStatsView callback;
    private ArrayList<Team> teams;
    ArrayList<Match> matches;

    public StatsPresenter() {
        teams = DataManager.getInstance().getTeams();
        matches = DataManager.getInstance().getMatches();
    }

    @Override
    public void setCallback(IStatsView callback) {
        this.callback = callback;
    }


    @Override
    @Background
    public void getDataForScoreTable() {
        ArrayList<String> dataForScoreTable = new ArrayList<>();
        int noOfColumns = teams.size() + 1;
        int i, j;

        String text = "";
        for (i = 0; i < noOfColumns; i++) {
            for (j = 0; j < noOfColumns; j++) {
                if (i == j) {
                    text = "";
                } else if (i == 0) {
                    text = teams.get(j-1).getTeamName();
                } else if (j == 0) {
                    text = teams.get(i-1).getTeamName();
                } else {
                    String firstTeamName = teams.get(i-1).getTeamName();
                    String secondTeamName = teams.get(j-1).getTeamName();

                    Match match = getMatchBetween(firstTeamName, secondTeamName);
                    if (match != null) {
                        text = String.valueOf(match.getFirstTeamGoals()) + " - " +
                                String.valueOf(match.getSecondTeamGoals());
                    }else{
                        text = "-";
                    }
                }
                dataForScoreTable.add(text);
            }
        }

        callback.onDataForScoreTableReady(dataForScoreTable, noOfColumns);
    }



    @Override
    @Background
    public void getTeams() {
        callback.onGetTeams(DataManager.getInstance().getTeams());
    }

    @Override
    @Background
    public void sortTeamsBy(int sortType) {
        switch (sortType){
            case SORT_NAME:
                Collections.sort(teams, Team.Comparators.NAME);
                break;
            case SORT_MATCHES:
                Collections.sort(teams, Team.Comparators.MATCHES);
                break;
            case SORT_WINS:
                Collections.sort(teams, Team.Comparators.WINS);
                break;
            case SORT_SCORED:
                Collections.sort(teams, Team.Comparators.GOALS_SCORED);
                break;
            case SORT_CONCEIVED:
                Collections.sort(teams, Team.Comparators.GOALS_CONCEIVED);
                break;
        }
        callback.onSortTeams(teams);
    }


    //
    //PRIVATE IMPLEMENTATION
    //
    private Match getMatchBetween(String firstTeamName, String secondTeamName) {
        for (Match match : matches){
            if(match.getFirstTeamName().equalsIgnoreCase(firstTeamName)
                    && match.getSecondTeamName().equalsIgnoreCase(secondTeamName)){
                return match;
            }
        }
        return  null;
    }
}
