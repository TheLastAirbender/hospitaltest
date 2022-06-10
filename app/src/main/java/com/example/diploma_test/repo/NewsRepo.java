package com.example.diploma_test.repo;

import android.app.Application;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.api.AppDatabase;
import com.example.diploma_test.api.RetroInstance;
import com.example.diploma_test.dao.NewsDao;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsRepo {

//    private static NewsRepo instance;
//    // в MutableLiveData можно пихать List или ArrayList
//    private ArrayList<News> dataset;
    private UserDao userDao;
    private NewsDao newsDao;
    private TokenDao tokenDao;
    private ApiInterface client;

    private LiveData<List<News>> listNews;

    public NewsRepo(Application application) {
        client = RetroInstance.getRetroClient().create(ApiInterface.class);
        AppDatabase db = AppDatabase.getInstance(application);
        newsDao = db.getNewsDao();
        userDao = db.getUserDao();
        tokenDao = db.getTokenDao();

        listNews = newsDao.getAll();


    }

    public void getAllNewsFromServer(){
        Call<List<News>> call = client.getAllNews(tokenDao.getToken().getValue().getToken());
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                System.out.println("response from get all news: "+ response);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newsDao.insertAll(response.body());
                    }
                });
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {

            }
        });

    };

    public LiveData<List<News>> getAllNewsFromRoom(){
        return newsDao.getAll();
    }

    private void setNews(){
//        dataset = new ArrayList<>();
//        dataset.add(new News("Вася Пупкин",new Date(System.currentTimeMillis()),"POST TEXT","image source"));
//        dataset.add(new News("Андрей Сороковиков",new Date(System.currentTimeMillis()),"POST TEXT 2","image source"));
//        dataset.add(new News("Лёня Петров",new Date(System.currentTimeMillis()),"ТУПО ЗАПОСТИЛ КРИНЖ","image source"));
    }
}
