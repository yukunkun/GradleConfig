package com.yk.gradleconfig.netword;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: kun .
 * date:   On 2019/8/22
 */
public class RetrofitManager {

    public static DateService mDateService;
    public static String baseUrl = "";

    public static DateService getService() {
        if (mDateService == null) {
            Retrofit build = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(initHttpClient())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mDateService = build.create(DateService.class);
        }
        return mDateService;
    }

    public static OkHttpClient initHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 打印请求log日志
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(@NonNull String message) {
                Log.i("OkHttp:", message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        builder.connectTimeout(10000, TimeUnit.SECONDS); // 设置连接超时
        builder.readTimeout(10000, TimeUnit.SECONDS);   // 设置读取超时
        builder.writeTimeout(10000, TimeUnit.SECONDS);  // 设置写入超时
        builder.retryOnConnectionFailure(true); // 设置重连
        return builder.build();
    }
}
