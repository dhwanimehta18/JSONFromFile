package com.example.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.jsondatafromfile.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_recycler_list, R.id.btn_recycler_grid, R.id.btn_recycler_horizontal, R.id.btn_recycler_nested,R.id.btn_recycler_checkbox})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_recycler_list:
                startActivity(new Intent(this, RecyclerListActivity.class));
                break;
            case R.id.btn_recycler_grid:
                startActivity(new Intent(this, RecyclerGridActivity.class));
                break;
            case R.id.btn_recycler_horizontal:
                startActivity(new Intent(this, RecyclerHorizontalActivity.class));
                break;
            case R.id.btn_recycler_nested:
                startActivity(new Intent(this, NestedRecyclerActivity.class));
                break;
            case R.id.btn_recycler_checkbox:
                startActivity(new Intent(this, CheckBoxHandlingActivity.class));
                break;
            default:
                startActivity(new Intent(this, RecyclerListActivity.class));
        }
    }
}
