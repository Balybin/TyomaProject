package ru.android.tyomaproject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyModel implements MainContract.Model {

    public void setPresenter(MyPresenter presenter) {
        this.presenter = presenter;
    }

    private MyPresenter presenter;

//    public MyModel(MyPresenter presenter) {
//        this.presenter = presenter;
//    }

    private int userId = 1;
    private List<Post> postList;

    @Override
    public void loadMoreData() {
        NetworkService.getInstance().getJSONPlaceHolderApi().getPostWithUserID(userId).enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                addData(response.body());
                userId++;
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                presenter.requireErrorDialog();
            }
        });
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public List<Post> getPostList() {
        return postList;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public void setPostList(List<Post> myData) {
        this.postList = myData;
    }

    public void addData(List<Post> data) {
        if (postList == null) {
            postList = new ArrayList<>();
        }
        for (Post item : data) {
            postList.add(item);
        }
        presenter.refreshData();
    }
}
