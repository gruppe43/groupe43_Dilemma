package com.gruppe17.dilema;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class DilemmaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firebaseApi", "https://dilemmadb.firebaseio.com/");
        editor.commit();
    }
}
