package com.example.model;

public class FilterModel {
    private String brandName;
    private int brandRate;
    private boolean isSelected;
    private int selectedPosition = -1;

    public FilterModel(String brandName, int brandRate, boolean isSelected) {
        this.brandName = brandName;
        this.brandRate = brandRate;
        this.isSelected = isSelected;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getBrandRate() {
        return brandRate;
    }

    public void setBrandRate(int brandRate) {
        this.brandRate = brandRate;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
