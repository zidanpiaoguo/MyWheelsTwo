package com.lzy.mylibrary.utils.bigimage;


import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
/**
 * Created by 刘振远 on 2017/11/25.
 */

public class ImagePageAdapter extends PagerAdapter {
    private ArrayList<View> mViews;

    public ImagePageAdapter(ArrayList<View> views) {
        this.mViews = views;
    }

    @Override
    public int getCount() {
        return mViews != null ? mViews.size() : 0;
    }

    @Override
    public View instantiateItem(ViewGroup container, final int position) {
        if (mViews != null) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        } else {
            return null;
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
