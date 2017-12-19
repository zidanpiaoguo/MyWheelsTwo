//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lzy.mylibrary.view.addpicture;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import com.lzy.mylibrary.view.addpicture.ActionSheetDialog.OnSheetItemClickListener;
import com.lzy.mylibrary.view.addpicture.ActionSheetDialog.SheetItemColor;
import com.lzy.mylibrary.view.addpicture.SelectImageAdapter.Callback;

public class PicturesPreviewer extends RecyclerView implements Callback {
    private SelectImageAdapter mImageAdapter;
    private ItemTouchHelper mTouchHelper;
    private Context mContext;
    private PicturesPreviewer.DialogItemClickEvent mItemClickEvent;
    private GridLayoutManager gridLayoutManager;
    private String popTitle;
    private String itemTextColor;

    public PicturesPreviewer(Context context) {
        this(context, (AttributeSet) null);
    }

    public PicturesPreviewer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PicturesPreviewer(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.popTitle = "";
        this.itemTextColor = "";
        this.mContext = context;
        this.init();
    }

    private void init() {
        this.mImageAdapter = new SelectImageAdapter(this, this.mContext);
        if(this.mImageAdapter.getMaxSize()<=3){
            this.gridLayoutManager = new GridLayoutManager(this.getContext(), 3);

        }else {
            this.gridLayoutManager = new GridLayoutManager(this.getContext(),3);

        }

        this.setLayoutManager(this.gridLayoutManager);
        this.setAdapter(this.mImageAdapter);
        this.setOverScrollMode(2);
        PicturesPreviewerItemTouchCallback callback = new PicturesPreviewerItemTouchCallback(this.mImageAdapter);
        this.mTouchHelper = new ItemTouchHelper(callback);
        this.mTouchHelper.attachToRecyclerView(this);
        this.popTitle = "您最多可以选择" + this.mImageAdapter.getCanChooseNum() + "张";
    }

    public void onLoadMoreClick() {
        initDialog();
    }

    private void initDialog() {
        (new ActionSheetDialog(this.mContext)).builder().setTitle(this.getPopMenuTitle())
                .setCancelable(false).setCanceledOnTouchOutside(false)
                .addSheetItem("拍照", this.getItemTextColor(), new OnSheetItemClickListener() {
            public void onClick(int which) {
                PicturesPreviewer.this.mItemClickEvent.ItemOne();
            }
        }).addSheetItem("相册", this.getItemTextColor(), new OnSheetItemClickListener() {
            public void onClick(int which) {
                PicturesPreviewer.this.mItemClickEvent.ItemTwo();
            }
        }).show();
    }

    public void setPopMenuTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            this.popTitle = title;
        }

    }

    public String getPopMenuTitle() {
        return this.popTitle;
    }

    public void setItemClickEvent(PicturesPreviewer.DialogItemClickEvent itemClickEvent) {
        this.mItemClickEvent = itemClickEvent;
    }

    public SheetItemColor getItemTextColor() {
        if (!TextUtils.isEmpty(this.itemTextColor)) {
            SheetItemColor.CUSTOM.setName(this.itemTextColor);
        } else {
            SheetItemColor.CUSTOM.setName(SheetItemColor.Red.getName());
        }

        return SheetItemColor.CUSTOM;
    }

    public void setItemTextColor(String itemTextColor) {
        this.itemTextColor = itemTextColor;
    }

    public SelectImageAdapter getImageAdapter() {
        return this.mImageAdapter;
    }

    public void setMaxSize(int maxSize) {
        if (maxSize != mImageAdapter.getMaxSize()) {
            mImageAdapter.setMaxSize(maxSize);
            gridLayoutManager = new GridLayoutManager(this.getContext(),
                    4);
            setLayoutManager(this.gridLayoutManager);
//            initDialog();
        }
    }

    public interface DialogItemClickEvent {
        void ItemOne();

        void ItemTwo();
    }
}
