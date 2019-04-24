package ru.android.tyomaproject;

import android.os.Bundle;

import java.util.List;

public interface MainContract {
    interface View{
        void initializeComponents();
        void showNetworkError();
    }
    interface Presenter{
        void saveData(Bundle state, EndlessOnScrollListener eosl);
        void requireMoreData();
        void requireErrorDialog();
        void loadData(Bundle state, EndlessOnScrollListener eosl);

    }
    interface Model{
        void loadMoreData();
        List<Post> getPostList();
        void setPostList(List<Post> myData);
        int getUserId();
        void setUserId(int userId);
    }
}
