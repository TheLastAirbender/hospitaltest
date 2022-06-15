package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.Channel;
import com.example.diploma_test.listAdaptersAndHolders.ChatsRecyclerAdapter;
import com.example.diploma_test.viewmodel.ChatsViewModel;

import java.util.List;

public class ChatsFragment extends Fragment implements ChatsRecyclerAdapter.ItemClickListener {

    private ChatsViewModel chatsViewModel;
    private RecyclerView chatsRecyclerView;
    private ChatsRecyclerAdapter chatsRecyclerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        chatsViewModel = new ViewModelProvider(this).get(ChatsViewModel.class);
        chatsViewModel = ChatsViewModel.getInstance(getActivity().getApplication());
        View root = inflater.inflate(R.layout.fragment_chats, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        chatsViewModel.requestForAllChats();
        chatsViewModel.observableListOfAllChats().observe(getViewLifecycleOwner(), new Observer<List<Channel>>() {
            @Override
            public void onChanged(List<Channel> channels) {
                chatsRecyclerAdapter.setChats(channels);
            }
        });



        /*String [] values = new String[]{"penises","promises"};
        ListView chatsListView = root.findViewById(R.id.chatsListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.chat_recycler_item,values);
        chatsListView.setAdapter(adapter);*/
        initRecyclerView(root);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_chats);

        return root;
    }

    private void initRecyclerView(View context) {
        chatsRecyclerAdapter = new ChatsRecyclerAdapter(this);
        //chatsRecyclerAdapter.init(dashboardViewModel.getNewsFeed());
        // Add the following lines to create RecyclerView
        chatsRecyclerView = context.findViewById(R.id.chatRecyclerView);
        //recyclerView.setHasFixedSize(true);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(context.getContext()));
        // загрузить из ViewModel и бахнуть туда

        chatsRecyclerView.setAdapter(chatsRecyclerAdapter);
    }

    @Override
    public void onItemClick(int position) {
        System.out.println(position);
        ChannelFragment channelFragment = new ChannelFragment();
//                AppCompatActivity activ = (AppCompatActivity)v.getContext();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.chatListRelativeLayout, channelFragment, "findThisFragment");
                transaction.addToBackStack(null);
                transaction.commit();
//        Intent intent = new Intent(getActivity().this,)
    }
}