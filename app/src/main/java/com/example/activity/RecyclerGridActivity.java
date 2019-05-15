package com.example.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.ReadJsonData;
import com.example.adapter.PlantGridDataAdapter;
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

public class RecyclerGridActivity extends AppCompatActivity {

    @BindView(R.id.rv_grid)
    RecyclerView rvGrid;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.ll_grid)
    LinearLayout llGrid;

    PlantGridDataAdapter plantGridDataAdapter;
    private List<Plant> plants = new ArrayList<>();
    InputStream is;
    ReadJsonData jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);
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
        //Log.e(TAG, "Main");
        setRecyclerViewAdapter();

        swipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToRefresh.setRefreshing(true);
                shuffle();

            }
        });

    }

    private void setRecyclerViewAdapter() {
        plantGridDataAdapter = new PlantGridDataAdapter(this, plants, llGrid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        rvGrid.setLayoutManager(mLayoutManager);
        rvGrid.setAdapter(plantGridDataAdapter);
    }

    private void shuffle() {
        Collections.shuffle(plants, new Random(System.currentTimeMillis()));
        swipeToRefresh.setRefreshing(false);
        setRecyclerViewAdapter();
    }

}
