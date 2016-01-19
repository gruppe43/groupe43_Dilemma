package com.gruppe17.dilema;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

public class Register extends Activity implements View.OnClickListener{

    TextView registerLogin;
    Button regBtn;
    EditText regUsername, regPassword, regPasswordRepeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        regUsername = (EditText) findViewById(R.id.registerPasswordEnter);
        regPassword = (EditText) findViewById(R.id.registerPasswordEnter);
        regPasswordRepeat = (EditText) findViewById(R.id.registerPasswordEnterRepeat);
        regBtn = (Button) findViewById(R.id.registerSubmit);
        regBtn.setOnClickListener(this);
        registerLogin = (TextView) findViewById(R.id.registerLoginView);
        registerLogin.setOnClickListener(this);

        //Shared preferences allow local save of information

        //Assign DB connection to string api, if not accessible print "DB connection not accessible";


        //Setup android to use Firebase;
        Firebase.setAndroidContext(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerSubmit:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String api = preferences.getString("firebaseApi", "DB connection not accessible");
                Log.d("Register: ","Register not yet implimented");
                String reqUsername = regUsername.getText().toString();
                String reqPassword = regPassword.getText().toString();
                Firebase root = new Firebase(api);

                if(reqPassword.equals(regPasswordRepeat.getText().toString())) {
                    root.child("User/" + reqUsername + "/Username").setValue(reqUsername);
                    root.child("User/" + reqUsername + "/Password").setValue(reqPassword);
                }
                else{
                    // TODO: 18-01-2016 error in password
                }
                startActivity(new Intent(this, MyActivity.class));
                break;
            case R.id.registerLoginView:
                startActivity(new Intent(this, Login.class));

                break;
        }
    }
}
