package com.example.model;

import java.util.List;

public class SectionDataModel {
    private String header;
    private List<Plant> plantList;

    public SectionDataModel(String header, List<Plant> plantList) {
        this.header = header;
        this.plantList = plantList;
    }

    public SectionDataModel() {

    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public List<Plant> getPlantList() {
        return plantList;
    }

    public void setPlantList(List<Plant> plantList) {
        this.plantList = plantList;
    }
}
