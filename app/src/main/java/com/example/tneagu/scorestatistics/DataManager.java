package com.example.tneagu.scorestatistics;

import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class DataManager {
    private static DataManager instance;

    private ArrayList<Team> teams;
    private ArrayList<Match> matches;

    private DataManager() {
        teams = loadTeams();
        matches = loadMatches();
    }

    public static DataManager getInstance(){
        if(instance == null){
            instance = new DataManager();
            return new DataManager();
        }else{
            return instance;
        }
    }

    //
    //PUBLIC IMPLEMENTATION
    //
    public void addTeam(Team team){
        if(teams != null){
            Team teamAlreadyInSystem = searchForTeamWithName(team.getTeamName());
            if(teamAlreadyInSystem == null){
                teams.add(team);
                saveTeams();
            }
        }
    }

    public void addMatch(Match match){
        if(matches != null && teams != null){
            Match matchAlreadyPlayed = searchForMatchAlreadyPlayed(match);

            if(matchAlreadyPlayed == null){
                matches.add(match);
                updateTeamsStats(match);
            }else{
               matches.remove(matchAlreadyPlayed);
               removeTeamStats(matchAlreadyPlayed);
               matches.add(match);
               updateTeamsStats(match);
            }

            saveTeams();
            saveMatches();
        }
    }



    public void clearData(){
        SharedPrefsManager.clearSharedPrefs();
        teams.clear();
        matches.clear();
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    //
    //PRIVATE IMPLEMENTATION
    //
    private void updateTeamsStats(Match match) {
        Team firstTeam = searchForTeamWithName(match.getFirstTeamName());
        Team secondTeam = searchForTeamWithName(match.getSecondTeamName());

        if(firstTeam != null){
            firstTeam.addMatchPlayed(match);
        }
        if(secondTeam != null){
            secondTeam.addMatchPlayed(match);
        }
    }

    private void removeTeamStats(Match match) {
        Team firstTeam = searchForTeamWithName(match.getFirstTeamName());
        Team secondTeam = searchForTeamWithName(match.getSecondTeamName());

        if(firstTeam != null){
            firstTeam.removeMatchPlayed(match);
        }
        if(secondTeam != null){
            secondTeam.removeMatchPlayed(match);
        }
    }


    private Team searchForTeamWithName(String teamName) {
        for(Team team : teams){
            if(team.getTeamName().equalsIgnoreCase(teamName)){
                return team;
            }
        }

        return null;
    }

    private Match searchForMatchAlreadyPlayed(Match newMatch){
        for(Match match : matches){
            if(match.getFirstTeamName().equalsIgnoreCase(newMatch.getFirstTeamName()) &&
                    match.getSecondTeamName().equalsIgnoreCase(newMatch.getSecondTeamName())){
                return match;
            }
        }
        return null;
    }


    private ArrayList<Team> loadTeams(){
        ArrayList<Team> result = new ArrayList<>();
        String teamsJson = SharedPrefsManager.getTeams();
        if (!teamsJson.equalsIgnoreCase("")) {
            Team[] teams = new Gson().fromJson(teamsJson, Team[].class);
            Collections.addAll(result, teams);
        }
        return result == null ? new ArrayList<Team>() : result;
    }

    private ArrayList<Match> loadMatches(){
        ArrayList<Match> result = new ArrayList<>();
        String matchesJson = SharedPrefsManager.getMatches();
        if (!matchesJson.equalsIgnoreCase("")) {
            Match[] matches = new Gson().fromJson(matchesJson, Match[].class);
            Collections.addAll(result, matches);
        }
        return result == null ? new ArrayList<Match>() : result;
    }

    private void saveTeams(){
        SharedPrefsManager.saveTeams(new Gson().toJson(teams));
    }

    private void saveMatches(){
        SharedPrefsManager.saveMatches(new Gson().toJson(matches));
    }

}
