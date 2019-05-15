package com.example.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.ReadJsonData;
import com.example.adapter.MainNestedRecyclerAdapter;
import com.example.jsondatafromfile.R;
import com.example.model.Plant;
import com.example.model.SectionDataModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NestedRecyclerActivity extends AppCompatActivity {

    ReadJsonData jsonData;

    private static final String TAG = "NestedRecyclerActivity";
    @BindView(R.id.rv_nested_main)
    RecyclerView rvNestedMain;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeToRefresh;
    @BindView(R.id.ll_nested_main)
    LinearLayout llNestedMain;

    private List<Plant> plants = new ArrayList<>();
    InputStream is;
    MainNestedRecyclerAdapter mainNestedRecyclerAdapter;
    List<SectionDataModel> sectionDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_recycler_view);
        ButterKnife.bind(this);

        sectionDataModelList = new ArrayList<>();

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

        addSections();
    }

    private void addSections() {
        for (int i = 0; i < 4; i++) {
            SectionDataModel sectionDataModel = new SectionDataModel();
            sectionDataModel.setHeader(getString(R.string.section_header, i + 1));
            sectionDataModel.setPlantList(plants);
            sectionDataModelList.add(sectionDataModel);
        }
    }

    private void setRecyclerViewAdapter() {
        mainNestedRecyclerAdapter = new MainNestedRecyclerAdapter(this, sectionDataModelList, llNestedMain);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvNestedMain.setLayoutManager(mLayoutManager);
        rvNestedMain.setAdapter(mainNestedRecyclerAdapter);
    }

    private void shuffle() {
        Collections.shuffle(plants, new Random(System.currentTimeMillis()));
        swipeToRefresh.setRefreshing(false);
        setRecyclerViewAdapter();
    }
}