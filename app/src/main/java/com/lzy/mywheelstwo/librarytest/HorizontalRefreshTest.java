package com.lzy.mywheelstwo.librarytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.HorizontalScrollView;

import com.lzy.mylibrary.view.horizontalrefresh.RefreshCallBack;
import com.lzy.mywheelstwo.R;

/**
 * Created by bullet on 2018\7\16 0016.
 */

public class HorizontalRefreshTest extends AppCompatActivity  {


    private RecyclerView rcl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_refresh);

        initView();


    }

    private void initView() {

        rcl = (RecyclerView) findViewById(R.id.rcl);



    }


//    @Override
//    public void onLeftRefreshing() {
//        fresh.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                fresh.onRefreshComplete();
//            }
//        }, 2000);
//    }
//
//    @Override
//    public void onRightRefreshing() {
//        fresh.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mLayoutAdapter.getMore();
//                fresh.onRefreshComplete();
//            }
//        }, 2000);
//    }

}
