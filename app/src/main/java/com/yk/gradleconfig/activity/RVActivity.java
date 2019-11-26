package com.yk.gradleconfig.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.yk.gradleconfig.R;
import com.yk.gradleconfig.adapter.RVVerticalAdapter;
import com.yk.gradleconfig.utils.log.LogUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class RVActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwlayout;
    private LinearLayoutManager mLinearLayoutManager;
    private RVVerticalAdapter mRVVerticalAdapter;
    private List<String> mList=new ArrayList<>();
    private String url="https://cdn.mom1.cn/?mom=json";


    public static void start(Context context){
        Intent intent=new Intent(context,RVActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSwlayout = (SwipeRefreshLayout) findViewById(R.id.sw);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRecyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRVVerticalAdapter = new RVVerticalAdapter(this, mList);
        mRecyclerView.setAdapter(mRVVerticalAdapter);
        inData();
        initListener();
    }

    private void initListener() {
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                Log.i("=============First",mLinearLayoutManager.findFirstVisibleItemPosition()+"");
                Log.i("=============First",mLinearLayoutManager.findFirstCompletelyVisibleItemPosition()+"");
                Log.i("=============Last",mLinearLayoutManager.findLastVisibleItemPosition()+"");
                Log.i("=============Last",mLinearLayoutManager.findLastCompletelyVisibleItemPosition()+"");
                Log.i("=============Count",mLinearLayoutManager.getItemCount()+"");
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
                    if(lastVisibleItemPosition==mLinearLayoutManager.getItemCount()-1){
                        inData();
                    }
                }
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        mSwlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inData();
                mSwlayout.setRefreshing(false);
            }
        });
    }

    private void inData() {
        OkHttpUtils.get().url(url)
                .build().execute(new StringCallback() {
            @Override
            public void onResponse(String response, int id) {
                if(!TextUtils.isEmpty(response)){
                    int indexOf = response.indexOf("{");
                    if(indexOf!=-1){
                        String data=response.substring(indexOf,response.length());
                        try {
                            JSONObject jsonObject=new JSONObject(data);
                            if(jsonObject.optString("result").equals("200")){
                                String downloadUrl="http:"+jsonObject.optString("img");
                                mList.add(downloadUrl);
                                mRVVerticalAdapter.notifyData(mList);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            @Override
            public void onError(Call call, Exception e, int id) {

            }
        });
    }

}
