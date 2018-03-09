package com.lzy.mylibrary.utils.banner;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by leo on 16/5/7.
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {

    private static final float MAX_SCALE = 1.0f;
    private static final float MIN_SCALE = 0.85f;

    @Override
    public void transformPage(View view, float position) {
        if (position < -1) {
            view.setAlpha(0);
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);


        } else if (position <= 1) {
            ////a页滑动至b页 ； a页从 0.0 -1 ；b页从1 ~ 0.0  [-1,1]


            float scaleFactor = MIN_SCALE + (1 - Math.abs(position)) * (MAX_SCALE - MIN_SCALE);





            //每次滑动后进行微小的移动目的是为了防止在三星的某些手机上出现两边的页面为显示的情况
            if (position > 0) {
                view.setTranslationX(-scaleFactor * 2);
            } else  {
                view.setTranslationX(scaleFactor * 2);
            }

            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);

            view.setAlpha(MIN_SCALE +
                    (scaleFactor - MIN_SCALE) /
                            (1 - MIN_SCALE) * (1 - MIN_SCALE));
        } else { // (1,+Infinity]
            view.setAlpha(0);
            view.setScaleX(MIN_SCALE);
            view.setScaleY(MIN_SCALE);
        }
    }
}
