package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.novelflex.R;

public class SignUpActivity extends AppCompatActivity {

    private Button  SignUpActivityButton_ID;
    private LinearLayout linearLayoutSignUpTxt_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    // Initialization
        SignUpActivityButton_ID = (Button) findViewById(R.id.signUpActivity_BTn_ID);
        linearLayoutSignUpTxt_ID = (LinearLayout) findViewById(R.id.linaelayout_signUp_txt_ID);

    // SignUp Button Onclick Listener
        SignUpActivityButton_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                tabScreen();
            }
        });

    // SignUp Text Onclick Listener
        linearLayoutSignUpTxt_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                loginScreen();
            }
        });
    }


    // Navigate to Tab Activity Screen
    public void tabScreen()
    {
        Intent i=new Intent(this, TabActivity.class);
        startActivity(i);
        finish();
    }

    // Navigate to Login Activity Screen
    public void loginScreen()
    {
        Intent i=new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}