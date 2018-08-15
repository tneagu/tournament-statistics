package com.example.tneagu.scorestatistics.dialogs;


import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tneagu.scorestatistics.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.dialog_add_team)
public class AddTeamDialog extends CustomDialog {
    private OnAddTeamCallback onAddTeam;
    @ViewById(R.id.et_teamname)
    EditText teamName;
    @ViewById(R.id.btn_save)
    Button saveButton;

    //
    //CALLBACKS
    //
    @Click(R.id.btn_save)
    public void onSave(){
        if(onAddTeam != null){
            onAddTeam.onAddTeam(teamName.getText().toString());
        }
        hideSoftKeyboard(teamName);
        dismiss();
    }

    @TextChange(R.id.et_teamname)
    protected void onNameTextChanged(TextView textView, CharSequence text) {
        if(text.length() != 0){
            saveButton.setEnabled(true);
        }else{
            saveButton.setEnabled(false);
        }
    }

    //
    //LIFECYCLE
    //
    @AfterViews
    public void init(){
        saveButton.setEnabled(false);
    }

    //
    //PUBLIC IMPLEMENTATION
    //
    public void show(Context context, OnAddTeamCallback onAddTeam) {
        super.show(context);
        this.showKeyboard(context);
        this.onAddTeam = onAddTeam;
    }

    //
    //INTERFACES
    //
    public interface OnAddTeamCallback{
        void onAddTeam(String teamName);
    }

}
