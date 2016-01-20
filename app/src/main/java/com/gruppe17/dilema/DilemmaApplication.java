package com.gruppe17.dilema;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class DilemmaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //Her laves vores shared preferences
       SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
       SharedPreferences.Editor editor = preferences.edit();
        //En shared preference har et navn og en string.
        editor.putString("firebaseApi", "https://dilemmadb.firebaseio.com/");
        //Shared preference commit sørger for at gemme den.
        editor.commit();
    }
}