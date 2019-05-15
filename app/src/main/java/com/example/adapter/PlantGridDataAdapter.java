package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.jsondatafromfile.R;
import com.example.model.Plant;

import java.util.List;

import timber.log.Timber;

public class PlantGridDataAdapter extends RecyclerView.Adapter<PlantGridDataAdapter.MyViewHolder> {
    private static final String TAG = "PlantGridDataAdapter";
    private Context mContext;
    private List<Plant> plants;
    private Plant plant;
    private LinearLayout llList;

    public PlantGridDataAdapter(Context mContext, List<Plant> plants, LinearLayout llList) {
        this.mContext = mContext;
        this.plants = plants;
        this.llList = llList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private static final String TAG = "PlantGridDataAdapter";
        AppCompatTextView tvGrowZoneNumber, tvWateringInterval, tvPlantName, tvPlantDesc;
        AppCompatImageView ivPlantImage;

        MyViewHolder(View view) {
            super(view);
            tvGrowZoneNumber = view.findViewById(R.id.tv_grow_zone_number);
            tvWateringInterval = view.findViewById(R.id.tv_watering_interval);
            tvPlantName = view.findViewById(R.id.tv_plant_name);
            tvPlantDesc = view.findViewById(R.id.tv_plant_desc);
            ivPlantImage = view.findViewById(R.id.iv_plant_image);
        }

    }

    @NonNull
    @Override
    public PlantGridDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_plant_grid_details, parent, false);

        return new PlantGridDataAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantGridDataAdapter.MyViewHolder holder, int position) {
        plant = plants.get(position);
        holder.tvPlantDesc.setEllipsize(TextUtils.TruncateAt.END);
        holder.tvPlantDesc.setText(plant.getDescription());
        holder.tvPlantName.setText(plant.getName());
        holder.tvWateringInterval.setText(String.valueOf(plant.getWateringInterval()));
        holder.tvGrowZoneNumber.setText(String.valueOf(plant.getGrowZoneNumber()));
        Timber.tag(TAG).e(plant.getImageUrl());
        Glide.with(mContext).load(plant.getImageUrl()).into(holder.ivPlantImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    plant = plants.get(position);
                    Snackbar.make(llList, TextUtils.concat(plant.getPlantId(), " ", plant.getName()), Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }
}