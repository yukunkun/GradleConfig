package com.yk.gradleconfig;

import com.yk.gradleconfig.base.BaseActivity;
import com.yk.gradleconfig.base.BasePresenter;
import com.yk.gradleconfig.base.ChatControler;

public class MainActivity extends BaseActivity implements ChatControler.View {

    @Override
    protected BasePresenter createPresenter() {
        return new MainPresenter(this,this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void getMessage(String msg) {

    }
}
