package com.example.diploma_test.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.diploma_test.R;
import com.example.diploma_test.entity.Channel;
import com.example.diploma_test.entity.Message;
import com.example.diploma_test.entity.MessageForTest;
import com.example.diploma_test.listAdaptersAndHolders.MessagesArrayAdapter;
import com.example.diploma_test.viewmodel.ChatsViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ChannelFragment extends Fragment {
    private ChatsViewModel chatsViewModel;
    private ListView messagesListView;
    private EditText messageEditText;

    // TODO: переделать нормально, через Обсервер, убрать весь хардкод
    private ArrayList messageArrayList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatsViewModel = ChatsViewModel.getInstance(getActivity().getApplication());

        // TODO: неправильно, он убирает чаты из стека вообще и при нажатии назад бросает в начало MainActivity (новостной фрагмент, соответственно)
        container.removeAllViews();
        //-------------------------

        View root = inflater.inflate(R.layout.fragment_insidechat, container, false);
        messagesListView = (ListView) root.findViewById(R.id.chatListView);
        messageEditText = (EditText) root.findViewById(R.id.messageEditText);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Профсоюз больных");

        messageArrayList = new ArrayList();
        String[] authors = {"Василий Орлов","Андрей Воробъев","Владимир Соловьев"};
        String[] messagesText = {"Привет, ну как, завтра на площади?","Завтра смогу только около двух","Я не могу, веду передачу"};
        String[] time = {"14:46","17:21","13:01"};
        for (int i=0; i<3; i++) {
            MessageForTest msg = new MessageForTest(authors[i], messagesText[i], time[i]);
            messageArrayList.add(msg);
        }
        MessagesArrayAdapter adapter = new MessagesArrayAdapter(getActivity(),messageArrayList);

        // устанавливаем для списка адаптер
        messagesListView.setAdapter(adapter);

        chatsViewModel.requestForAllChats();
        chatsViewModel.observableListOfAllChats().observe(getViewLifecycleOwner(), new Observer<List<Channel>>() {
            @Override
            public void onChanged(List<Channel> channels) {
//                chatsRecyclerAdapter.setChats(channels);
            }
        });
        return root;
    }
}
