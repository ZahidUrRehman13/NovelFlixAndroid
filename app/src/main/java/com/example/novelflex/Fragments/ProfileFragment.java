package com.example.novelflex.Fragments;

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
import com.example.novelflex.R;

public class ProfileFragment extends Fragment {

    TextView user_Name;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        user_Name = view.findViewById(R.id.data_txt_view);




        user_Name.setText("Sorry No Data To Display!");






        return view;
    }
}