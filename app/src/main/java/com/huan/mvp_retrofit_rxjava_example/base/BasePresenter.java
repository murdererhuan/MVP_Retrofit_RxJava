package com.huan.mvp_retrofit_rxjava_example.base;

import com.huan.mvp_retrofit_rxjava_example.retrofix.ApiService;

public class BasePresenter<V> {
    public  V mvpView;

    protected ApiService apiService;
}
