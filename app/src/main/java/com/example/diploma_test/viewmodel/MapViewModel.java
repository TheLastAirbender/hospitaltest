package com.example.diploma_test.viewmodel;

import android.app.Application;
import android.content.Context;

import com.example.diploma_test.entity.CabinetMissing;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MapViewModel extends AndroidViewModel {
    private MutableLiveData<CabinetMissing> currentRoom = new MutableLiveData<>();
    private static MapViewModel instance;

    private MutableLiveData<String> mText;

    public static MapViewModel getInstance(Application application) {
        if (instance == null) {
            instance = new MapViewModel(application);
        }
        return instance;
    }

    public MapViewModel(@NonNull Application application) {
        super(application);


//        mAllPlacesArchive = mRepository.getPlacesFromArchive();
//        mAllPlacesToService = mRepository.getPlacesToService();

        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
//        currentRoom = new MutableLiveData<>();
//        currentRoom.setValue(new CabinetMissing("NOT FOUND"));

//        Mapbox.getInstance(Mapbox.getApplicationContext(),"pk.eyJ1Ijoic2tyYXBpdm5veSIsImEiOiJjbDQzdzBpNHMwN21mM2JvZDVycHg0MjI4In0.EVAin0I89fnCOHGqnckCjg");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setData(String lol) {
        //mText = new MutableLiveData<>();
        mText.setValue(lol);
    }

    public MutableLiveData<CabinetMissing> getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(CabinetMissing room) {
        currentRoom.setValue(room);
    }
}