package com.example.tneagu.scorestatistics.dialogs;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.tneagu.scorestatistics.R;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public class CustomDialog extends DialogFragment {
    private static final String TAG = "CustomDialog";

    public void show(Context context) {
        if (context != null) {
            if (context instanceof FragmentActivity) {
                show(((FragmentActivity) context).getFragmentManager(), TAG);
            }
        }
    }

    protected void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    protected void hideSoftKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
