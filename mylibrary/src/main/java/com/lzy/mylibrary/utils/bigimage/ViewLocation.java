package com.lzy.mylibrary.utils.bigimage;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 刘振远 on 2017/11/25.
 */

public class ViewLocation implements Parcelable {
    public float x;
    public float y;
    public float width;
    public float height;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(this.y);
        dest.writeFloat(this.width);
        dest.writeFloat(this.height);
    }

    public ViewLocation() {
    }

    protected ViewLocation(Parcel in) {
        this.x = in.readFloat();
        this.y = in.readFloat();
        this.width = in.readFloat();
        this.height = in.readFloat();
    }

    public static final Parcelable.Creator<ViewLocation> CREATOR = new Parcelable.Creator<ViewLocation>() {
        @Override
        public ViewLocation createFromParcel(Parcel source) {
            return new ViewLocation(source);
        }

        @Override
        public ViewLocation[] newArray(int size) {
            return new ViewLocation[size];
        }
    };
}
