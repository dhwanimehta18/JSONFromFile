package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatCheckBox;
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

public class PlantListDataAdapter extends RecyclerView.Adapter<PlantListDataAdapter.MyViewHolder> {
    private static final String TAG = "PlantListDataAdapter";
    private Context mContext;
    private List<Plant> plants;
    private Plant plant;
    private LinearLayout llList;

    public PlantListDataAdapter(Context mContext, List<Plant> plants, LinearLayout llList) {
        this.mContext = mContext;
        this.plants = plants;
        this.llList = llList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvGrowZoneNumber, tvWateringInterval, tvPlantName, tvPlantDesc;
        AppCompatImageView ivPlantImage;
        AppCompatCheckBox cbSelection;

        MyViewHolder(View view) {
            super(view);
            tvGrowZoneNumber = view.findViewById(R.id.tv_grow_zone_number);
            tvWateringInterval = view.findViewById(R.id.tv_watering_interval);
            tvPlantName = view.findViewById(R.id.tv_plant_name);
            tvPlantDesc = view.findViewById(R.id.tv_plant_desc);
            ivPlantImage = view.findViewById(R.id.iv_plant_image);
            cbSelection = view.findViewById(R.id.cb_selection);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_plant_list_detail, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        plant = plants.get(position);
        holder.tvPlantDesc.setEllipsize(TextUtils.TruncateAt.END);
        holder.tvPlantDesc.setText(plant.getDescription());
        holder.tvPlantName.setText(plant.getName());
        holder.tvWateringInterval.setText(String.valueOf(plant.getWateringInterval()));
        holder.tvGrowZoneNumber.setText(String.valueOf(plant.getGrowZoneNumber()));
        Timber.tag(TAG).e(plant.getImageUrl());
        Glide.with(mContext).load(plant.getImageUrl()).into(holder.ivPlantImage);

        holder.cbSelection.setChecked(plant.isSelected());

        holder.cbSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                if (plants.get(pos).isSelected()) {
                    holder.cbSelection.setChecked(false);
                    plants.get(pos).setSelected(false);
                } else {
                    holder.cbSelection.setChecked(true);
                    plants.get(pos).setSelected(true);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                plant = plants.get(position);
                holder.cbSelection.setTag(pos);

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