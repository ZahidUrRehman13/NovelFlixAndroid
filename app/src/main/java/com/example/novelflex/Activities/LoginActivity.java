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

//                            LoginResponseApi(UserName_Txt_ID.getText().toString().trim(), Password_Txt_ID.getText().toString().trim());
                              tabScreen();
                            Toast.makeText(LoginActivity.this, getResources().getString(R.string.login_success), Toast.LENGTH_SHORT).show();

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
    private void LoginResponseApi(String email, String password) {
        // url to post our data
//        String url = "https://api-yas.broomstickcreative.com/api/auth/local";
        loadingPB.setVisibility(View.VISIBLE);

        Map<String, String> params = new HashMap<String, String>();
//        params.put("identifier", "as@msn.com");
//        params.put("password", "manager");
        params.put("identifier", email);
        params.put("password", password);
        JSONObject objRegData = new JSONObject(params);

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, ApiUtils.BASE_URL, objRegData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.d("API_RESPONSE_IS", response.toString());
                        loadingPB.setVisibility(View.GONE);

                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.login), Toast.LENGTH_SHORT).show();
                        try {

                            JSONObject userDetail = response.getJSONObject("user");
                            Log.d("API_RESPONSE_IS", userDetail.toString());

                            SharedPrefManager.setUserID(getApplicationContext(), "id", userDetail.getString("id"));
                            SharedPrefManager.setUserName(getApplicationContext(), "username", userDetail.getString("username"));
                            SharedPrefManager.setUserEmail(getApplicationContext(), "email", userDetail.getString("email"));
                            SharedPrefManager.setUserPhone(getApplicationContext(), "phone_number", userDetail.getString("Phone_number"));


                            Log.d("Saved email", SharedPrefManager.getUserEmail(getApplicationContext(), "email"));


                            Intent intent = new Intent(LoginActivity.this, TabActivity.class);
                            startActivity(intent);
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loadingPB.setVisibility(View.GONE);
//                if (error.networkResponse.statusCode == 400) {
                        Toast.makeText(LoginActivity.this, getResources().getString(R.string.invalid_email_or_password_login), Toast.LENGTH_SHORT).show();
//                }
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        queue.add(jsObjRequest);
    }

}