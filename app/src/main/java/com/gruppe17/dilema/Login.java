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

        loginUsername = (EditText) findViewById(R.id.loginUsernameEnter);
        loginPassword = (EditText) findViewById(R.id.loginPasswordEnter);
        loginBtn = (Button) findViewById(R.id.LoginSubmit);
        loginRegister = (TextView) findViewById(R.id.loginRegisterText);
        loginBtn.setOnClickListener(this);
        loginRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LoginSubmit:
                Log.d("Login: ", "Login not yet implimented");
                startActivity(new Intent(this, MyActivity.class));
                break;
            case R.id.loginRegisterText:
                Log.d("Register: ", "Navigating from Login to Register");
                startActivity(new Intent(this, Register.class));

                break;
        }
    }
}
