package ru.android.tyomaproject;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EndlessOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;
    public MyPresenter presenter;

    public int getPreviousTotal() {
        return previousTotal;
    }

    public int getFirstVisibleItem() {
        return firstVisibleItem;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setPreviousTotal(int previousTotal) {
        this.previousTotal = previousTotal;
    }

    public void setFirstVisibleItem(int firstVisibleItem) {
        this.firstVisibleItem = firstVisibleItem;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    private int previousTotal = 0; //The total number  of items in the dataset after last load
    private boolean loading = true; // True, if we are still waiting for the last set of data to load
    private int visibleThreshold = 5; // The minimum  amount of items to have bellow your current scroll position before loading more
    int firstVisibleItem, visibleItemCount, totalItemCount;

    private int currentPage = 1;

    public EndlessOnScrollListener(LinearLayoutManager layoutManager, MyPresenter presenter) {
        this.layoutManager = layoutManager;
        this.presenter = presenter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount >= previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleItemCount)) {
            //End has been reached

            //Do something
            currentPage++;

            //Log.i("myTag", "make http request, user id = " + currentPage);
            //onLoadMore(currentPage);
            Log.i("myTag", "presenter.requireMoreData()");
            presenter.requireMoreData();
            loading = true;
        }
    }

//    public void onLoadMore(int userId) {
//        NetworkService.getInstance()
//                .getJSONPlaceHolderApi()
//                .getPostWithUserID(currentPage)
//                .enqueue(new Callback<List<Post>>() {
//                    @Override
//                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                        List<Post> data = response.body();
//                        adapter.addData(data);
//                    }
//                    @Override
//                    public void onFailure(Call<List<Post>> call, Throwable t) {
//                        t.printStackTrace();
//                    }
//                });
//    }

}
