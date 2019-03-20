package com.example.appium_sample.utils;

import com.example.appium_sample.model.FruitModel;

import java.util.ArrayList;
import java.util.List;

public class DataIntent {
    private static volatile DataIntent intent = new DataIntent();
    private List<FruitModel> recentList = new ArrayList<>();

    public static DataIntent getInstance() {
        return intent;
    }

    public void addItemInCart(FruitModel item) {
        recentList.add(item);
    }

    public void removeItemCart(FruitModel item) {
        recentList.remove(item);
    }

    public List<FruitModel> getCartList() {
        return recentList;
    }
}
