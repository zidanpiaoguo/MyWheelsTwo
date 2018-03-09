package com.lzy.mylibrary.utils.bigimage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Gravity;

import com.bumptech.glide.request.RequestOptions;
import com.lzy.mylibrary.R;

import java.util.ArrayList;

/**
 *
 * Created by 刘振远 on 2017/11/25.
 */

//用于数据的传输，进行了简单的封装

public class ImageViewer {


    private static final String TAG = "ImageViewer";

    //点击图片的相对位置
    private ViewLocation mViewLocation;

    private ArrayList<Object> mImageUrl;

    private int mBeginIndex;

    //（n/m）字体的位置
    private int mIndexPos;

    private boolean isShowProgress;

    //可以自己重新绘画,直接加载自定义progress
    private static Drawable mProgressDrawable;

    //Gilde 使用的加载失败，
    private static RequestOptions mOptions;

    public ImageViewer() {

        this.mBeginIndex = 0;
        this.mIndexPos = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        this.isShowProgress = true;
        this.mOptions = new RequestOptions()
                .placeholder(R.mipmap.img_viewer_placeholder)
                .error(R.mipmap.img_viewer_error);
        this.mProgressDrawable =null;
    }

    public static ImageViewer getInstance(){
        return new ImageViewer();
    }



    public void show(Context context) {
        if (mViewLocation == null || mImageUrl.size() == 0) {
            Log.w(TAG, "mViewLocation or mImageUrl is null or length 0");
            return;
        }
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        intent.putExtra(ImageDefine.BEGIN_INDEX, mBeginIndex);
        intent.putExtra(ImageDefine.VIEW_LOCATION, mViewLocation);

        intent.putExtra(ImageDefine.IMAGE_ARRAY, mImageUrl);
        intent.putExtra(ImageDefine.INDEX_GRAVITY, mIndexPos);
        intent.putExtra(ImageDefine.SHOW_PROGRESS, isShowProgress);
        context.startActivity(intent);
    }

    public ImageViewer beginIndex(@NonNull int index) {
        this.mBeginIndex = index;
        return this;
    }
    public ImageViewer viewData(@NonNull ViewLocation viewLocation) {
        this.mViewLocation = viewLocation;
        return this;
    }

    public ImageViewer imageData(@NonNull ArrayList<Object> imageData) {
        this.mImageUrl = imageData;
        return this;
    }

    public ImageViewer indexPos(int pos) {
        this.mIndexPos = pos;
        return this;
    }




    public static RequestOptions getOptions() {
        return mOptions;
    }

    public static Drawable getProgressDrawable() {
        return mProgressDrawable;
    }
}
