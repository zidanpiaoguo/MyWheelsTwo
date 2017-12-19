package com.lzy.mylibrary.view.loading.loading2;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import com.lzy.mylibrary.R;


/**
 *
 * Created by bullet on 2017/11/14.
 */
public class LoadingDialog2 {

    private static CustomDialog mDialog;

    public LoadingDialog2() {

    }


    public static void show(Activity activity){
        View contentView= LayoutInflater.from(activity).inflate(R.layout.view_loading_two,null);
        mDialog=new CustomDialog(activity)
                .setContentView(contentView, Gravity.CENTER)
                .setCancelable(true)
                .setCanceledOnTouchOutside(false);

        if (!mDialog.isShowing()){
            mDialog.show();
        }
    }

    public void dismiss(){
        if(mDialog!=null){
            if (mDialog.isShowing()){
                mDialog.dismiss();
            }
        }

    }
}
