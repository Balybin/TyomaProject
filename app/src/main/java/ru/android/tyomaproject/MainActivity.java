package ru.android.tyomaproject;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    //private HttpClient httpClient;
    private Adapter listAdapter;
    private EndlessOnScrollListener eosListener;
    private int userId = 1;

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.i("myTag", "onRestoreInstanceState()");
        ArrayList<Data> data;
        data = state.getParcelableArrayList("data");
        listAdapter.setData((ArrayList)data);
        eosListener.setCurrentPage(state.getInt("currentPage"));
        eosListener.setFirstVisibleItem(state.getInt("firstVisibleItem"));
        eosListener.setPreviousTotal(state.getInt("previousTotal"));
        //eosListener.setTotalItemCount(state.getInt("totalItemCount"));
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Log.i("myTag", "onSaveInstanceState()");
        state.putParcelableArrayList("data", new ArrayList<Data>((ArrayList)listAdapter.getData()));
        state.putInt("currentPage", eosListener.getCurrentPage());
        state.putInt("firstVisibleItem", eosListener.getFirstVisibleItem());
        state.putInt("previousTotal", eosListener.getPreviousTotal());
        //state.putInt("totalItemCount", eosListener.getTotalItemCount());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myTag", "onCreate()");
        setContentView(R.layout.activity_main);

        initRecyclerView();

        //httpClient = new HttpClient();

        if (savedInstanceState == null) {
//            loadData(userId);
//            userId++;
//            try {
//                listAdapter.addData(NetworkService.getInstance()
//                        .getJSONPlaceHolderApi()
//                        .getPostWithUserID(1)
//                        .execute().body());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            NetworkService.getInstance()
                        .getJSONPlaceHolderApi()
                        .getPostWithUserID(1)
                        .enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                     listAdapter.setData((ArrayList)response.body());
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                    }
                });
        }
    }

    public void initRecyclerView() {
        Log.i("myTag", "initRecyclerView()");
        list = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        listAdapter = new Adapter();
        list.setAdapter(listAdapter);
        eosListener = new EndlessOnScrollListener(layoutManager, listAdapter);
        list.addOnScrollListener(eosListener);
    }
}

//    public void loadData(int userId) {
//        Log.i("myTag","loadData()");
//        final int finalUseId = userId;
//        new AsyncTask<Void, Void, Collection<Data>>() {
//            @Override
//            protected Collection<Data> doInBackground(Void... voids) {
//                try {
//                    return httpClient.readDataInfo(finalUseId);
//                } catch (IOException | JSONException e) {
//                    e.printStackTrace();
//                    return null;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(Collection<Data> data) {
//                listAdapter.setData(data);
//            }
//        }.execute();
//    }
//}
