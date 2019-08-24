package com.yk.gradleconfig.netword;

import com.yk.gradleconfig.beans.Weather;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author: kun .
 * date:   On 2019/8/22
 */
public interface DateService {

    @GET("/weatherApi")
    Call<Response1<Weather>> getWeather(@Query("city") String city);

    @GET("/weatherApi")
    Observable<Response1<Weather>> getWeatherRx(@Query("city") String city);
}
