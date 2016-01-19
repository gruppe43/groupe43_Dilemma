package com.gruppe17.dilema;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DilemmaActivity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dilemma);
        Bundle b = getIntent().getExtras();
        String value = b.getString("dilemmaTitel");
        System.out.println(value);


    }
}
