package com.example.diploma_test.recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.diploma_test.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatsRecyclerAdapter extends RecyclerView.Adapter {

    @Override
    public int getItemViewType(final int position) {
        return R.layout.chat_recycler_item;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ChatsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
