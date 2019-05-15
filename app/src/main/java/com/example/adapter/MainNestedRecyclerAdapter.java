package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jsondatafromfile.R;
import com.example.model.Plant;
import com.example.model.SectionDataModel;

import java.util.List;

public class MainNestedRecyclerAdapter extends RecyclerView.Adapter<MainNestedRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private List<SectionDataModel> sectionDataModelList;
    private LinearLayout llNestedMain;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public MainNestedRecyclerAdapter(Context mContext, List<SectionDataModel> sectionDataModelList, LinearLayout llNestedMain) {
        this.mContext = mContext;
        this.sectionDataModelList = sectionDataModelList;
        this.llNestedMain = llNestedMain;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvSectionName;
        RecyclerView rvNestedSub;
        AppCompatButton btnMore;

        MyViewHolder(View view) {
            super(view);
            tvSectionName = view.findViewById(R.id.tv_section_name);
            rvNestedSub = view.findViewById(R.id.rv_nested_sub);
            btnMore = view.findViewById(R.id.btn_more);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_nested_main, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String sectionName = sectionDataModelList.get(position).getHeader();
        List<Plant> dataList = sectionDataModelList.get(position).getPlantList();
        holder.tvSectionName.setText(sectionName);
        PlantGridDataAdapter gridDataAdapter = new PlantGridDataAdapter(mContext, dataList, llNestedMain);
        holder.rvNestedSub.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.rvNestedSub.setAdapter(gridDataAdapter);
        holder.rvNestedSub.setRecycledViewPool(recycledViewPool);

        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, sectionDataModelList.get(position).getHeader(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return sectionDataModelList.size();
    }
}