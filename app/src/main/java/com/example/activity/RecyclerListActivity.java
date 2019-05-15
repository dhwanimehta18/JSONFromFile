package com.example.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.ReadJsonData;
import com.example.adapter.PlantListDataAdapter;
import com.example.jsondatafromfile.R;
import com.example.model.Plant;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerListActivity extends AppCompatActivity {

    ReadJsonData jsonData;

    private static final String TAG = "RecyclerListActivity";

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.ll_list)
    LinearLayout llList;

    private List<Plant> plants = new ArrayList<>();
    InputStream is;
    PlantListDataAdapter plantListDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_list);
        ButterKnife.bind(this);

        swipeToRefresh.setColorSchemeResources(R.color.colorAccent);

        try {
            is = getAssets().open("plants.json");
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonData = new ReadJsonData(plants, is);
        jsonData.readJSONData();

        setRecyclerViewAdapter();

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToRefresh.setRefreshing(true);
                shuffle();
            }
        });
    }

    //set adapter
    private void setRecyclerViewAdapter() {
        plantListDataAdapter = new PlantListDataAdapter(this, plants, llList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvList.setLayoutManager(mLayoutManager);
        rvList.setAdapter(plantListDataAdapter);
    }

    private void shuffle() {
        Collections.shuffle(plants, new Random(System.currentTimeMillis()));
        swipeToRefresh.setRefreshing(false);
        setRecyclerViewAdapter();
    }
}