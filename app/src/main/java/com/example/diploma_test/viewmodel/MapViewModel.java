package com.example.diploma_test.viewmodel;

import com.example.diploma_test.models.News;
import com.example.diploma_test.repo.NewsRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MapViewModel() {

//        mAllPlacesArchive = mRepository.getPlacesFromArchive();
//        mAllPlacesToService = mRepository.getPlacesToService();

        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setData(String lol) {
        //mText = new MutableLiveData<>();
        mText.setValue(lol);
    }
}