package com.huan.mvp_retrofit_rxjava_example.weather_mvp.view;

import com.huan.mvp_retrofit_rxjava_example.weather_mvp.model.WeatherModel;

public interface WeatherView {
    void getWeatherSuccess(WeatherModel weatherModel);
}
