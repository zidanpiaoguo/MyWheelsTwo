package com.lzy.mywheelstwo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.image_view)
//    ImageView imageView;


    @BindView(R.id.image_view)
    PhotoView imageView;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.sw)
    SwipeRefreshLayout sw;
    private HomeAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        initView();
    }

    private void initView() {

    }

    private void init() {
        String ImageUrl = "http://10.10.10.182:8080/files/2017/10/20171025091602801-总图-拓扑图.jpg";

//        Glide.with(this).load(ImageUrl).placeholder(R.mipmap.ic_launcher).into(imageView);

        adapter = new HomeAdapter(R.layout.activity_main,list);
        sw.setColorSchemeColors(ContextCompat.getColor(this,R.color.app));
        rv.setLayoutManager(new LinearLayoutManager(this));
        initAdapter();
        addHeadView();

    }

    private void addHeadView() {

    }

    private void initAdapter() {
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                loadMore();
            }
        },rv);
    }

    private void loadMore() {
        ;

    }


}
