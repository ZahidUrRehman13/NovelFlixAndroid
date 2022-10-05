package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.novelflex.Constants.Constant;
import com.example.novelflex.LocalCache.SharedPrefManager;
import com.example.novelflex.R;

public class SplashActivity extends AppCompatActivity {

    // Time Delay for 3 seconds
    static int splashTime=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPrefManager.getSharedPref(getApplicationContext());


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (SharedPrefManager.getUserID(SplashActivity.this, "accessToken").isEmpty()) {
                    Intent i=new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Intent i=new Intent(SplashActivity.this,TabActivity.class);
                    startActivity(i);
                    finish();
                }




            }
        },splashTime);
    }
}