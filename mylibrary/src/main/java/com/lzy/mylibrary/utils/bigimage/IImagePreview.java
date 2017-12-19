package com.lzy.mylibrary.utils.bigimage;


import android.content.Intent;

/**
 * Created by 刘振远 on 2017/11/25.
 */

public interface IImagePreview {


    void initUI();

    void addListener();

    void handleIntent(Intent intent);

    /**
     * The image is animated after the click
     */
    void fullScreen();

    /**
     * Animation of the image when exiting
     */
    void restoreImage();

    void clear();
}
