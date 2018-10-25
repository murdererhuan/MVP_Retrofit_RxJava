package com.huan.mvp_retrofit_rxjava_example.retrofix;

import com.huan.mvp_retrofit_rxjava_example.weather_mvp.model.WeatherModel;



import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {


    //baseurl
    String API_SERVER_URL = "http://apistore.baidu.com/microservice/";


    //加载天气
    @GET("weather")
    Observable<WeatherModel> loadDataByRetrofitRxjava(@Query("citypinyin") String cityId);
}
