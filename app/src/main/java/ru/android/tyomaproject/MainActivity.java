package ru.android.tyomaproject;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private RecyclerView recyclerView;
    private Adapter listAdapter;
    private EndlessOnScrollListener eosListener;
    private MyModel model;
    public MyPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myTag", "onCreate()");
        setContentView(R.layout.activity_main);

        initializeComponents();

        if (savedInstanceState == null) {
            presenter.requireMoreData();
        }
    }

    @Override
    public void initializeComponents() {
        Log.i("myTag", "initializeComponents()");
        recyclerView = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listAdapter = new Adapter();
        recyclerView.setAdapter(listAdapter);
        eosListener = new EndlessOnScrollListener(layoutManager, listAdapter, presenter);
        recyclerView.addOnScrollListener(eosListener);
        model = new MyModel();
        presenter = new MyPresenter(model, this);
        model.setPresenter(presenter);
    }

    @Override
    public void showNetworkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Network error")
                .setMessage("Network error")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setCancelable(false)
                .setNegativeButton("Ok",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    protected void onRestoreInstanceState(Bundle state) {
        super.onRestoreInstanceState(state);
        Log.i("myTag", "onRestoreInstanceState()");
        presenter.loadData(state, eosListener);
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        Log.i("myTag", "onSaveInstanceState()");
        presenter.saveData(state, eosListener);
    }

    public void acceptData(List<Post> postList) {
        listAdapter.setData(postList);
    }

    public void refresh() {
        listAdapter.refresh();
    }
}

