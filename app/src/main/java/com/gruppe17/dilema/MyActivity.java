package com.gruppe17.dilema;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyActivity extends Activity {

    private View.OnClickListener newDilemmaButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          Intent newIntent = new Intent(MyActivity.this, newDilemaActivity.class);

            startActivity(newIntent);

        }
    };

    private View.OnClickListener newLoginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(MyActivity.this, Login.class);

            startActivity(newIntent);

        }
    };

    private View.OnClickListener dilemaBodyListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent newIntent = new Intent(MyActivity.this, DilemmaActivity.class);
            startActivity(newIntent);


        }
    };
    LinearLayout ll;
    TextView dilemaTextView;
    TextView dilemaBodyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Shared preferences allow local save of information
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Assign DB connection to string api, if not accessible print "DB connection not accessible";
        String api = preferences.getString("firebaseApi", "DB connection not accessible");

        //Setup android to use Firebase;
        Firebase.setAndroidContext(this);
        //Assign DB connection to root
        Firebase root = new Firebase(api+"Dilemma");

        Log.d("Firebase connection", root.toString());
        final ArrayList<String> dilemmaTitels = new ArrayList<>();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child: snapshot.getChildren()){

                    String dilemmaTitel = child.child("DilemmaTitel").getValue() + " Rateing: " + child.child("DilemmaRating").getValue();
                    dilemmaTitels.add(dilemmaTitel);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        MyActivity.this, android.R.layout.simple_list_item_1, dilemmaTitels);
                final ListView dilemmaList = (ListView) findViewById(R.id.listView);
                dilemmaList.setAdapter(adapter);
                dilemmaList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        int itemPosition = position;

                        String value = (String) dilemmaList.getItemAtPosition(position);
                        Intent intent = new Intent(MyActivity.this, DilemmaActivity.class);
                        Bundle b = new Bundle();
                        b.putString("dilemmaTitel", value);
                        intent.putExtras(b);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });


        setContentView(R.layout.activity_my);



        int n=10;

        Query queryRef = root.orderByKey().limitToFirst(n);
        queryRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChild) {
                Map<String, String> value = (Map<String, String>) snapshot.getValue();
                List<String> list = new ArrayList<String>(value.values());
                System.out.println(list);

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
            // ....
        });



        TextView login = (TextView) findViewById(R.id.menu_login);
        login.setOnClickListener(newLoginListener);


        //Definere newButton
        Button newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(newDilemmaButtonListener);
        //Sætter en listener til at lytte efter click på newButton


    }


}
