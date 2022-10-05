package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.novelflex.R;

public class MyUploadActivity extends AppCompatActivity {
    ImageView Back_button_ID, AddButton_ID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_upload);


        Back_button_ID = findViewById(R.id.settingButton_uploadID);
        AddButton_ID = findViewById(R.id.add_btn_id);

        Back_button_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        AddButton_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent login = new Intent(MyUploadActivity.this,UploadMangaActivity.class);
                startActivity(login);
            }
        });


    }
}