package com.example.diploma_test.viewmodel;

import android.app.Application;

import com.example.diploma_test.entity.Channel;
import com.example.diploma_test.repo.ChannelRepo;
import com.example.diploma_test.repo.NewsRepo;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ChatsViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private ChannelRepo channelRepo;
    public static ChatsViewModel instance;

    public ChatsViewModel(@NonNull Application application) {
        super(application);
        channelRepo = new ChannelRepo(application);
    }

    public static ChatsViewModel getInstance(@NonNull Application application) {
        if (instance == null) {
            instance = new ChatsViewModel(application);
        }
        return instance;
    }

    public LiveData<List<Channel>> observableListOfAllChats() {
        return channelRepo.getAllChannelsFromRoom();
    }

    public void requestForAllChats() {
        channelRepo.getAllChatsFromServer();
    }
}