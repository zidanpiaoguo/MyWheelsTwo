package com.lzy.mylibrary.view.addpicture;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.lzy.mylibrary.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片选择
 * <p>
 * Created by imtianx on 2017-3-7 10:24.
 */

public class SelectImageAdapter extends RecyclerView.Adapter<SelectImageAdapter.SelectImageHolder>
        implements PicturesPreviewerItemTouchCallback.ItemTouchHelperAdapter {

    private int MAX_SIZE = 6;
    private final int TYPE_NOME = 1;
    private final int TYPE_ADD = 2;
    private final List<String> mDatas = new ArrayList<>();
    private Callback mCallback;
    private Context mContext;

    public SelectImageAdapter(Callback callback, Context context) {
        mCallback = callback;
        mContext = context;
    }

    public int getCanChooseNum() {
        return getMaxSize() - mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int size = mDatas.size();
        if (size >= MAX_SIZE) {
            return TYPE_NOME;
        } else if (position == size) {
            return TYPE_ADD;
        } else {
            return TYPE_NOME;
        }
    }


    @Override
    public SelectImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_add_picture, parent, false);
        if (viewType == TYPE_NOME) {
            return new SelectImageHolder(view, new HolderListener() {
                @Override
                public void onDelete(String s) {
                    Callback callback = mCallback;
                    if (callback != null) {
                        int pos = mDatas.indexOf(s);
                        if (pos == -1) {
                            return;
                        }
                        mDatas.remove(pos);
                        notifyDataSetChanged();
                    }
                }

                @Override
                public void onClick(String s) {
                    Toast.makeText(mContext, "添加图片", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            return new SelectImageHolder(view, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Callback callback = mCallback;
                    if (callback != null)
                        callback.onLoadMoreClick();

                }
            });
        }
    }

    @Override
    public void onBindViewHolder(SelectImageHolder holder, int position) {

        int size = mDatas.size();

        if (size >= MAX_SIZE || size != position) {
            String path = mDatas.get(position);

            Glide.with(mContext).load(new File(path)).into(holder.mIvPic);
            holder.mIvPicDel.setTag(mDatas.get(position));
        }
        if (size == 0) {
            holder.mTvPicText.setText("添加图片");
        } else {
            holder.mTvPicText.setText(size + "/" + getMaxSize());
        }

    }

    @Override
    public int getItemCount() {
        int size = mDatas.size();
        return size == MAX_SIZE ? size : size + 1;
    }

    public List<String> getDatas() {
        return mDatas;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        // TODO: 2017-3-7
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mDatas.remove(position);
        notifyItemChanged(position);
    }

    /**
     * 设置最大数目
     *
     * @param maxSize
     */
    public void setMaxSize(int maxSize) {
        MAX_SIZE = maxSize > MAX_SIZE ? maxSize : MAX_SIZE;
        notifyDataSetChanged();
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    class SelectImageHolder extends RecyclerView.ViewHolder {

        private ImageView mIvPic;
        private ImageView mIvPicDel;
        private TextView mTvPicText;
        private HolderListener mListener;


        public SelectImageHolder(View itemView, HolderListener listener) {
            super(itemView);
            mListener = listener;
            mIvPic = (ImageView) itemView.findViewById(R.id.iv_item_select_pic);
            mIvPicDel = (ImageView) itemView.findViewById(R.id.iv_item_select_pic_del);
            mTvPicText = (TextView) itemView.findViewById(R.id.tv_item_select_pic_text);
            mTvPicText.setVisibility(View.GONE);

            mIvPicDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object o = v.getTag();
                    final HolderListener holderListener = mListener;
                    if (holderListener != null && o != null && o instanceof String) {
                        holderListener.onDelete((String) o);
                    }
                }
            });

            mIvPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object obj = mIvPicDel.getTag();
                    final HolderListener holderListener = mListener;
                    if (holderListener != null && obj != null && obj instanceof String) {
                        holderListener.onClick((String) obj);
                    }
                }
            });
            mIvPic.setBackgroundColor(0xffdadada);
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public SelectImageHolder(View itemView, View.OnClickListener clickListener) {
            super(itemView);
            mIvPic = (ImageView) itemView.findViewById(R.id.iv_item_select_pic);
            mIvPicDel = (ImageView) itemView.findViewById(R.id.iv_item_select_pic_del);
            mTvPicText = (TextView) itemView.findViewById(R.id.tv_item_select_pic_text);

            mIvPicDel.setVisibility(View.GONE);
            mTvPicText.setVisibility(View.VISIBLE);
            int size = mDatas.size();
            if (size == 0) {
                mTvPicText.setText("添加图片");
            } else {
                mTvPicText.setText(size + "/" + MAX_SIZE);
            }
            mIvPic.setImageResource(R.mipmap.icon_xiang_ji);
            mIvPic.setBackground(null);
            mIvPic.setOnClickListener(clickListener);
        }

    }

    public void clear() {
        mDatas.clear();
    }

    public void addAll(ArrayList<String> s) {
        clear();
        mDatas.addAll(s);
        notifyDataSetChanged();
    }


    public void add(String s) {
        if (mDatas.size() >= MAX_SIZE)
            return;
        mDatas.add(s);
        notifyDataSetChanged();
    }

    /**
     * Holder 与Adapter之间的桥梁
     */
    interface HolderListener {
        void onDelete(String s);

        void onClick(String s);
    }

    public interface Callback {
        void onLoadMoreClick();

        Context getContext();
    }
}
