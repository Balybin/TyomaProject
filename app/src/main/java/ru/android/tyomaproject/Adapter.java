package ru.android.tyomaproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ListViewHolder> {
    private static final String TITLE = "title:\"";
    private static final String BODY = "body:\"";

    private List<Post> DataList;


    public class ListViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHeadline;
        private TextView tvContent;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tv_content);
            tvHeadline = itemView.findViewById(R.id.tv_headline);
        }

        public void bind(String Title, String Body) {
            tvHeadline.setText(Title);
            tvContent.setText(Body);
        }
    }

    public List<Post> getData(){
        return DataList;
    }

    public void setData(List<Post> list) {
        DataList = (List<Post>) list;
        notifyDataSetChanged();
    }

    public void addData(List<Post> list) {
        for (Post item:list) {
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
        listViewHolder.bind(DataList.get(i).getTitle(), DataList.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        if (DataList == null)
            return 0;
        else
            return DataList.size();
    }
}
