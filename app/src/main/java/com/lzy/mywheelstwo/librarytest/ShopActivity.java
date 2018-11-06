package com.lzy.mywheelstwo.librarytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.florent37.expectanim.ExpectAnim;
import com.lzy.mywheelstwo.R;

import static com.github.florent37.expectanim.core.Expectations.alignLeft;
import static com.github.florent37.expectanim.core.Expectations.alignTop;
import static com.github.florent37.expectanim.core.Expectations.belowOf;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;

/**
 * Created by bullet on 2018\5\4 0004.
 */

public class ShopActivity extends Activity {


    private ImageView ivImage;
    private TextView tvPrice;
    private TextView tvNumber;
    private TextView tvDetail;

    private ExpectAnim exception;
    private NestedScrollView scrollView;
    private View background;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        initView();

        exception = new ExpectAnim()
                .expect(ivImage)
                .toBe(
                        topOfParent().withMarginDp(20),
                        leftOfParent().withMarginDp(20),
                        scale(0.5f, 0.5f)
                )
                .expect(tvPrice)
                .toBe(
                        alignTop(ivImage).withMarginDp(16),
                        toRightOf(ivImage)
                )
                .expect(tvNumber)
                .toBe(
                        belowOf(tvPrice),
                        alignLeft(tvPrice)
                )
                .expect(tvDetail)
                .toBe(
                        belowOf(tvNumber),
                        alignLeft(tvNumber)
                )
                .expect(background)
                .toBe(
                        height((int) getResources().getDimension(R.dimen.height)).withGravity(Gravity.LEFT, Gravity.TOP)
                )
                .toAnimation()
                .setDuration(1500);


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
//                exception.setPercent(percent);
                if(scrollX==0){
                    exception.reset();
                }

            }
        });

    }

    private void initView() {
        ivImage = (ImageView) findViewById(R.id.iv_image);
        tvPrice = (TextView) findViewById(R.id.tv_price);
        tvNumber = (TextView) findViewById(R.id.tv_number);
        tvDetail = (TextView) findViewById(R.id.tv_detail);
        scrollView = (NestedScrollView) findViewById(R.id.scrollView);
        background = (View) findViewById(R.id.background);
    }
}
