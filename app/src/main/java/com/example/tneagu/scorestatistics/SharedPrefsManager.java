package com.example.tneagu.scorestatistics;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsManager {
    private static final String PREFS = "prefs";
    private static final String KEY_TEAMS = "teams";
    private static final String KEY_MATCHES = "matches";

    public static SharedPreferences sharedPreferences;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void saveTeams(String teamsJson){
        sharedPreferences.edit().putString(KEY_TEAMS, teamsJson).apply();
    }

    public static String getTeams(){
        return sharedPreferences.getString(KEY_TEAMS, "");
    }

    public static void saveMatches(String matches){
        sharedPreferences.edit().putString(KEY_MATCHES, matches).apply();
    }

    public static String getMatches(){
        return sharedPreferences.getString(KEY_MATCHES, "");
    }

    public static void clearSharedPrefs(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
