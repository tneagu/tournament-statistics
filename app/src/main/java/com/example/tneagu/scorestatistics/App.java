package com.example.tneagu.scorestatistics;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPrefsManager.init(this);
    }
}
