package com.gruppe17.dilema;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
//MyActivity er vores hovedklasse hvor vi starter fra.
public class MyActivity extends Activity {
   //Button listeners.
    private View.OnClickListener newDilemmaButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent newIntent = new Intent(MyActivity.this, newDilemaActivity.class);
            //Sender os til newDilemaActivity.class
            startActivity(newIntent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    };

    private View.OnClickListener newLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(MyActivity.this, Login.class);
            //Sender os til Login.class
            startActivity(newIntent);
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        }
    };

    private View.OnClickListener myDilemmasListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(MyActivity.this, myDilemmasActivity.class);
            //Sender os til Login.class
            startActivity(newIntent);
            overridePendingTransition(0,0);
             }
    };


    private View.OnClickListener dilemaBodyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(MyActivity.this, DilemmaActivity.class);
            //Sender os til DilemmaActivity.class
            startActivity(newIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Shared prefference bruges til små mængder information som bruges i hele appen.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //shared preference firebaseApi assignes til api.
        String api = preferences.getString("firebaseApi", "DB connection not accessible");

        //Sætter firbase op.
        Firebase.setAndroidContext(this);
        //Assign DB connection til root
        Firebase root = new Firebase(api+"Dilemma");
        //Laver en Arraylist og gøre den final så den kan bruges fra hele klassen.
        final ArrayList<Dilemma> dilemmas = new ArrayList<Dilemma>();
        final ArrayList<String> dilemmasView = new ArrayList<String>();
        //Laver en eventlistener fra firebase
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    long dilemmaId = (long) child.child("DilemmaId").getValue();
                    String dilemmaTitel = (String) child.child("DilemmaTitel").getValue();
                    String dilemmaBody = (String) child.child("DilemmaBody").getValue();
                    String dilemmaRating = (String) child.child("DilemmaRating").getValue();
                    dilemmas.add(new Dilemma(dilemmaId, dilemmaTitel, dilemmaBody, dilemmaRating));
                    dilemmasView.add(dilemmaTitel + " Vægt: " + dilemmaRating);
                }

                ArrayAdapter<Dilemma> adapter = new ArrayAdapter<>(
                        MyActivity.this, android.R.layout.simple_list_item_1, dilemmas);
                final ListView dilemmaList = (ListView) findViewById(R.id.listView);

                dilemmaList.setAdapter(adapter);
                dilemmaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int itemPosition = position;

                        String value = (String) dilemmaList.getItemAtPosition(position).toString();
                        String dilemmaId = value.substring(0, 7);
                        Intent intent = new Intent(MyActivity.this, DilemmaActivity.class);
                        Bundle b = new Bundle();
                        b.putString("dilemmaTitel", dilemmaId);
                        intent.putExtras(b);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        setContentView(R.layout.activity_my);



        TextView login = (TextView) findViewById(R.id.menu_login);
        login.setOnClickListener(newLoginListener);

        Button myDilemmas = (Button) findViewById(R.id.myDilemmas);

        myDilemmas.setOnClickListener(myDilemmasListener);

        //Definere newButton
        Button newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(newDilemmaButtonListener);
        //Sætter en listener til at lytte efter click på newButton


    }


}
