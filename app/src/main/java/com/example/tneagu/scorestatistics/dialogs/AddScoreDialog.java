package com.example.tneagu.scorestatistics.dialogs;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tneagu.scorestatistics.DataManager;
import com.example.tneagu.scorestatistics.R;
import com.example.tneagu.scorestatistics.models.Match;
import com.example.tneagu.scorestatistics.models.Team;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

@EFragment(R.layout.dialog_add_score)
public class AddScoreDialog extends CustomDialog {

    @ViewById(R.id.spinner_home_team)
    Spinner homeTeam;
    @ViewById(R.id.spinner_away_team)
    Spinner awayTeam;
    @ViewById(R.id.et_home_score)
    EditText homeScore;
    @ViewById(R.id.et_away_score)
    EditText awayScore;
    @ViewById(R.id.btn_save_score)
    Button saveButton;

    private String homeTeamName = "";
    private String awayTeamName = "";
    private int homeTeamGoals = 0;
    private int awayTeamGoals = 0;
    private OnAddScore onAddScore;

    //
    //LIFECYCLE
    //
    @AfterViews
    public void init(){
        initializeSpinner(homeTeam, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                homeTeamName = parent.getItemAtPosition(position).toString();
                enableSaveIfAllFieldsAreValid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        initializeSpinner(awayTeam, new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                awayTeamName = parent.getItemAtPosition(position).toString();
                enableSaveIfAllFieldsAreValid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setEnabled(false);
    }




    //
    //CALLBACKS
    //
    @TextChange(R.id.et_home_score)
    protected void onHomeScoreChanged(TextView textView, CharSequence text) {
        if(text.length() > 0){
            homeTeamGoals = Integer.valueOf(text.toString());
            enableSaveIfAllFieldsAreValid();
        }else{
            saveButton.setEnabled(false);
        }
    }

    @TextChange(R.id.et_away_score)
    protected void onAwayScoreChanged(TextView textView, CharSequence text) {
        if(text.length() > 0){
            awayTeamGoals = Integer.valueOf(text.toString());
            enableSaveIfAllFieldsAreValid();
        }else{
            saveButton.setEnabled(false);
        }

    }

    @Click(R.id.btn_save_score)
    protected void onSave(){
        Match match = new Match(homeTeamName, homeTeamGoals, awayTeamName, awayTeamGoals);
        onAddScore.onAddScore(match);
        dismiss();
    }


    //
    //PUBLIC IMPLEMENTATION
    //
    public void show(Context context, OnAddScore onAddScore) {
        if(!areAtLeastTwoTeams()) {
            Toast.makeText(context,
                    R.string.alert_not_enough_teams, Toast.LENGTH_LONG).show();
            return;
        }
        this.onAddScore = onAddScore;
        super.show(context);
    }


    //
    //PRIVATE IMPLEMENTATION
    //
    private void enableSaveIfAllFieldsAreValid() {
        boolean fieldsValid = true;

        if(homeTeamName.equalsIgnoreCase("") ||
                awayTeamName.equalsIgnoreCase("") ||
                awayTeamName.equalsIgnoreCase(homeTeamName) ||
                homeScore.getText().toString().equalsIgnoreCase("") ||
                awayScore.getText().toString().equalsIgnoreCase("")){
            fieldsValid = false;
        }

        saveButton.setEnabled(fieldsValid);
    }

    private boolean areAtLeastTwoTeams() {
        ArrayList<Team> teams = DataManager.getInstance().getTeams();
        return teams.size() >= 2;
    }

    private void initializeSpinner(Spinner spinner, AdapterView.OnItemSelectedListener listener) {
        List<String> spinnerElements = getSpinnerElements(spinner);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddScoreDialog.this.getActivity().getApplicationContext(),
                android.R.layout.simple_spinner_item, spinnerElements);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(listener);
    }

    private List<String> getSpinnerElements(Spinner spinner) {
        List<String> spinnerElements = new ArrayList<String>();
        ArrayList<Team> teams = DataManager.getInstance().getTeams();

        for(Team team : teams){
            spinnerElements.add(team.getTeamName());
        }

        return spinnerElements;
    }


    //
    //INTERFACES
    //
    public interface OnAddScore{
        void onAddScore(Match match);
    }
}
