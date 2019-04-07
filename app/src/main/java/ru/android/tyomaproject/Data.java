package ru.android.tyomaproject;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    public String Title;
    public String Body;

//    public String getTitle() {
//        return Title;
//    }
//
//    public String getBody() {
//        return Body;
//    }
//
//    public void setTitle(String title) {
//        Title = title;
//    }
//
//    public void setBody(String body) {
//        Body = body;
//    }

    public Data(String title, String body) {
        Title = title;
        Body = body;
    }

    protected Data(Parcel in) {
        Title = in.readString();
        Body = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {Title, Body});
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}