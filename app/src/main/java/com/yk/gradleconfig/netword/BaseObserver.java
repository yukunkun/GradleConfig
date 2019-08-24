package com.yk.gradleconfig.netword;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class  BaseObserver<T> implements Observer<T> {
    @Override
    public void onComplete(){

    };

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }
}
