package com.lzy.mywheelstwo.librarytest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.mylibrary.view.circleIndicator.CircleIndicator;
import com.lzy.mywheelstwo.R;

import java.util.Random;

/**
 * Created by 刘振远 on 2017/11/27.
 */

public class IndicatorTest  extends AppCompatActivity{
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_indicator);
        viewPager = findViewById(R.id.vp_test);
        circleIndicator = findViewById(R.id.circle);
        MypageAdapter adapter = new MypageAdapter();
        viewPager.setAdapter(adapter);
        circleIndicator.setViewPager(viewPager);
    }

    class MypageAdapter extends PagerAdapter {
        private Random random =new Random();
        private int size = 1;

        public MypageAdapter() {
            this.size = 5;
        }

        public MypageAdapter(int size) {
            this.size = size;
        }

        @Override
        public int getCount() {
            return size;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText(String.valueOf(position));

            textView.setBackgroundColor(0xff000000 | random.nextInt(0x00ffffff));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(48);
            textView.setTextColor(Color.WHITE);
            container.addView(textView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            return textView;


        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

            container.removeView((View) object);
        }
    }
}
