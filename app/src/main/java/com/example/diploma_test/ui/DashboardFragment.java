package com.example.diploma_test.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.adapters.MyRecyclerAdapter;
import com.example.diploma_test.viewmodel.DashboardViewModel;
import com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private MyRecyclerAdapter recyclerAdapter;
    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        //final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                recyclerAdapter.notifyDataSetChanged();
                //textView.setText(s);
            }
        });

        initRecyclerView(root);


        return root;
    }

    private void initRecyclerView(View context) {
        recyclerAdapter = new MyRecyclerAdapter();
        recyclerAdapter.init(dashboardViewModel.getNewsFeed());
        // Add the following lines to create RecyclerView
        recyclerView = context.findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // загрузить из ViewModel и бахнуть туда

        recyclerView.setAdapter(recyclerAdapter);
    }
}