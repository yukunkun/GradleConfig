package com.yk.gradleconfig.activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yk.gradleconfig.R;
import com.yk.gradleconfig.base.BaseActivity;
import com.yk.gradleconfig.base.BasePresenter;
import com.yk.gradleconfig.beans.Weather;
import com.yk.gradleconfig.utils.log.LogUtil;

public class RxNetActivity extends BaseActivity implements RxNetControler.View {

    private TextView mTextView;
    private TextView mTvRx;
    private RxNetPresenter mRxNetPresenter;
    private Button mButton;

    public static void start(Context context){
        Intent intent=new Intent(context,RxNetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected BasePresenter createPresenter() {
        return new RxNetPresenter(this,this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_rx_net;
    }

    @Override
    public void initView() {
        mRxNetPresenter= (RxNetPresenter) mPresenter;
        mTextView = (TextView) findViewById(R.id.tv);
        mTvRx = (TextView) findViewById(R.id.tv_rx);
        mButton = (Button) findViewById(R.id.bt_start);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initDate() {

    }

    @Override
    public void getWeather(Weather weather) {
        LogUtil.i(weather.toString());
        mTextView.setText("retroft:"+weather.toString());
    }

    @Override
    public void getWeatherRx(Weather weather) {
        mTvRx.setText("rx："+weather.toString()+"");
        LogUtil.i(weather.toString());
    }

    public void start(View view) {
        mRxNetPresenter.getWeather("成都");
    }
}
