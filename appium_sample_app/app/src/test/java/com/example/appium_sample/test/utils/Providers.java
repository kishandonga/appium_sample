package com.example.appium_sample.test.utils;

import java.util.HashMap;
import java.util.Map;

import io.appium.java_client.android.AndroidDriver;

public class Providers {

    private volatile static Providers provider = new Providers();
    private Map<String, Object> values = new HashMap<>();
    private AndroidDriver driver;

    private Providers() {
    }

    public static Providers getInstance() {
        return provider;
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public void setDriver(AndroidDriver driver) {
        this.driver = driver;
    }

    public void put(String key, Object value) {
        values.put(key, value);
    }

    public Object get(String key) {
        return values.get(key);
    }
}
