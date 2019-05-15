package com.example.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.adapter.FilterRecyclerViewAdapter;
import com.example.jsondatafromfile.R;
import com.example.model.FilterModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckBoxHandlingActivity extends AppCompatActivity {

    @BindView(R.id.rv_checkbox_list)
    RecyclerView rvCheckboxList;
    @BindView(R.id.ll_checkbox_handling)
    LinearLayout llCheckboxHandling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_handling);
        ButterKnife.bind(this);

        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(this);
        rvCheckboxList.setLayoutManager(recyclerLayoutManager);

        //RecyclerView adapter
        FilterRecyclerViewAdapter recyclerViewAdapter = new
                FilterRecyclerViewAdapter(getBrands(), this);
        rvCheckboxList.setAdapter(recyclerViewAdapter);
    }

    private List<FilterModel> getBrands() {
        List<FilterModel> modelList = new ArrayList<>();
        modelList.add(new FilterModel("Adidas", 1323, false));
        modelList.add(new FilterModel("Nike", 2321, false));
        modelList.add(new FilterModel("Reebok", 3221, false));
        modelList.add(new FilterModel("Boss", 1323, false));
        modelList.add(new FilterModel("Wrangler", 5651, false));
        modelList.add(new FilterModel("Lee", 1898, false));
        modelList.add(new FilterModel("Levis", 1655, false));
        modelList.add(new FilterModel("Polo", 8881, false));
        modelList.add(new FilterModel("Tommy Hil", 167, false));
        modelList.add(new FilterModel("Nautica", 177, false));
        modelList.add(new FilterModel("Gas", 14, false));
        modelList.add(new FilterModel("Diesel", 1555, false));
        modelList.add(new FilterModel("Gap", 551, false));
        modelList.add(new FilterModel("Flying Machine", 199, false));
        modelList.add(new FilterModel("Pepe Jeans", 981, false));
        modelList.add(new FilterModel("Jack Jones", 561, false));
        modelList.add(new FilterModel("Puma", 1832, false));

        return modelList;
    }
}