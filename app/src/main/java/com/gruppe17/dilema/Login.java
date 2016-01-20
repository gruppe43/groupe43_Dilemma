package com.gruppe17.dilema;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements View.OnClickListener{

    Button loginBtn;
    EditText loginUsername, loginPassword;
    TextView loginRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        //Felter vi bruger registreres.
        loginUsername = (EditText) findViewById(R.id.loginUsernameEnter);
        loginPassword = (EditText) findViewById(R.id.loginPasswordEnter);
        loginBtn = (Button) findViewById(R.id.LoginSubmit);
        loginRegister = (TextView) findViewById(R.id.loginRegisterText);
        //Listeners på knapper.
        loginBtn.setOnClickListener(this);
        loginRegister.setOnClickListener(this);


        Button backButton = (Button)findViewById(R.id.backButtonLogin);
        backButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(Login.this, MyActivity.class);
                //Sender os til DilemmaActivity.class
                startActivity(newIntent);
                overridePendingTransition(R.anim.slide_enter, R.anim.slide_exit);
            }
        });
    }

    @Override
    public void onClick(View v) {
        //Switch der lutter efter hvilken knap der trykkes på
        switch(v.getId()){
            case R.id.LoginSubmit:
                Log.d("Login: ", "Login not yet implimented");
                //Sender os til MyActivity.class
                startActivity(new Intent(this, MyActivity.class));
                break;
            case R.id.loginRegisterText:
                Log.d("Register: ", "Navigating from Login to Register");
                //Sender so til Register.class
                startActivity(new Intent(this, Register.class));

                break;
        }


    }
}
