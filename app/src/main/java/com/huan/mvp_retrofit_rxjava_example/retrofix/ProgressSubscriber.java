package com.huan.mvp_retrofit_rxjava_example.retrofix;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {

    private SubscriberOnNextListener<T> mListener;
    private Context mContext;
    private ProgressDialogHandler mHandler;

    public ProgressSubscriber(SubscriberOnNextListener<T> mListener, Context mContext) {
        this.mListener = mListener;
        this.mContext = mContext;
        mHandler=new ProgressDialogHandler(mContext, true, this);
    }


    private void showProgressDialog(){
        if (mHandler!=null){
            mHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }

    }

    private void dismissProgressDialog(){
        if (mHandler!=null){
            mHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mHandler=null;
        }
    }

    /*
    * 订阅开始时调用
    * */
    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
    }

    @Override
    public void onCancelProgress() {
        if(!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Toast.makeText(DemoApplication.getAppContent(),"获取数据完成",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException){
            Toast.makeText(DemoApplication.getAppContent(),"网络中断,请检查您的网络状态",Toast.LENGTH_SHORT).show();
        }else if (e instanceof ConnectException){
            Toast.makeText(DemoApplication.getAppContent(),"网络中断,请检查您的网络状态",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(DemoApplication.getAppContent(),"error:"+e.getMessage(),Toast.LENGTH_SHORT).show();
            Log.e("错误","error:"+e.getMessage());

        }
        dismissProgressDialog();
    }

    @Override
    public void onNext(T t) {
        if(mListener!=null){
            mListener.onNext(t);
        }

    }
}
