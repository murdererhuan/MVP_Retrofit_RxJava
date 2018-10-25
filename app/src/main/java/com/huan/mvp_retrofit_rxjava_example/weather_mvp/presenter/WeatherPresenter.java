package com.huan.mvp_retrofit_rxjava_example.weather_mvp.presenter;

import com.huan.mvp_retrofit_rxjava_example.base.BasePresenter;
import com.huan.mvp_retrofit_rxjava_example.weather_mvp.model.WeatherModel;
import com.huan.mvp_retrofit_rxjava_example.weather_mvp.view.WeatherView;

import rx.Subscriber;

public class WeatherPresenter extends BasePresenter<WeatherView> {


    public WeatherPresenter(WeatherView view) {
        attachView(view);
    }


    public void loadDataByRetrofixjava1111(String cityId){
        addSubscription(apiService.loadDataByRetrofitRxjava(cityId), new Subscriber<WeatherModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(WeatherModel weatherModel) {
                mvpView.getWeatherSuccess(weatherModel);
            }
        });
    }
}
