package com.example.tneagu.scorestatistics.activities;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tneagu.scorestatistics.DataManager;
import com.example.tneagu.scorestatistics.R;
import com.example.tneagu.scorestatistics.adapters.ScoreTableAdapter;
import com.example.tneagu.scorestatistics.adapters.StatsTableAdapter;
import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;
import com.example.tneagu.scorestatistics.presenters.StatsPresenter;
import com.example.tneagu.scorestatistics.presenters.interfaces.IStatsPresenter;
import com.example.tneagu.scorestatistics.presenters.interfaces.IStatsView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_stats)
public class StatsActivity extends Activity implements IStatsView {

    @ViewById(R.id.recycler_view_statistics)
    RecyclerView statsRecycler;
    @ViewById(R.id.recycler_view_scoretable)
    RecyclerView scoreRecycler;

    private StatsTableAdapter statsTableAdapter;
    private ScoreTableAdapter scoreTableAdapter;

    @Bean(StatsPresenter.class)
    IStatsPresenter statsPresenter;

    //
    //STATIC
    //
    public static void openActivity(Activity context){
        StatsActivity_.intent(context).start();
    }



    //
    //LIFECYCLE
    //
    @AfterViews
    public void init(){
        statsPresenter.setCallback(this);
        statsPresenter.getDataForScoreTable();
        setupScoreRecyclerView();
        setupStatsRecyclerView();
    }

    //
    //CALLBACKS
    //
    @Click(R.id.sort_name)
    public void sortByName(){
        statsPresenter.sortTeamsBy(StatsPresenter.SORT_NAME);
    }

    @Click(R.id.sort_matches)
    public void sortByMatches(){
        statsPresenter.sortTeamsBy(StatsPresenter.SORT_MATCHES);
    }

    @Click(R.id.sort_wins)
    public void sortByWins(){
        statsPresenter.sortTeamsBy(StatsPresenter.SORT_WINS);
    }

    @Click(R.id.sort_scored)
    public void sortByGoalsScored(){
        statsPresenter.sortTeamsBy(StatsPresenter.SORT_SCORED);
    }

    @Click(R.id.sort_conceived)
    public void sortByGoalsConceived(){
        statsPresenter.sortTeamsBy(StatsPresenter.SORT_CONCEIVED);
    }


    //
    //IStatsView
    //
    @Override
    @UiThread
    public void onDataForScoreTableReady(ArrayList<String> data, int noOfColumns) {
        if(scoreTableAdapter != null){
            scoreTableAdapter.setItems(data);
        }

    }

    @Override
    @UiThread
    public void onGetTeams(ArrayList<Team> teams) {
        if(statsTableAdapter != null){
            statsTableAdapter.setItems(teams);
        }
    }

    @Override
    @UiThread
    public void onSortTeams(ArrayList<Team> teams) {
        if(statsTableAdapter != null){
            statsTableAdapter.setItems(teams);
        }
    }


    //
    //PRIVATE IMPLEMENTATION
    //
    private void setupScoreRecyclerView() {
        int noOfColumns = DataManager.getInstance().getTeams().size() + 1;
        scoreRecycler.setLayoutManager(new GridLayoutManager(StatsActivity.this, noOfColumns));
        scoreTableAdapter = new ScoreTableAdapter();
        scoreRecycler.setAdapter(scoreTableAdapter);
    }

    private void setupStatsRecyclerView() {
        statsRecycler.setLayoutManager(new LinearLayoutManager(StatsActivity.this));
        statsTableAdapter = new StatsTableAdapter();
        statsRecycler.setAdapter(statsTableAdapter);
        statsPresenter.getTeams();
    }


}
