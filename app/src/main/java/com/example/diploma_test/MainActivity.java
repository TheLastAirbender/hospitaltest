package com.example.diploma_test;

import android.app.SearchManager;
import android.app.appsearch.AppSearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SearchView searchView;

    // TODO: Видимо, надо хранить все кабинеты, врачей, которых будем искать через поиск локально в
    //  какой-то базе данных, потому что поиск будет осуществляться с помощью ArrayAdapter.getFilter().filter() и потом выводиться в ListView,
    //  ну либо через Recycler, но это сложнее
    //  И ПОТОМ ЧЕРЕЗ VIEWMODEL ДОСТАВАТЬ ЭТИ ЗНАЧЕНИЯ

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = this.getSharedPreferences("com.example.diploma_test", Context.MODE_PRIVATE);
        if (prefs == null) {
            this.finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_chats, R.id.navigation_dashboard, R.id.navigation_wiki, R.id.navigation_map)
                .build();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        //NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        //AppCompatActivity activity = this;
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("SUKKKAAAA");
            //doMySearch(query);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);

        searchView = (SearchView) myActionMenuItem.getActionView();

        initSearchListener();

        return true;
    }

    private void initSearchListener() {
        searchView.setQueryHint(getString(R.string.search_hint));
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Toast like print
                //UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                System.out.println("SUBMIT IN MAINACTIVITY");
                //myActionMenuItem.collapseActionView();
                //TODO: здесь, видимо, должно быть обращение к ВьюМодел и поиск
                // noteViewModel.queryNotes(query)
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // Text changed, perform flitering?

                // This resets the notes list to display all notes if the query is
                // cleared.
//                if (newText.isEmpty()) noteViewModel.queryNotes()
                return false;
            }
        });
    }

}