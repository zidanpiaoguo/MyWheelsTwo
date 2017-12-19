package com.lzy.mywheelstwo.librarytest;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.lzy.mylibrary.view.addpicture.PicturesPreviewer;
import com.lzy.mywheelstwo.R;
import com.lzy.mywheelstwo.utils.MiPictureHelper;

import java.io.File;

import static android.content.ContentValues.TAG;

/**
 * Created by zidan on 2017/11/3.
 */

public class PictureTest extends Activity{
    private PicturesPreviewer mPreviewer;
    private String tempCameraFilePath;

    private final int REQUEST_CODE_CAMERA = 1;
    private final int REQUEST_CODE_ALBUM = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_test);



        //6.0后动态申请权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!(checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED)) {
                shouldShowRequestPermissionRationale(Manifest.permission.CAMERA);
                requestPermissions(new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE}, 231);
            }
        }

        mPreviewer = (PicturesPreviewer) findViewById(R.id.prv);
        mPreviewer.setItemClickEvent(mItemClickEvent);
        mPreviewer.setMaxSize(4);


    }

    private PicturesPreviewer.DialogItemClickEvent mItemClickEvent = new PicturesPreviewer.DialogItemClickEvent() {
        @Override
        public void ItemOne() {
            takePhoto(0);
        }

        @Override
        public void ItemTwo() {
            openAlbum(0);
        }
    };


    private void takePhoto(int type) {
        tempCameraFilePath = Environment.getExternalStorageDirectory()
                + "/xioajiedai/" + System.currentTimeMillis() + ".jpg";
        File file = new File(tempCameraFilePath);
        if (!file.exists())
            file.getParentFile().mkdirs();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = Uri.fromFile(new File(tempCameraFilePath));

//        //适配7.0，使用 content uri 替换 File uri
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
//            uri = FileProvider.getUriForFile(this,
//                    BuildConfig.APPLICATION_ID + ".provider", file);
//        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

    /**
     * 打开相册
     *
     * @param type
     */
    private void openAlbum(int type) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, REQUEST_CODE_ALBUM);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_CAMERA: { //相机
                if (resultCode == RESULT_OK) {
                    mPreviewer.getImageAdapter().add(tempCameraFilePath);
                    mPreviewer.getImageAdapter().notifyDataSetChanged();
                }
                break;
            }

            case REQUEST_CODE_ALBUM: { //相册
                if (resultCode == RESULT_OK) {
                    if (data != null) {

                        //uri 转 file path
                        Uri uri = data.getData();
                        String pickPath = MiPictureHelper.getPath(this, uri);  // 获取图片路径的方法调用
                        Log.e(TAG, "onActivityResult: path------" + pickPath);

                        mPreviewer.getImageAdapter().add(pickPath);
                        mPreviewer.getImageAdapter().notifyDataSetChanged();
                    }
                }
                break;
            }
        }
    }
}
