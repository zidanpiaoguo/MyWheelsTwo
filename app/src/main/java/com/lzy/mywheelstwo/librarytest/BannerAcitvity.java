package com.lzy.mywheelstwo.librarytest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lzy.mylibrary.utils.banner.CarouseBanner;
import com.lzy.mywheelstwo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by 刘振远 on 2017/12/11.
 */

public class BannerAcitvity extends AppCompatActivity {

    @BindView(R.id.banner)
    CarouseBanner banner;


    @BindView(R.id.banner2)
    CarouseBanner banner2;
    @BindView(R.id.banner3)
    CarouseBanner banner3 ;

    private List<String> list = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_banner);
        ButterKnife.bind(this);

        generateData();
        //设置类型，正常的，或者画廊效果的 CarouselBanner.NORMAL/GALLERY
//        banner.setmType(CarouselBanner.NORMAL);
        //是否显示指示点
//        banner.setPointsIsVisible(true);
        //指示点margin
//        banner.setPointMargin(new float[]{0, 10, 20, 10});

        //每页margin

        //GALLERY类型此处设置无效 要在zoomOutPageTransformer 中设置MIN_SCALE
//        banner.setmPageMargin(3);
        //可以设置卡片效果，阴影
        banner.setCardElevation(30);
        //圆角
        banner.setRadius(20);
        //图片，设置网络图片  banner.setImagesUrl();
        banner.setImagesUrl(list);
        //item点击
        banner.setOnItemClickListener(new CarouseBanner.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                Toast.makeText(getApplicationContext(), "===" + position, Toast.LENGTH_SHORT).show();


            }
        });
        banner2.setmPageMargin(30);

        banner2.setImagesUrl(list);

        banner2.setPageWidth((float) 0.8);


        banner3.setImagesUrl(list);


    }


    private void generateData() {

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
        list.add(url0);
        list.add(url1);
        list.add(url2);
        list.add(url3);
        list.add(url4);
        list.add(url5);
        list.add(url6);
        list.add(url7);
        list.add(url8);
        list.add(url9);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        banner.stopAutoPlay();
    }


}
