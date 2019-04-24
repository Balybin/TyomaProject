package ru.android.tyomaproject;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MyPresenter implements MainContract.Presenter {
    private MyModel model;
    private MainActivity view;

    public MyPresenter(MyModel model, MainActivity view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void requireMoreData() {
        model.loadMoreData();
    }

    @Override
    public void requireErrorDialog() {
        view.showNetworkError();
    }

    @Override
    public void loadData(Bundle state, EndlessOnScrollListener eosl) {
        eosl.setCurrentPage(state.getInt("currentPage"));
        eosl.setFirstVisibleItem(state.getInt("firstVisibleItem"));
        eosl.setPreviousTotal(state.getInt("previousTotal"));
        //eosl.setTotalItemCount(state.getInt("totalItemCount"));
        model.setPostList(state.<Post>getParcelableArrayList("PostList"));
        model.setUserId(state.getInt("userId"));
        view.acceptData(model.getPostList());
    }

    @Override
    public void saveData(Bundle state, EndlessOnScrollListener eosl) {
        state.putParcelableArrayList("PostList", (ArrayList) model.getPostList());
        state.putInt("userId", model.getUserId());
        state.putInt("currentPage",eosl.getCurrentPage());
        state.putInt("firstVisibleItem",eosl.getFirstVisibleItem());
        state.putInt("previousTotal",eosl.getPreviousTotal());
        //state.putInt("totalItemCount",eosl.getTotalItemCount());
    }

    public void refreshData() {
        view.acceptData(model.getPostList());
    }
}
