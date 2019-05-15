package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jsondatafromfile.R;
import com.example.model.FilterModel;

import java.util.List;

public class FilterRecyclerViewAdapter extends RecyclerView.Adapter<FilterRecyclerViewAdapter.MyViewHolder> {

    private List<FilterModel> filterModelList;
    private Context mContext;
    private FilterModel filterModel;
    private int selectedPosition = -1;

    public FilterRecyclerViewAdapter(List<FilterModel> filterModelList, Context mContext) {
        this.filterModelList = filterModelList;
        this.mContext = mContext;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvBrandName;
        AppCompatTextView tvBrandRate;
        AppCompatCheckBox cbSelection;

        MyViewHolder(View view) {
            super(view);
            tvBrandName = view.findViewById(R.id.tv_brand_name);
            tvBrandRate = view.findViewById(R.id.tv_brand_rate);
            cbSelection = view.findViewById(R.id.cb_selection);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_checkbox_handling, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        filterModel = filterModelList.get(position);
        holder.tvBrandName.setText(filterModel.getBrandName());
        holder.tvBrandRate.setText(String.valueOf(filterModel.getBrandRate()));
        holder.cbSelection.setChecked(filterModel.isSelected());
        //holder.cbSelection.setChecked(selectedPosition == position);

        if (selectedPosition == position) {
            holder.cbSelection.setChecked(true);
            filterModel.setSelected(true);
        } else {
            filterModel.setSelected(false);
            holder.cbSelection.setChecked(false);
        }

        holder.cbSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                int arrayLength = filterModelList.size();

                selectedPosition = holder.getAdapterPosition();
                //filterModel.setSelectedPosition(holder.getAdapterPosition());

                /*for (int i = 0; i < arrayLength; i++) {
                    //   holder.cbSelection.setChecked(false);
                    filterModelList.get(i).setSelected(false);
                }*/

                //filterModelList.get(pos).setSelected(true);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }
}
