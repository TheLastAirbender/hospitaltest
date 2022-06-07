package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.recyclers.DashboardRecyclerAdapter;
import com.example.diploma_test.viewmodel.DashboardViewModel;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private DashboardRecyclerAdapter recyclerAdapter;
    private DashboardViewModel dashboardViewModel;
    private Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.toolbarmenu, menu);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_dashboard);
    }

    private void initRecyclerView(View context) {
        recyclerAdapter = new DashboardRecyclerAdapter();
        recyclerAdapter.init(dashboardViewModel.getNewsFeed());
        // Add the following lines to create RecyclerView
        recyclerView = context.findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // TODO: загрузить из ViewModel и бахнуть туда

        recyclerView.setAdapter(recyclerAdapter);
    }
}