package com.example.tneagu.scorestatistics.activities;

import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.tneagu.scorestatistics.DataManager;
import com.example.tneagu.scorestatistics.R;
import com.example.tneagu.scorestatistics.dialogs.AddScoreDialog;
import com.example.tneagu.scorestatistics.dialogs.AddScoreDialog_;
import com.example.tneagu.scorestatistics.dialogs.AddTeamDialog;
import com.example.tneagu.scorestatistics.dialogs.AddTeamDialog_;
import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;
import com.example.tneagu.scorestatistics.presenters.MainPresenter;
import com.example.tneagu.scorestatistics.presenters.interfaces.IMainPresenter;
import com.example.tneagu.scorestatistics.presenters.interfaces.IMainView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends FragmentActivity{
    private static final String TAG = "MainActivity";

    @Bean(MainPresenter.class)
    IMainPresenter mainPresenter;

    //
    //CALLBACKS
    //
    @Click(R.id.btn_add_team)
    public void addTeam(){
        AddTeamDialog_.builder().build().show(MainActivity.this, new AddTeamDialog.OnAddTeamCallback() {
            @Override
            public void onAddTeam(String teamName) {
                mainPresenter.addTeam(teamName);
            }
        });
    }

    @Click(R.id.btn_add_score)
    public void addScore(){
        AddScoreDialog_.builder().build().show(MainActivity.this, new AddScoreDialog.OnAddScore() {
            @Override
            public void onAddScore(Match match) {
                mainPresenter.addMatch(match);
            }
        });
    }

    @Click(R.id.btn_statistics)
    public void onStatisticsClicked(){
        ArrayList<Team> teams = DataManager.getInstance().getTeams();
        if(teams.size() < 2){
            Toast.makeText(MainActivity.this, R.string.alert_not_enough_teams_stats, Toast.LENGTH_SHORT).show();
        }else{
            StatsActivity.openActivity(MainActivity.this);
        }
    }


    @Click(R.id.btn_reset)
    public void onReset(){
        mainPresenter.clearData();
        Toast.makeText(MainActivity.this, R.string.alert_data_reset, Toast.LENGTH_SHORT).show();
    }

}
