package com.example.diploma_test.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.listAdaptersAndHolders.DashboardRecyclerAdapter;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;
import com.example.diploma_test.viewmodel.DashboardViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class DashboardFragment extends Fragment {

    private SharedPreferences prefs;
    private RecyclerView recyclerView;
    private DashboardRecyclerAdapter recyclerAdapter;
    private DashboardViewModel dashboardViewModel;
    private Toolbar toolbar;
    private NavHostFragment navHostFragment;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        prefs = getActivity().getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dashboardViewModel.requestForAllNews();

        dashboardViewModel.observableListOfAllNews().observe(getViewLifecycleOwner(), new Observer<List<NewsInNewsfeed>>() {
            @Override
            public void onChanged(List<NewsInNewsfeed> news) {
                System.out.println("observable news changed" + news);
                for (NewsInNewsfeed item : news) {
                    System.out.println("Inside fragment" + item.getMessage());

                }

                recyclerAdapter.setNews(news);
            }
        });

        // TODO: ПЕРЕДЕЛАТЬ ИЗ КОЛХОЗА НОРМАЛЬНО
        navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        navController = navHostFragment.getNavController();
        FloatingActionButton fab = root.findViewById(R.id.addnewsfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println(recyclerAdapter.getNews(0).getMessage());

                Integer role = prefs.getInt("adminrole",0);
                if (role==0) {
                    Snackbar.make(view, "Извините, вы не можете публиковать новости!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                CreatePostFragment nextFrag= new CreatePostFragment();
                navController.navigate(R.id.create_new_post);

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
        //recyclerAdapter.init(dashboardViewModel.observableListOfAllNews());
        // Add the following lines to create RecyclerView
        recyclerView = context.findViewById(R.id.recyclerview);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // ODO: загрузить из ViewModel и бахнуть туда

        recyclerView.setAdapter(recyclerAdapter);
    }
}