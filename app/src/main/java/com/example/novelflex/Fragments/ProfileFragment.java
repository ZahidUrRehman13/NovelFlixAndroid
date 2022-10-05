package com.example.novelflex.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.novelflex.Activities.GridActivity;
import com.example.novelflex.Activities.MyUploadActivity;
import com.example.novelflex.Activities.UploadMangaActivity;
import com.example.novelflex.R;

public class ProfileFragment extends Fragment {

    TextView MyMangaUploadButton_ID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        MyMangaUploadButton_ID = view.findViewById(R.id.login_btn_ID);

        MyMangaUploadButton_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyUploadActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });





//        user_Name.setText("Sorry No Data To Display!");






        return view;
    }
}