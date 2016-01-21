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
import android.widget.EditText;
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

public class DilemmaActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Sætter Layout
        setContentView(R.layout.dilemma);
        //Tager imod bundle sendt fra MyActivity
        Bundle b = getIntent().getExtras();
        final String value = b.getString("dilemmaTitel");

        //Shared preferences bruges til at forbinde til vores API
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Tager imod Shared Prefference, og sætter en default hvis den ikke kan modtages
        String api = preferences.getString("firebaseApi", "DB connection not accessible");
        //Gør android klar til Firebase
        Firebase.setAndroidContext(this);
        //Binder vores forbindelse til root
        final Firebase root = new Firebase(api+"Dilemma/"+value+"/");

        Button backButton = (Button)findViewById(R.id.backButtonDilemma);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(DilemmaActivity.this, MyActivity.class);
                //Sender os til DilemmaActivity.class
                startActivity(newIntent);
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
            }
        });

        //Laver en event listener.
        root.addValueEventListener(new ValueEventListener() {
            @Override
            //Når data ændres sendes et snapshot med ændringer.
            public void onDataChange(DataSnapshot snapshot) {
                //Tager imod de forskellige attributter vi vil kende.
                String dilemmaTitel = (String) snapshot.child("DilemmaTitel").getValue();
                String dilemmaBody = (String) snapshot.child("DilemmaBody").getValue();
                String dilemmaRating = (String) snapshot.child("DilemmaRating").getValue();
                root.child("/ChangeMade").setValue(false);
                TextView Titel = (TextView) findViewById(R.id.dilemmatitle);
                TextView Body = (TextView) findViewById(R.id.dilemmaTextBody);
                //Opdatere textviews til at vise de informationer vi har fået.
                Titel.setText(dilemmaTitel);
                Body.setText(dilemmaBody);


                final ArrayList<String> comments = new ArrayList<>();
                for (DataSnapshot child: snapshot.child("Comments").getChildren()){
                    String commentBody = (String) child.child("Commentbody").getValue();
                    System.out.println(commentBody);
                    comments.add(commentBody);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        DilemmaActivity.this, android.R.layout.simple_list_item_1, comments);
                final ListView dilemmaList = (ListView) findViewById(R.id.commentList);

                dilemmaList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //Hvis noget går galt printes en fejl.
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        root.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to the database
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                String dilemma = snapshot.toString();
                NotificationEventReceiver.setupAlarm(getApplicationContext());
                Log.d("ChildAdded: ", snapshot.toString());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                NotificationEventReceiver.setupAlarm(getApplicationContext());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        Button submitButton = (Button)findViewById(R.id.commentButton);
        final TextView commentBody = (TextView)findViewById(R.id.dilemmaAnswerText);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Random r = new Random();
                int commentId = r.nextInt(9999999) + 1000000;
                String Body = commentBody.getText().toString();
                root.child("/ChangeMade").setValue(true);
                root.child("/Comments/" + commentId + "/Commentbody").setValue(Body);

            }
        });
    }
}
