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

public class ChannelViewModel extends AndroidViewModel {
    private ChannelRepo chatRepo;

    public ChannelViewModel(@NonNull Application application) {
        super(application);
        chatRepo = new ChannelRepo(application);
    }

    public LiveData<List<Channel>> observableListOfAllNews() {
        return chatRepo.getAllChannelsFromRoom();
    }

    public void requestForAllNews() {
        chatRepo.getAllChatsFromServer();
    }
}
