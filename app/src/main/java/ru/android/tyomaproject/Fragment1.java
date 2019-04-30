package ru.android.tyomaproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class Fragment1 extends Fragment implements MainContract.View {

    private MyPresenter presenter;
    private Adapter adapter;
    private EndlessOnScrollListener endlessOnScrollListener;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = getView().findViewById(R.id.rv);
        initializeComponents();
        if (savedInstanceState == null) {
            presenter.requireMoreData();
        } else {
            presenter.loadData(savedInstanceState, endlessOnScrollListener);
        }

    }

    @Override
    public void initializeComponents() {
        Log.i("myTag", "initializeComponents");
        presenter = new MyPresenter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Adapter();
        recyclerView.setAdapter(adapter);
        endlessOnScrollListener = new EndlessOnScrollListener(linearLayoutManager, presenter);
        recyclerView.addOnScrollListener(endlessOnScrollListener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.saveData(outState, endlessOnScrollListener);
    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void acceptData(List<Post> postList) {
        adapter.setData(postList);
    }
}
