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
import com.example.diploma_test.api.GitHubRepo;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.recyclers.DashboardRecyclerAdapter;
import com.example.diploma_test.viewmodel.DashboardViewModel;

import java.util.List;

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

        dashboardViewModel.requestForAllNews();

        dashboardViewModel.observableListOfAllNews().observe(getViewLifecycleOwner(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                for (News item : news) {
                    System.out.println(item.getMessage());

                }

                recyclerAdapter.setNews(news);
            }
        });

//            @Override
//            public void onChanged(@Nullable String s) {
//                recyclerAdapter.notifyDataSetChanged();
//                //textView.setText(s);
//            }
//        });




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
        //recyclerAdapter.init(dashboardViewModel.observableListOfAllNews());
        // Add the following lines to create RecyclerView
        recyclerView = context.findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // TODO: загрузить из ViewModel и бахнуть туда

        recyclerView.setAdapter(recyclerAdapter);
    }
}