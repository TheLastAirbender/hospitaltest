package com.example.diploma_test.viewmodel;

import android.app.Application;

import com.example.diploma_test.R;
import com.mapbox.mapboxsdk.Mapbox;

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

        Mapbox.getInstance(Mapbox.getApplicationContext(),"pk.eyJ1Ijoic2tyYXBpdm5veSIsImEiOiJjbDQzdzBpNHMwN21mM2JvZDVycHg0MjI4In0.EVAin0I89fnCOHGqnckCjg");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setData(String lol) {
        //mText = new MutableLiveData<>();
        mText.setValue(lol);
    }
}