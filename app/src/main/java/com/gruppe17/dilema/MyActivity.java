package com.gruppe17.dilema;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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


        setContentView(R.layout.activity_my);



        TextView login = (TextView) findViewById(R.id.menu_login);
        login.setOnClickListener(newLoginListener);


        //Definere newButton
        Button newButton = (Button) findViewById(R.id.newButton);

        newButton.setOnClickListener(newDilemmaButtonListener);
        //Sætter en listener til at lytte efter click på newButton
        ll = (LinearLayout) findViewById(R.id.arrayLayout);
        CharSequence[] dilemaArray = getResources().getTextArray(R.array.Dilemmas);
        CharSequence[] dilemabodyArray = getResources().getTextArray(R.array.DilemmasBody);
        CharSequence[] dilemalevelArray = getResources().getTextArray(R.array.DilemmasLevel);
        this.overridePendingTransition(R.anim.animation, R.anim.animation2);
        int arraySize = dilemaArray.length;
        for(int i = 0; i < arraySize; i++) {
            dilemaTextView = new TextView(this);
            dilemaBodyView = new TextView(this);
            dilemaBodyView.setOnClickListener(dilemaBodyListener);
            dilemaTextView.append(dilemaArray[i].toString() + " Niveau: " + dilemalevelArray[i].toString());
            dilemaBodyView.append(dilemabodyArray[i]);
            dilemaBodyView.append("\n");
            ll.addView(dilemaTextView);
            ll.addView(dilemaBodyView);
        }

    }
}
