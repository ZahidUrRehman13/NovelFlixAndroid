package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novelflex.R;

import java.util.regex.Pattern;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText  email_ID;
    private AppCompatButton sendButton_ID;
    private ProgressBar loadingPB;
    private ImageView settingButton_ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        email_ID = findViewById(R.id.email_fg);
        sendButton_ID = (AppCompatButton) findViewById(R.id.sendButton_ID);
        loadingPB = findViewById(R.id.progress_fg);
        settingButton_ID = findViewById(R.id.settingButtonfg_ID);
        sendButton_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                if(isValidEmailId(email_ID.getText().toString().trim())){
                    loadingPB.setVisibility(View.VISIBLE);
                    LoginScreen();
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.email_send_text_success), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ForgetPasswordActivity.this, getResources().getString(R.string.enterCorrect_email), Toast.LENGTH_SHORT).show();

                }
            }
        });

        settingButton_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
            }
        });

    }
    // Email validation string
    private boolean isValidEmailId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    // Home Activity Method
    public void LoginScreen()
    {
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}