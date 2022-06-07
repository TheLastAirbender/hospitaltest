package com.example.diploma_test.repo;

import com.example.diploma_test.entity.News;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
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
        dataset.add(new News("Вася Пупкин",new Date(System.currentTimeMillis()),"POST TEXT","image source"));
        dataset.add(new News("Андрей Сороковиков",new Date(System.currentTimeMillis()),"POST TEXT 2","image source"));
        dataset.add(new News("Лёня Петров",new Date(System.currentTimeMillis()),"ТУПО ЗАПОСТИЛ КРИНЖ","image source"));

    }
}
