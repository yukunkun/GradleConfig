package com.yk.gradleconfig.activity;

import android.content.Context;

import com.yk.gradleconfig.base.BasePresenter;
import com.yk.gradleconfig.base.BaseView;
import com.yk.gradleconfig.beans.Weather;

/**
 * author: kun .
 * date:   On 2019/3/26
 */
public interface RxNetControler {

    interface View extends BaseView{

        void getWeather(Weather weather);

        void getWeatherRx(Weather weather);
    }

    abstract class Presenter extends BasePresenter<RxNetControler.View> {

        public Presenter(Context context, RxNetControler.View view) {
            super(context, view);
        }

        public abstract void getWeather(String city);

    }

}
