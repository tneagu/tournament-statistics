package com.example.tneagu.scorestatistics.models;

public class Match {
    private String firstTeamName;
    private String secondTeamName;
    private int firstTeamGoals;
    private int secondTeamGoals;

    public Match(String firstTeamName, int firstTeamGoals, String secondTeamName, int secondTeamGoals) {
        this.firstTeamName = firstTeamName;
        this.secondTeamName = secondTeamName;
        this.firstTeamGoals = firstTeamGoals;
        this.secondTeamGoals = secondTeamGoals;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public int getFirstTeamGoals() {
        return firstTeamGoals;
    }

    public int getSecondTeamGoals() {
        return secondTeamGoals;
    }


}
