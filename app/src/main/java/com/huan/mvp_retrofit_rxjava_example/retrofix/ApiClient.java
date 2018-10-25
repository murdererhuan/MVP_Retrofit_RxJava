package com.huan.mvp_retrofit_rxjava_example.retrofix;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit mRetrofix;

    public static Retrofit retrofit(){

        if (mRetrofix==null){
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            OkHttpClient okHttpClient=builder.build();
            mRetrofix=new Retrofit.Builder()
                    .baseUrl(ApiService.API_SERVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofix;
    }
}
