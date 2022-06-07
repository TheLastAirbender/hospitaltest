package com.example.diploma_test.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.recyclers.ChatsRecyclerAdapter;
import com.example.diploma_test.recyclers.DashboardRecyclerAdapter;
import com.example.diploma_test.viewmodel.ChatsViewModel;

public class ChatsFragment extends Fragment {

    private ChatsViewModel chatsViewModel;
    private RecyclerView chatsRecyclerView;
    private ChatsRecyclerAdapter chatsRecyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatsViewModel =
                new ViewModelProvider(this).get(ChatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chats, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);
        chatsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });

        /*String [] values = new String[]{"penises","promises"};
        ListView chatsListView = root.findViewById(R.id.chatsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.chat_recycler_item,values);
        chatsListView.setAdapter(adapter);*/
        initRecyclerView(root);
        return root;
    }

    private void initRecyclerView(View context) {
        chatsRecyclerAdapter = new ChatsRecyclerAdapter();
        //chatsRecyclerAdapter.init(dashboardViewModel.getNewsFeed());
        // Add the following lines to create RecyclerView
        chatsRecyclerView = context.findViewById(R.id.chatRecyclerView);
        //recyclerView.setHasFixedSize(true);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // загрузить из ViewModel и бахнуть туда

        chatsRecyclerView.setAdapter(chatsRecyclerAdapter);
    }
}