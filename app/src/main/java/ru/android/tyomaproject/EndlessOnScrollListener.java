package ru.android.tyomaproject;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.Collection;

public class EndlessOnScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;
    private Adapter adapter;

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

    public EndlessOnScrollListener(LinearLayoutManager layoutManager, Adapter adapter) {
        this.layoutManager = layoutManager;
        this.adapter = adapter;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleItemCount)) {
            //End has been reached

            //Do something
            currentPage++;

            Log.i("myTag","make http request, user id = " + currentPage);
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public void onLoadMore(int userId){
        final int finalUseId = userId;
        new AsyncTask<Void, Void, Collection<Data>>(){
            @Override
            protected Collection<Data> doInBackground(Void...voids){
                try{
                    HttpClient httpClient = new HttpClient();
                    return httpClient.readDataInfo(finalUseId);
                } catch(IOException | JSONException e){
                    e.printStackTrace();
                    return null;
                }
            }
            @Override
            protected void onPostExecute(Collection<Data> data){
                adapter.addData(data);
            }
        }.execute();
    }

}
