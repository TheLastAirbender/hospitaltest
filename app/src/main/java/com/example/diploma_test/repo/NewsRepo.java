package com.example.diploma_test.repo;

import com.example.diploma_test.models.News;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class NewsRepo {

    private static NewsRepo instance;
    // в MutableLiveData можно пихать List или ArrayList
    private ArrayList<News> dataset;

    public static NewsRepo getInstance() {
        if (instance==null) {
            instance=new NewsRepo();
        }
        return instance;
    }

    public MutableLiveData<List<News>> getNewsfeed(){
        setNews();

        MutableLiveData<List<News>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    };

    private void setNews(){
        dataset = new ArrayList<>();
        dataset.add(new News("header","news text","image source"));
        dataset.add(new News("2 header","2 news text","2 image source"));
        dataset.add(new News("3 header","4 news text","3 image source"));

    }
}
