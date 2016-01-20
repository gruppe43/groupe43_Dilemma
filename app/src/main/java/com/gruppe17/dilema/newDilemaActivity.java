package com.gruppe17.dilema;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.Random;


public class newDilemaActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dilema_new);
        //Shared preferences allow local save of information
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Assign DB connection to string api, if not accessible print "DB connection not accessible";
        String api = preferences.getString("firebaseApi", "DB connection not accessible");

        //Setup android to use Firebase;
        Firebase.setAndroidContext(this);
        //Assign DB connection to root
        final Firebase root = new Firebase(api);

        Button backButton = (Button)findViewById(R.id.backButtonNew);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(newDilemaActivity.this, MyActivity.class);
                //Sender os til DilemmaActivity.class
                startActivity(newIntent);
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
            }
        });

        final TextView newDillemaTitel = (TextView) findViewById(R.id.newDilemmaTitel);


        final TextView newDillemaBody = (TextView) findViewById(R.id.newDilemmaBody);

        final Spinner newDillemaRating = (Spinner) findViewById(R.id.newDilemmaDegree);
        Button submitButton = (Button)findViewById(R.id.newDilemmaSubmit);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0){
                Random r = new Random();
                int dilemmaId = r.nextInt(9999999)+1000000;

                String dilemmaTitel = newDillemaTitel.getText().toString();
                String dilemmaBody = newDillemaBody.getText().toString();
                String dilemmaRating = newDillemaRating.getSelectedItem().toString();

                root.child("Dilemma/" + dilemmaId + "/DilemmaId").setValue(dilemmaId);
                root.child("Dilemma/" + dilemmaId +"/DilemmaTitel").setValue(dilemmaTitel);
                root.child("Dilemma/" + dilemmaId +"/DilemmaBody").setValue(dilemmaBody);
                root.child("Dilemma/" + dilemmaId +"/DilemmaRating").setValue(dilemmaRating);

                Log.d("Test of get text", newDillemaTitel.getText().toString());
                Log.d("Test of get text", newDillemaBody.getText().toString());
            }
        });

    }

}
