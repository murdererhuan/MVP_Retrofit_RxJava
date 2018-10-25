package com.huan.mvp_retrofit_rxjava_example.weather_mvp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.huan.mvp_retrofit_rxjava_example.R;
import com.huan.mvp_retrofit_rxjava_example.weather_mvp.model.WeatherModel;
import com.huan.mvp_retrofit_rxjava_example.weather_mvp.presenter.WeatherPresenter;

public class MainActivity extends AppCompatActivity implements WeatherView,View.OnClickListener {

    private Button btn;
    private TextView tv_show;
    private EditText edt;
    private WeatherPresenter weatherPresenter=new WeatherPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_show=(TextView)findViewById(R.id.tv_show);
        btn=(Button)findViewById(R.id.btn);
        edt=(EditText)findViewById(R.id.edt);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                weatherPresenter.loadDataByRetrofixjava1111(edt.getText().toString());
                break;
        }
    }

    @Override
    public void getWeatherSuccess(WeatherModel weatherModel) {
        tv_show.setText("  "+weatherModel.getRetData().getWeather()+"  "+weatherModel.getRetData().getWD());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(weatherPresenter!=null){
            weatherPresenter.detachView();
            Log.e("RXJAVA","毁灭");
        }
    }
}
