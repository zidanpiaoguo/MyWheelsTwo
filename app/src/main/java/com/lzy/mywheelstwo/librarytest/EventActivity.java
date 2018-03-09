package com.lzy.mywheelstwo.librarytest;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.mywheelstwo.R;

public class EventActivity extends AppCompatActivity {

    private static final String TAG = "EventActivity";
    private LinearLayout llEvent;
    private TextView tvEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        initView();
    }




    private void initView() {
        llEvent = (LinearLayout) findViewById(R.id.ll_event);
        tvEvent = (TextView) findViewById(R.id.tv_event);



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {



        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN :
                for (int i = 0; i < event.getPointerCount(); i++) {
                    Log.d(TAG, "onTouchEvent: "+event.getPointerId(i));

                }

        }

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 手指按下
                break;
            case MotionEvent.ACTION_MOVE:
                // 手指移动
                break;
            case MotionEvent.ACTION_UP:
                // 手指抬起
                break;
            case MotionEvent.ACTION_CANCEL:
                // 事件被拦截
                break;
            case MotionEvent.ACTION_OUTSIDE:
                // 超出区域
                break;
        }
        return super.onTouchEvent(event);

    }
}
