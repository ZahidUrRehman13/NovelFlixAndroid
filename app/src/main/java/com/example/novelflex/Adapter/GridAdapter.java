package com.example.novelflex.Adapter;


import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_AUTHOR;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_DESCRIPTION;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_IMAGE;
import static com.example.novelflex.Fragments.HomeFragment.ACTION_FOR_TITLE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.novelflex.Activities.DetailActivity;
import com.example.novelflex.Models.DataModelClass;
import com.example.novelflex.R;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private Context context;
    private ArrayList<DataModelClass> dataModelClassesArrayList;

    public GridAdapter(Context context, ArrayList<DataModelClass> dataModelClassesArrayList) {
        this.context = context;
        this.dataModelClassesArrayList = dataModelClassesArrayList;
    }

    @NonNull
    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gridview__item_layout, parent, false);
        return new GridAdapter.ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull GridAdapter.ViewHolder holder, int position) {

        DataModelClass dataModelClass = dataModelClassesArrayList.get(position);
//        holder.doctorImage_ID.setImageResource(doctorsSpecialityModel.getImagePath());
        Glide.with(context).load(dataModelClass.getImage()).into(holder.Image_ID);

        holder.title_ID.setText(dataModelClass.getTitle());
        holder.Press_Layout_ID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(ACTION_FOR_IMAGE, dataModelClass.getImage());
                intent.putExtra(ACTION_FOR_TITLE, dataModelClass.getTitle());
                intent.putExtra(ACTION_FOR_DESCRIPTION, dataModelClass.getDescriptions());
                intent.putExtra(ACTION_FOR_AUTHOR, dataModelClass.getAuthor());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataModelClassesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context ctx;
        private ImageView Image_ID;
        private TextView title_ID;
        private RelativeLayout Press_Layout_ID;
        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            context = ctx;

            //Casting Widget's
            Image_ID = itemView.findViewById(R.id.image);
            title_ID = itemView.findViewById(R.id.title_text_ID);
            Press_Layout_ID = itemView.findViewById(R.id.press_R_layout);
        }
    }
}
