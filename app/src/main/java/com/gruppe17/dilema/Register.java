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
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.registerSubmit:
                Log.d("Register: ","Register not yet implimented");
                startActivity(new Intent(this, MyActivity.class));
                break;
            case R.id.registerLoginView:
                startActivity(new Intent(this, Login.class));

                break;
        }
    }
}
