package com.example.novelflex.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.novelflex.Activities.GridActivity;
import com.example.novelflex.Adapter.ThrillerAdapter;
import com.example.novelflex.Constants.ApiUtils;
import com.example.novelflex.Models.DataModelClass;
import com.example.novelflex.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public final static String ACTION_HOME_FRAGMENT = "action_home_fragment";
    private ArrayList<DataModelClass> thrillerModelArrayList;

    private ThrillerAdapter thrillerAdapter;

    private RecyclerView thriller_ID , romance_ID , fashion_ID ;

    private ProgressBar progressBar;

    private TextView seeAll_R_ID, seeAll_T_ID,seeAll_RO_ID,seeAll_F_ID;

    public final static String ACTION_FOR_IMAGE = "action_doctor_profile_id";
    public final static String ACTION_FOR_TITLE = "action_localization_doctor_profile_id";
    public final static String ACTION_FOR_DESCRIPTION = "action_doctor_profile_image";
    public final static String ACTION_FOR_AUTHOR = "action_author_profile";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        /// thriller RecyclerView
        thriller_ID = view.findViewById(R.id.thriller_recyclerView_ID);
        /// Romance RecyclerView
        romance_ID = view.findViewById(R.id.romance_recyclerView_ID);
        /// Fashion RecyclerView
        fashion_ID = view.findViewById(R.id.fashion_recyclerView_ID);

        // Progress Dialog
        progressBar = view.findViewById(R.id.progressBar);

      /// See All TextView
        seeAll_R_ID = view.findViewById(R.id.see_all_recent_id);

        seeAll_T_ID = view.findViewById(R.id.see_all_T);

        seeAll_RO_ID = view.findViewById(R.id.see_all_R);

        seeAll_F_ID = view.findViewById(R.id.see_all_F);

        thrillerModelArrayList = new ArrayList<>();

        // GET all data from API
        loadThrillersList();

        seeAll_R_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }


        });
        seeAll_T_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }


        });
        seeAll_RO_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }


        });
        seeAll_F_ID.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GridActivity.class);
                startActivity(intent);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }


        });


        return view;
    }
    private void loadThrillersList() {
        //getting the progressbar


        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiUtils.BASE_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);

                Log.d("Response", response.toString());

                for (int i = 0; i < response.length(); i++) {
                    // creating a new json object and
                    // getting each object from our json array.
                    try {
                        // we are getting each json object.
                        JSONObject thrillerObjects = response.getJSONObject(i);


//                        String image1 = thrillerObjects.getString("img");
//                        String titlem = thrillerObjects.getString("title");


                        //creating a thriller object and giving them the values from json object
                        DataModelClass dataModelClass = new DataModelClass(
                                thrillerObjects.getString("img"),
                                thrillerObjects.getString("title"),
                                thrillerObjects.getString("description"),
                                thrillerObjects.getString("author")
                        );

                        //adding the hero to herolist
                        thrillerModelArrayList.add(dataModelClass);

//                        Log.d("Response", image1);
//                        Log.d("Response", titlem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /// Thriller View
                thriller_ID.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL , false));
                thriller_ID.setFocusable(false);
                thriller_ID.setHasFixedSize(true);

                thrillerAdapter = new ThrillerAdapter(getContext(), thrillerModelArrayList);
                thriller_ID.setAdapter(thrillerAdapter);
                /// Thriller View
                romance_ID.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
                romance_ID.setFocusable(false);
                romance_ID.setHasFixedSize(true);

                thrillerAdapter = new ThrillerAdapter(getContext(), thrillerModelArrayList);
                romance_ID.setAdapter(thrillerAdapter);
                /// Fashion View
                fashion_ID.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
                fashion_ID.setFocusable(false);
                fashion_ID.setHasFixedSize(true);

                thrillerAdapter = new ThrillerAdapter(getContext(), thrillerModelArrayList);
                fashion_ID.setAdapter(thrillerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        //adding the array request to request queue
        requestQueue.add(jsonArrayRequest);
    }
}