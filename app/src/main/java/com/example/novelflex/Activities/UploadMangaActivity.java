package com.example.novelflex.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.novelflex.R;

public class UploadMangaActivity extends AppCompatActivity {
    Spinner spinnerSelectItem;
    ImageView Back_button_ID;
    String[] users = {"Select Generals", "Thriller", "Romance",
            "Fashion", "Gold",  };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_manga);
        spinnerSelectItem = (Spinner) findViewById(R.id.spinner_generals);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectItem.setAdapter(adapter);
        Back_button_ID = findViewById(R.id.settingButton_MangaID);

        Back_button_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}