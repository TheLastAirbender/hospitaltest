package com.example.diploma_test.repo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.api.AppDatabase;
import com.example.diploma_test.api.RetroInstance;
import com.example.diploma_test.dao.NewsDao;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;

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
    SharedPreferences prefs;

    private LiveData<Token> token;
    private LiveData<List<News>> listNews;

    public NewsRepo(Application application) {
        prefs = application.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        client = RetroInstance.getRetroClient().create(ApiInterface.class);
        AppDatabase db = AppDatabase.getInstance(application);
        newsDao = db.getNewsDao();
        userDao = db.getUserDao();
        tokenDao = db.getTokenDao();
    }

    public void getAllNewsFromServer(){
        String token = prefs.getString("token","");
        System.out.println("Got token from SharedPreferences " + token);
        Call<List<News>> call = client.getAllNews("Bearer "+token);
        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                System.out.println("response from get all news: "+ response.body());
                System.out.println(response.body().get(0).getMessage());
                System.out.println(response.body().get(0).getSenderId());
                System.out.println(response.body().get(0).getTime());
//                System.out.println(response.body().get(0).getText());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        newsDao.insertAll(response.body());
//                        newsDao.insert(response.body().get(0));
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                System.out.println(t);
            }
        });
    };

    public LiveData<List<NewsInNewsfeed>> getAllNewsFromRoom(){
        return newsDao.getAll();
    }

    private void setNews(){
//        dataset = new ArrayList<>();
//        dataset.add(new News("Вася Пупкин",new Date(System.currentTimeMillis()),"POST TEXT","image source"));
//        dataset.add(new News("Андрей Сороковиков",new Date(System.currentTimeMillis()),"POST TEXT 2","image source"));
//        dataset.add(new News("Лёня Петров",new Date(System.currentTimeMillis()),"ТУПО ЗАПОСТИЛ КРИНЖ","image source"));
    }
}
