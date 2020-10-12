package com.example.basemvvm.network.model;

/**
 * author: 王统根
 * time: 2020/8/6
 * desc:
 */
public class WeatherModel {
    private static WeatherModel mInstance;

    private WeatherModel() {
    }

    public static WeatherModel getInstance() {
        if (mInstance == null) {
            synchronized (WeatherModel.class) {
                if (mInstance == null) {
                    mInstance = new WeatherModel();
                }
            }
        }
        return mInstance;
    }
}
