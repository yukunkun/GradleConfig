package com.yk.gradleconfig.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yk.gradleconfig.utils.ActivityManager;

/**
 * author: kun .
 * date:   On 2019/3/26
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    public T mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initBaseData();
        mPresenter=createPresenter();
        initView();
        initDate();
        initListener();
    }

    protected abstract T createPresenter();

    public abstract int getLayout();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initDate();

    private  void initBaseData(){
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.mPresenter != null) {
            this.mPresenter=null;
        }
    }
}
