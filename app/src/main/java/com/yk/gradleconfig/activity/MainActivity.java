package com.yk.gradleconfig.activity;

import android.view.View;
import android.widget.Button;

import com.yk.gradleconfig.R;
import com.yk.gradleconfig.base.BaseActivity;
import com.yk.gradleconfig.base.BasePresenter;
import com.yk.gradleconfig.base.ChatControler;

public class MainActivity extends BaseActivity implements ChatControler.View, View.OnClickListener {

    private Button mButton1;
    private Button mButton2;
    private Button mButton3;

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
        mButton1 = (Button) findViewById(R.id.bt_1);
        mButton2 = (Button) findViewById(R.id.bt_2);
        mButton3 = (Button) findViewById(R.id.bt_3);
    }

    @Override
    public void initListener() {
        mButton1.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
    }

    @Override
    public void initDate() {

    }

    @Override
    public void getMessage(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_1:
                RxNetActivity.start(this);
                break;
            case R.id.bt_2:
                break;
            case R.id.bt_3:
                break;
        }
    }
}
