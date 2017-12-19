package com.lzy.mywheelstwo.librarytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.liyi.autogrid.AutoGridView;
import com.liyi.autogrid.BaseGridAdapter;
import com.lzy.mylibrary.utils.bigimage.ImageViewer;
import com.lzy.mylibrary.utils.bigimage.ViewLocation;
import com.lzy.mywheelstwo.R;

import java.util.ArrayList;

/**
 * Created by 刘振远 on 2017/11/25.
 */

public class BigImageTest extends AppCompatActivity {
    private AutoGridView autoGridView;

    private ImageViewer imageViewer;
    private ArrayList<Object> mImageDatas;
    private ArrayList<ViewLocation> mViewDatas;
    private RequestOptions mOptions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pic);
        initUI();
        addListener();
    }

    private void initUI() {
        autoGridView = (AutoGridView) findViewById(R.id.autogridview);
        generateData();

        mViewDatas = new ArrayList<>();
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.img_viewer_placeholder).error(R.mipmap.img_viewer_error);
        imageViewer = ImageViewer.getInstance()
                .indexPos(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL)
                .imageData(mImageDatas);
        autoGridView.setAdapter(new MyAdapter());
    }



    private void addListener() {
        autoGridView.setOnItemClickListener(new AutoGridView.OnItemClickListener() {
            @Override
            public void onItemClick(int i, View view) {

                int[] location = new int[2];
                // 获取在整个屏幕内的绝对坐标
                view.getLocationOnScreen(location);
                ViewLocation viewData = new ViewLocation();
                viewData.x = location[0];
                viewData.y = location[1];
                viewData.width = view.getMeasuredWidth();
                viewData.height = view.getMeasuredHeight();
                imageViewer.beginIndex(i)
                        .viewData(viewData)
                        .show(BigImageTest.this);
            }
        });
    }

    private void generateData() {
        mImageDatas = new ArrayList<>();
        String url0 = "http://img5.duitang.com/uploads/item/201404/11/20140411214939_XswXa.jpeg";
        String url1 = "http://att.bbs.duowan.com/forum/201210/20/210446opy9p5pghu015p9u.jpg";
        String url2 = "https://b-ssl.duitang.com/uploads/item/201505/09/20150509221719_kyNrM.jpeg";
        String url3 = "https://b-ssl.duitang.com/uploads/item/201709/26/20170926131419_8YhLA.jpeg";
        String url4 = "https://b-ssl.duitang.com/uploads/item/201505/11/20150511122951_MAwVZ.jpeg";
        String url5 = "https://b-ssl.duitang.com/uploads/item/201704/23/20170423205828_BhNSv.jpeg";
        String url6 = "https://b-ssl.duitang.com/uploads/item/201706/30/20170630181644_j4mh5.jpeg";
        String url7 = "https://b-ssl.duitang.com/uploads/item/201407/22/20140722172759_iPCXv.jpeg";
        String url8 = "https://b-ssl.duitang.com/uploads/item/201511/11/20151111103149_mrRfd.jpeg";
        String url9 = "https://b-ssl.duitang.com/uploads/item/201510/14/20151014172010_RnJVz.jpeg";
        mImageDatas.add(url0);
        mImageDatas.add(url1);
        mImageDatas.add(url2);
        mImageDatas.add(url3);
        mImageDatas.add(url4);
        mImageDatas.add(url5);
        mImageDatas.add(url6);
        mImageDatas.add(url7);
        mImageDatas.add(url8);
        mImageDatas.add(url9);
    }

    private class MyAdapter extends BaseGridAdapter {

        @Override
        public int getCount() {
            return mImageDatas != null ? mImageDatas.size() : 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ItemHolder holder = null;
            if (view == null) {
                view = LayoutInflater.from(BigImageTest.this).inflate(R.layout.item_auto_grid, null);
                holder = new ItemHolder();
                holder.iv_grid = (ImageView) view.findViewById(R.id.iv_item_grid);
                view.setTag(holder);
            } else {
                holder = (ItemHolder) view.getTag();
            }
            Glide.with(BigImageTest.this)
                    .load(mImageDatas.get(i))
                    .apply(mOptions)
                    .into(holder.iv_grid);
            return view;
        }

        private class ItemHolder {
            private ImageView iv_grid;
        }
    }
}
