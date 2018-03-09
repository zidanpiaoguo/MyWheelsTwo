package com.lzy.mywheelstwo.librarytest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.engine.www.coloZXing.activity.CaptureActivity;
import com.engine.www.coloZXing.utils.Constant;
import com.engine.www.coloZXing.utils.LogUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.lzy.mywheelstwo.R;

public class ZxingTest extends AppCompatActivity {

    private static final String TAG = "ZxingTest";
    private Button btZxing;
    private Button btGet;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zxing_test);
        initView();

    }




    private void initView() {
        btZxing = (Button) findViewById(R.id.bt_zxing);
        btZxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ZxingTest.this, CaptureActivity.class);
                startActivityForResult(intent, Constant.REQUEST_CODE_SCAN);
            }
        });


        btGet = (Button) findViewById(R.id.bt_get);
        ivImage = (ImageView) findViewById(R.id.iv_image);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                ivImage.setImageBitmap(ecodeImage("17386049422"));
            }
        });

    }


    private Bitmap ecodeImage(String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
            // 使用 ZXing Android Embedded 要写的代码

            int w = result.getWidth();
            int h = result.getHeight();
            int[] pixels = new int[w * h];

            for (int y = 0; y < h; y++) {
                int offset = y * w;
                for (int x = 0; x < w; x++) {
                    pixels[offset + x] = result.get(x, y) ? 0xFF000000  : 0xFFFFFFFF;
                }
            }
            bitmap = Bitmap.createBitmap(pixels,0,w,w, h, Bitmap.Config.ARGB_8888);
//            bitmap.setPixels(pixels, 0, 100, 0, 0, w, h);

        } catch (WriterException e) {
            Log.d(TAG, "ecodeImage:111 "+e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException iae) { // ?
            Log.d(TAG, "ecodeImage:222 "+iae.getMessage());
            return null;
        }

        Log.d(TAG, "ecodeImage: "+bitmap.toString());
        return bitmap;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        LogUtil.d("TANHQ===> onActivityResult: requestCode = " + requestCode + ", resultCode = " + resultCode);
        LogUtil.d("TANHQ===> onActivityResult: data = " + data);

        switch (requestCode) {
            case Constant.REQUEST_CODE_SCAN:
                handleRequestCodeScanEvent(resultCode, data);
                break;
            default:
                break;
        }
    }

    /**
     * handle the scanning event
     *
     * @param resultCode
     * @param data
     */
    private void handleRequestCodeScanEvent(int resultCode, Intent data) {
        LogUtil.d("TANHQ===> handleEvent: resultCode = " + resultCode + ", data = " + data);

        if (resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra(Constant.REQUEST_CODE_SCAN_RESULT);

        }
    }


}
