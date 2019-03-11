package com.example.appium_sample.test;

import java.util.HashMap;
import java.util.Map;

public class DataProvider {

    private volatile static DataProvider provider = new DataProvider();
    private Map<String, Object> values = new HashMap<>();

    private DataProvider() {
    }

    public static DataProvider getInstance() {
        return provider;
    }

    public void put(String key, Object value) {
        values.put(key, value);
    }

    public Object get(String key) {
        return values.get(key);
    }
}
