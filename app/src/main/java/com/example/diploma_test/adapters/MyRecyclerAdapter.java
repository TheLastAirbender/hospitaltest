package com.example.diploma_test.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.models.News;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
    private Random random;
//    private List<String> dataset;
    private LiveData<List<News>> dataset = null;


    public void init(LiveData<List<News>> dataset) {
        //this.random = new Random(seed);
        this.dataset = dataset;
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.recycle_item;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new MyRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        //Integer newInt = dataset.get(position);
        System.out.println(position);
        System.out.println(dataset.getValue().get(position).getHeader());
        //try{ holder.setHeader(dataset.get(position).getHeader());} catch (Exception e) {System.out.println(e);};
        holder.setHeader(dataset.getValue().get(position).getHeader());
        holder.setText(dataset.getValue().get(position).getText());
//        holder.setText(dataset.get(position));
//        holder.flagView.setImageResource(state.getFlagResource());
//        holder.nameView.setText(state.getName());
//        holder.capitalView.setText(state.getCapital());
    }

    @Override
    public int getItemCount() {
        // сюда скорее всего из ViewModel вписать количество
        return 2;
    }
}