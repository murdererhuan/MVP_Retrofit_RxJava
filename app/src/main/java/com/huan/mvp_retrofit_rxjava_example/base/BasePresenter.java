package com.huan.mvp_retrofit_rxjava_example.base;

import com.huan.mvp_retrofit_rxjava_example.retrofix.ApiClient;
import com.huan.mvp_retrofit_rxjava_example.retrofix.ApiService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class BasePresenter<V> {
    public V mvpView;

    protected ApiService apiService;

    private CompositeSubscription compositeSubscription;


    public void attachView(V mvpView) {
        this.mvpView = mvpView;
        apiService = ApiClient.retrofit().create(ApiService.class);
    }


    public void detachView() {
        this.mvpView = null;
        onUnsubscribe();
    }


    //Rxjava取消注册,以避免内存泄漏
    private void onUnsubscribe() {
        if(compositeSubscription!=null&&compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();

        }
    }



    public <T> void addSubscription(Observable<T> observable, Subscriber<T> subscriber){
        if(compositeSubscription==null){
            compositeSubscription=new CompositeSubscription();
        }

        compositeSubscription.add(observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber));
    }

}

