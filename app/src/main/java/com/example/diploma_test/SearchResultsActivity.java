package com.example.diploma_test;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SearchResultsActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);
        handleIntent(getIntent());

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        //AppCompatActivity activity = this;
        toolbar.setTitle(R.string.search_results);
        setSupportActionBar(toolbar);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        // ...
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("SEARCHING FOR " + query + " INSIDE SEARCHACTIVITY");
            //use the query to search your data somehow
        }
    }
    //...
}