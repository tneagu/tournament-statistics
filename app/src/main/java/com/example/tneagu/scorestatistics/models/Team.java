package com.example.tneagu.scorestatistics.models;

import android.support.annotation.NonNull;

import java.util.Comparator;

public class Team implements Comparable<Team>{
    private String teamName = "";
    private int matchsPlayed = 0;
    private int wins = 0;
    private int goalsScored = 0;
    private int goalsConceived = 0;

    public Team(String teamName) {
        this.teamName = teamName;
    }


    public void addMatchPlayed(Match match){
        String firstTeamName = match.getFirstTeamName();
        String secondTeamName = match.getSecondTeamName();
        int scoredGoals = 0, conceivedGoals = 0;

        if(firstTeamName.equalsIgnoreCase(this.teamName)){
            scoredGoals = match.getFirstTeamGoals();
            conceivedGoals = match.getSecondTeamGoals();
        }else if(secondTeamName.equalsIgnoreCase(this.teamName)){
            scoredGoals = match.getSecondTeamGoals();
            conceivedGoals = match.getFirstTeamGoals();
        }else{
            return;
        }

        matchsPlayed++;
        this.goalsConceived += conceivedGoals;
        this.goalsScored += scoredGoals;
        if(scoredGoals > conceivedGoals){
            this.wins++;
        }
    }

    public void removeMatchPlayed(Match match){
        String firstTeamName = match.getFirstTeamName();
        String secondTeamName = match.getSecondTeamName();
        int scoredGoals = 0, conceivedGoals = 0;

        if(firstTeamName.equalsIgnoreCase(this.teamName)){
            scoredGoals = match.getFirstTeamGoals();
            conceivedGoals = match.getSecondTeamGoals();
        }else if(secondTeamName.equalsIgnoreCase(this.teamName)){
            scoredGoals = match.getSecondTeamGoals();
            conceivedGoals = match.getFirstTeamGoals();
        }else{
            return;
        }

        matchsPlayed--;
        this.goalsConceived -= conceivedGoals;
        this.goalsScored -= scoredGoals;
        if(scoredGoals > conceivedGoals){
            this.wins--;
        }
    }

    public String getTeamName() {
        return teamName;
    }

    public int getMatchsPlayed() {
        return matchsPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsConceived() {
        return goalsConceived;
    }


    @Override
    public int compareTo(@NonNull Team o) {
        return Comparators.NAME.compare(this, o);
    }


    public static class Comparators {

        public static Comparator<Team> NAME = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getTeamName().compareTo(o2.getTeamName());
            }
        };

        public static Comparator<Team> MATCHES = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getMatchsPlayed() - o2.getMatchsPlayed();
            }
        };

        public static Comparator<Team> WINS = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getWins() - o2.getWins();
            }
        };

        public static Comparator<Team> GOALS_SCORED = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getGoalsScored() - o2.getGoalsScored();
            }
        };

        public static Comparator<Team> GOALS_CONCEIVED = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                return o1.getGoalsConceived() - o2.getGoalsConceived();
            }
        };
    }
}
