package ru.android.tyomaproject;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {//} implements MainContract.View {

    //private MyModel model;
    //private MyPresenter presenter;
    //private RecyclerView recyclerView;
    //private Adapter listAdapter;
    //private EndlessOnScrollListener eosListener;
    Fragment1 fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("myTag", "onCreate()");
        setContentView(R.layout.activity_main);
        //fragment = findViewById(R.layout.fragment1);
        //initializeComponents();

        //if (savedInstanceState == null) {
        //    presenter.requireMoreData();
        //    Log.i("myTag", "onCreate()::presenter.requireMoreData()");
        //}
    }

//    @Override
//    public void initializeComponents() {
//        Log.i("myTag", "initializeComponents()");
//        //model = new MyModel();
//        //presenter = new MyPresenter(model, this);
//        model.setPresenter(presenter);
//        recyclerView = findViewById(R.id.rv);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        listAdapter = new Adapter();
//        recyclerView.setAdapter(listAdapter);
//        Log.i("myTag", "EndlessOnScrollListener");
//        //eosListener = new EndlessOnScrollListener(layoutManager, presenter);
//        recyclerView.addOnScrollListener(eosListener);
//
//        Log.i("myTag", "model.setPresenter()");
//    }

//    @Override
//    public void showNetworkError() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Network error")
//                .setMessage("Network error")
//                .setIcon(android.R.drawable.ic_dialog_info)
//                .setCancelable(false)
//                .setNegativeButton("Ok",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle state) {
//        super.onRestoreInstanceState(state);
//        Log.i("myTag", "onRestoreInstanceState()");
//        //presenter.loadData(state, eosListener);
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle state) {
//        super.onSaveInstanceState(state);
//        Log.i("myTag", "onSaveInstanceState()");
//        //presenter.saveData(state, eosListener);
//    }
//
//    @Override
//    public void acceptData(List<Post> postList) {
//        listAdapter.setData(postList);
//    }

}

