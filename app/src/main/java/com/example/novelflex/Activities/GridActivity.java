package com.example.novelflex.Activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.novelflex.Adapter.GridAdapter;
import com.example.novelflex.Constants.ApiUtils;
import com.example.novelflex.Models.DataModelClass;
import com.example.novelflex.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GridActivity extends AppCompatActivity {

    private ArrayList<DataModelClass> dataModelArrayList;
    private RecyclerView gridRecycler_ID;
    private GridAdapter gridViewRecyclerViewAdapter;
    ProgressBar progressBar;

    public final static String ACTION_SEARCH_SPECIALITY_TITLE = "action_search_speciality_title";
    public final static String ACTION_SEARCH_SPECIALITY_ID = "action_search_speciality_id";
    public final static String ACTION_SEARCH_SPECIALITY_ARABIC_ID = "action_search_speciality_arabic_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);




        //Adding Data to a List

        dataModelArrayList = new ArrayList<>();

        gridRecycler_ID = findViewById(R.id.gridRecycler_ID);
        progressBar = (ProgressBar) findViewById(R.id.progressBarSp);

        loadSelectedList();




    }
    private void loadSelectedList() {
        //getting the progressbar


        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ApiUtils.BASE_URL1, null, new Response.Listener<JSONArray>() {
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
                        dataModelArrayList.add(dataModelClass);

//                        Log.d("Response", image1);
//                        Log.d("Response", titlem);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                /// Thriller View
                gridRecycler_ID.setLayoutManager(new GridLayoutManager(GridActivity.this, 3));
                gridRecycler_ID.setFocusable(false);
                gridRecycler_ID.setHasFixedSize(true);

                gridViewRecyclerViewAdapter = new GridAdapter(GridActivity.this, dataModelArrayList);
                gridRecycler_ID.setAdapter(gridViewRecyclerViewAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the array request to request queue
        requestQueue.add(jsonArrayRequest);
    }

}