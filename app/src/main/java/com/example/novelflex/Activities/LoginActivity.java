package com.example.novelflex.Activities;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.novelflex.R;

public class LoginActivity extends AppCompatActivity {

    private Button SignInButton_ID;
    private LinearLayout SignUpLayout_ID;
    private EditText UserName_Txt_ID, Password_Txt_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    // Initialize Button
        SignInButton_ID = (Button) findViewById(R.id.login_btn_ID);
        SignUpLayout_ID = (LinearLayout) findViewById(R.id.singUp_Layout_ID);

    // Initialization  Edit Text
        UserName_Txt_ID = (EditText) findViewById(R.id.user_name_Edit_txt);
        Password_Txt_ID = (EditText) findViewById(R.id.Password_Edit_txt);

    // Navigate to Tab Activity screen
        SignInButton_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                tabScreen();
            }
        });

    // Navigate to SignUp Activity screen
        SignUpLayout_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                SignUpActivity();
            }
        });
    }

    // Home Activity Method
    public void tabScreen()
    {
        Intent i=new Intent(this, TabActivity.class);
        startActivity(i);
        finish();
    }

    // SignUp Activity Method
    public void SignUpActivity()
    {
        Intent i=new Intent(this, SignUpActivity.class);
        startActivity(i);
        finish();
    }

}