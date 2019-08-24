package com.yk.gradleconfig.activity;

import android.content.Context;

import com.yk.gradleconfig.beans.Weather;
import com.yk.gradleconfig.netword.BaseCallBack;
import com.yk.gradleconfig.netword.BaseObserver;
import com.yk.gradleconfig.netword.Response1;
import com.yk.gradleconfig.netword.RetrofitManager;
import com.yk.gradleconfig.utils.log.LogUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class RxNetPresenter extends RxNetControler.Presenter {

    public RxNetPresenter(Context context, RxNetControler.View view) {
        super(context, view);
    }

    @Override
    public void getWeather(String city) {
        final Call<Response1<Weather>> weather = RetrofitManager.getService().getWeather(city);
        weather.enqueue(new BaseCallBack<Weather>() {
            @Override
            public void onGood(Weather data) {
                mView.getWeather(data);
            }

            @Override
            public void onError(String data, int code) {
                LogUtil.i("error:",data+" code:"+code);
            }
        });

        RetrofitManager.getService()
                .getWeatherRx(city)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<Response1<Weather>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response1<Weather> weatherResponse1) {
                        mView.getWeatherRx(weatherResponse1.getData());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
