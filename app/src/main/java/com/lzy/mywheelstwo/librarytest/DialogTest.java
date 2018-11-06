package com.lzy.mywheelstwo.librarytest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lzy.mylibrary.view.dialog.MyDialog;
import com.lzy.mylibrary.view.dialog.MyDialogOnClick;
import com.lzy.mylibrary.view.loading.loading1.LoadingDialog;
import com.lzy.mylibrary.view.loading.loading2.LoadingDialog2;
import com.lzy.mylibrary.view.selectaddress.SelectAddressWindow;
import com.lzy.mywheelstwo.R;
import com.lzy.mywheelstwo.utils.MyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zidan on 2017/11/1.
 */

public class DialogTest extends Activity {
    private static final String TAG = "DialogTest";
    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.main_layout)
    LinearLayout mainLayout;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    private SelectAddressWindow memuWindow;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3,R.id.button4, R.id.button5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button1:
                oneDialog();
                break;
            case R.id.button2:
                twoDialog();
                break;
            case R.id.button3:
                memuwindow();
                break;
            case R.id.button4:
                LoadingDialog.showDialogForLoading(DialogTest.this);
                break;
            case R.id.button5:
                LoadingDialog2.show(DialogTest.this);
                break;
            default:
                break;

        }
    }

    private void memuwindow() {
        memuWindow = new SelectAddressWindow(DialogTest.this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tv_cancel:
                        memuWindow.dismiss();
                        break;
                    case R.id.tv_ok:
                        memuWindow.dismiss();
                        MyToast.makeText("当前选中:" + memuWindow.getmCurrentProviceName() + ","
                                + memuWindow.getmCurrentCityName() + ","
                                + memuWindow.getmCurrentDistrictName()
                        );
                        break;
                }
            }
        });
        memuWindow.showAtLocation(mainLayout, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void twoDialog() {
        new MyDialog(this, MyDialog.DIALOG_TWOOPTION, "你是一个bug", "", new MyDialogOnClick() {
            @Override
            public void sureOnClick(View v) {
                MyToast.makeText("确定");
            }

            @Override
            public void cancleOnClick(View v) {
                MyToast.makeText("取消");
            }
        }).show();
    }


    private void oneDialog(){
        Log.d(TAG, "onViewClicked: ");
        new MyDialog(this, MyDialog.DIALOG_ONEOPTION, "", "你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug你是一个bug", new MyDialogOnClick() {
            @Override
            public void sureOnClick(View v) {
                MyToast.makeText("确定");
            }

            @Override
            public void cancleOnClick(View v) {

            }
        }).show();
    }



}
