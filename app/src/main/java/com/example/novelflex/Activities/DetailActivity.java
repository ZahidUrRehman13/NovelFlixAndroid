package com.example.novelflex.Activities;


import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_AUTHOR;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_DESCRIPTION;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_IMAGE;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_TITLE;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.novelflex.R;

public class DetailActivity extends AppCompatActivity {

    String image_url;
    String title;
    String descriptions;
    String author;

    ImageView Image_ID, Profile_IMG_ID, backButton_ID;
    TextView Title_ID;
    TextView Description_ID;
    TextView Author_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Image_ID = findViewById(R.id.image_view_ID);
        Profile_IMG_ID = findViewById(R.id.profile_img_ID);
        Title_ID = findViewById(R.id.title_text_ID);
        Description_ID = findViewById(R.id.description_id);
        Author_ID = findViewById(R.id.subTitle);
        backButton_ID = findViewById(R.id.backButton_ID);


        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            image_url = extras.getString(ACTION_FOR_IMAGE);
            title = extras.getString(ACTION_FOR_TITLE);
            descriptions = extras.getString(ACTION_FOR_DESCRIPTION);
            author = extras.getString(ACTION_FOR_AUTHOR);


            Glide.with(getApplicationContext()).load(image_url).into(Image_ID);
            Glide.with(getApplicationContext()).load(image_url).into(Profile_IMG_ID);
            Title_ID.setText(title);
            Description_ID.setText(descriptions);
            Author_ID.setText(author);
        }

        backButton_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}