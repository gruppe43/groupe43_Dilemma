package com.gruppe17.dilema;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gruppe17.dilema.Service.NotificationEventReceiver;

import java.util.ArrayList;
import java.util.Random;

public class myDilemmasActivity extends Activity {

    private View.OnClickListener aktuelleDilemmaButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(myDilemmasActivity.this, MyActivity.class);
            //Sender os til Login.class
            startActivity(newIntent);
            overridePendingTransition(0,0);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Sætter Layout
        setContentView(R.layout.mydilemma);

        Button myAktuelle = (Button) findViewById(R.id.myAktuelle);
        myAktuelle.setOnClickListener(aktuelleDilemmaButtonListener);


}
}