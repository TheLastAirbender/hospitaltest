package com.example.diploma_test.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.News;

import java.util.List;
import java.util.Random;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerViewHolder> {
    private Random random;
//    private List<String> dataset;
    private LiveData<List<News>> dataset = null;


    public void init(LiveData<List<News>> dataset) {
        //this.random = new Random(seed);
        this.dataset = dataset;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.feed_recycle_item;
    }

    @NonNull
    @Override
    public DashboardRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new DashboardRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardRecyclerViewHolder holder, int position) {
        //Integer newInt = dataset.get(position);
        System.out.println(position);
        System.out.println(dataset.getValue().get(position).getAuthor());
        //try{ holder.setHeader(dataset.get(position).getHeader());} catch (Exception e) {System.out.println(e);};
        holder.setPostAuthor(dataset.getValue().get(position).getAuthor());
        holder.setPostInfoAndDate(dataset.getValue().get(position).getInfoAndDate().toString());
        holder.setPostText(dataset.getValue().get(position).getText());
//        holder.setText(dataset.get(position));
//        holder.flagView.setImageResource(state.getFlagResource());
//        holder.nameView.setText(state.getName());
//        holder.capitalView.setText(state.getCapital());
    }

    @Override
    public int getItemCount() {
        // сюда скорее всего из ViewModel вписать количество
        return dataset.getValue().size();
    }
}