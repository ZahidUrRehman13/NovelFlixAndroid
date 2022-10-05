package com.example.novelflex.Activities;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.novelflex.Constants.ApiUtils;
import com.example.novelflex.Constants.InternetConnection;
import com.example.novelflex.LocalCache.SharedPrefManager;
import com.example.novelflex.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private TextView SignInButton_ID;
    private LinearLayout SignUpLayout_ID;
    private EditText UserName_Txt_ID, Password_Txt_ID;
    private TextView ForgetPassword_ID;
    ProgressBar loadingPB;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    // Initialize Button
        SignInButton_ID = (TextView) findViewById(R.id.login_btn_ID);
        SignUpLayout_ID = (LinearLayout) findViewById(R.id.layout_id_signup);
        loadingPB = findViewById(R.id.idLoadingPBlogin);

    // Initialization  Edit Text
        UserName_Txt_ID = (EditText) findViewById(R.id.user_name_Edit_txt);
        Password_Txt_ID = (EditText) findViewById(R.id.Password_Edit_txt);
        ForgetPassword_ID = (TextView) findViewById(R.id.forgetPassword_id);

    // Navigate to Tab Activity screen
        SignInButton_ID.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
            public final void onClick(View it) {


                if (isValidEmailId(UserName_Txt_ID.getText().toString().trim())) {
                    if (Password_Txt_ID.getText().toString().trim().length() >= 6) {

                        if (InternetConnection.checkConnection(LoginActivity.this)) {

                            Register(UserName_Txt_ID.getText().toString().trim(), Password_Txt_ID.getText().toString().trim());

                        } else {
                            new AlertDialog.Builder(LoginActivity.this)
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


                    } else {
//                        if (langChoice == 0) {
                        Password_Txt_ID.setError("Password Length must be 6 or bigger");
//                        } else {
//                            passwordEditText.setError("يجب أن يكون طول كلمة المرور 6 أو أكبر");
//                        }


                    }
                } else {
//                    if (langChoice == 0) {
                    UserName_Txt_ID.setError("Email Address is not Valid");
//                    } else {
//                        emailEditText.setError("عنوان البريد الإلكتروني غير صالح");
//                    }


                }
            }
        }));

    // Navigate to SignUp Activity screen
        SignUpLayout_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                SignUpActivity();
            }
        });

        ForgetPassword_ID.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                ForgetPasswordActivity();
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
//        finish();
    }

    // ForgetPassword Activity Method
    public void ForgetPasswordActivity()
    {
//        Intent i=new Intent(this, ForgetPasswordActivity.class);
        Intent i=new Intent(this, TabActivity.class);
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

    // Login Api response


    private void Register(String email, String password)
    {
        loadingPB.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://dats.digitecglobal.com/novel/auth/login",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.e("response",response);
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
//
                            if(success.equals("200")){

                                JSONObject data = jsonObject.getJSONObject("data");

                                SharedPrefManager.setUserID(getApplicationContext(), "accessToken", data.getString("accessToken"));
                                SharedPrefManager.setUserName(getApplicationContext(), "fname", data.getString("fname"));
                                SharedPrefManager.setUserEmail(getApplicationContext(), "email", data.getString("email"));

                                Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();
                                loadingPB.setVisibility(View.GONE);
                                Intent login = new Intent(LoginActivity.this,TabActivity.class);
                                startActivity(login);
                                finish();
                            }else{
                                loadingPB.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(),"Invalid Email or Password",Toast.LENGTH_LONG).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"Login Error! try again"+e,Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                loadingPB.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Login Error try again",Toast.LENGTH_LONG).show();


            }
        })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("email",email);
                params.put("password",password);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}