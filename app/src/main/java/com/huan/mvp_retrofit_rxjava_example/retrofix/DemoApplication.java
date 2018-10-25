package com.huan.mvp_retrofit_rxjava_example.retrofix;

import android.app.Application;
import android.content.Context;

public class DemoApplication  extends Application{

    private static DemoApplication instance;

    public static Context getAppContent(){

        return instance==null?null:instance.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }
}
