package ru.android.tyomaproject;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private static final String TITLE = "title:\"";
    private static final String BODY = "body:\"";

    private List<Data> DataList;


    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_headline;
        private TextView tv_content;

        public ListViewHolder(View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_headline = itemView.findViewById(R.id.tv_headline);
        }

        public void bind(String Title, String Body) {
            tv_headline.setText(Title);
            tv_content.setText(Body);
        }
    }

    public Collection<Data> getData(){
        return DataList;
    }

    public void setData(Collection<Data> list) {
        DataList = (List<Data>) list;
        notifyDataSetChanged();
    }

    public void addData(Collection<Data> list) {
        for (Data item:list) {
            DataList.add(item);
        }
        notifyDataSetChanged();
    }


    public void clearData() {
        DataList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder listViewHolder, int i) {
        listViewHolder.bind(DataList.get(i).Title, DataList.get(i).Body);
    }

    @Override
    public int getItemCount() {
        if (DataList == null)
            return 0;
        else
            return DataList.size();
    }
}
