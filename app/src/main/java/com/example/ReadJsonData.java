package com.example;

import com.example.model.Plant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import timber.log.Timber;

public class ReadJsonData {
    private static final String TAG = "ReadJsonData";
    private List<Plant> plants;
    private InputStream is;

    public ReadJsonData(List<Plant> plants, InputStream is) {
        this.plants = plants;
        this.is = is;
    }

    public void readJSONData() {
        Timber.tag(TAG).e("readJSON");
        try {
            Timber.tag(TAG).e("readJSON try");
            JSONArray jsonArray = new JSONArray(loadJSONFromAsset());

            Timber.tag(TAG).e("readJSON before for");
            int arrayLength = jsonArray.length();
            for (int i = 0; i < arrayLength; i++) {
                Timber.tag(TAG).e("readJSON for");
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String plantId = jsonObject.getString("plantId");
                String name = jsonObject.getString("name");
                String description = jsonObject.getString("description");
                int growZoneNumber = Integer.parseInt(jsonObject.getString("growZoneNumber"));
                int wateringInterval = Integer.parseInt(jsonObject.getString("wateringInterval"));
                String imageUrl = jsonObject.getString("imageUrl");
                Timber.tag(TAG).e(imageUrl);
                plants.add(new Plant(plantId, name, description, growZoneNumber, wateringInterval, imageUrl));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadJSONFromAsset() {
        Timber.tag(TAG).e("jsonReadFromAsset");
        String json = null;
        try {
            //InputStream is = getAssets().open("plants.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        Timber.tag(TAG).e("json : %s", json);
        return json;
    }
}