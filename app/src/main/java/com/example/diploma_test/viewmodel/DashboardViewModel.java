package com.example.diploma_test.viewmodel;

import com.example.diploma_test.models.News;
import com.example.diploma_test.repo.NewsRepo;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<News> newsList;

    private NewsRepo mRepository;
    public MutableLiveData<List<News>> mAllPlaces;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

        if (newsList != null) {
            return;
        }
        // здесь сначала вытаскиваем все данные из хранилища
        mRepository = NewsRepo.getInstance();
        mAllPlaces = mRepository.getNewsfeed();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<News>> getNewsFeed() {
        return mAllPlaces;
    }
}