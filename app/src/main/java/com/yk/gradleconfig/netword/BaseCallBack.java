package com.yk.gradleconfig.netword;

import com.yk.gradleconfig.utils.log.LogUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class  BaseCallBack<T> implements Callback<Response1<T>> {

    @Override
    public void onResponse(Call<Response1<T>> call, Response<Response1<T>> response) {
        if(response.isSuccessful()){
            Response1 data = response.body();
               if(null !=data){
                    if(data.getCode()==200){
                        onGood((T)data.getData());
                    }else {
                        onError(data.msg, data.code);
                    }
                }
            }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        LogUtil.i("网络失败：",t.toString());
    }

    public abstract void onGood(T data);

    public abstract void onError(String data,int code);

}
