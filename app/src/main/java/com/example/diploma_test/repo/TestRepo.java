package com.example.diploma_test.repo;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TestRepo {

    private static TestRepo instance;
    private ArrayList<Integer> dataset = new ArrayList<>();

    public static TestRepo getInstance() {
        if (instance==null) {
            instance=new TestRepo();
        }
        return instance;
    }

    public MutableLiveData<List<Integer>> getNumbers(){
        setNumbers();

        MutableLiveData<List<Integer>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    };

    private void setNumbers(){
        dataset.add(2);
        dataset.add(4);
        dataset.add(1);
        dataset.add(6);
        dataset.add(2);
        dataset.add(26);
        dataset.add(234);
        dataset.add(2);

    }
}
