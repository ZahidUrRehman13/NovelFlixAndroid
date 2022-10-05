package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.novelflex.Constants.InternetConnection;
import com.example.novelflex.R;

import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity {

    private Button  SignUpActivityButton_ID;
    private LinearLayout linearLayoutSignUpTxt_ID;
    private EditText email_ID,firstName_ID,LastName_ID,password_ID,confirm_password_ID;
    private ImageView settingButton_ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

    // Initialization
        SignUpActivityButton_ID = (Button) findViewById(R.id.signUpActivity_BTn_ID);
        linearLayoutSignUpTxt_ID = (LinearLayout) findViewById(R.id.linaelayout_signUp_txt_ID);
        email_ID = (EditText) findViewById(R.id.email_id);
        firstName_ID = (EditText) findViewById(R.id.first_name_id);
        LastName_ID = (EditText) findViewById(R.id.last_name_id);
        password_ID = (EditText) findViewById(R.id.password_id);
        confirm_password_ID = (EditText) findViewById(R.id.confirm_password_id);
        settingButton_ID=(ImageView)findViewById(R.id.settingButton_SignUpID);

        // Navigate to Tab Activity screen
        SignUpActivityButton_ID.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
            public final void onClick(View it) {


               if(!firstName_ID.getText().toString().trim().equals("")){
                   if(!LastName_ID.getText().toString().trim().equals("")){
                       if (isValidEmailId(email_ID.getText().toString().trim())) {
                           if (password_ID.getText().toString().trim().length() >= 6) {
                             if(confirm_password_ID.getText().toString().matches(password_ID.getText().toString())){

                                 if (InternetConnection.checkConnection(SignUpActivity.this)) {

//                                     LoginResponseApi(UserName_Txt_ID.getText().toString().trim(), Password_Txt_ID.getText().toString().trim());
                                       tabScreen();
                                     Toast.makeText(SignUpActivity.this, getResources().getString(R.string.register_success), Toast.LENGTH_SHORT).show();

                                 } else {
                                     new AlertDialog.Builder(SignUpActivity.this)
                                             .setIcon(android.R.drawable.ic_dialog_alert)
                                             .setTitle("No Internet Connection")
                                             .setMessage("Please Check Your Internet Connection")
                                             .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                                 @Override
                                                 public void onClick(DialogInterface dialogInterface, int i) {
                                                     finish();
                                                 }
                                             }).show();
                                 }


                             }else{
                                 confirm_password_ID.setError("Password Not Matches");
                             }
                           } else {
//                        if (langChoice == 0) {
                               password_ID.setError("Password Length must be 6 or bigger");
//                        } else {
//                            passwordEditText.setError("يجب أن يكون طول كلمة المرور 6 أو أكبر");
//                        }


                           }
                       } else {
//                    if (langChoice == 0) {
                           email_ID.setError("Enter Valid Email");
//                    } else {
//                        emailEditText.setError("عنوان البريد الإلكتروني غير صالح");
//                    }


                       }

                   }else{
                       LastName_ID.setError("Please enter last name");
                   }
               }else{
                   firstName_ID.setError("Please enter first name");
               }
            }
        }));


        // SignUp Text Onclick Listener
        linearLayoutSignUpTxt_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                loginScreen();
            }
        });
        settingButton_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                finish();
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
//        finish();
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
}