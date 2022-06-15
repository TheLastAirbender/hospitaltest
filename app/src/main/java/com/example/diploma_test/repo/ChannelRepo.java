package com.example.diploma_test.repo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.api.RetroInstance;
import com.example.diploma_test.dao.ChannelDao;
import com.example.diploma_test.dao.NewsDao;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.db.AppDatabase;
import com.example.diploma_test.entity.Channel;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.Token;

import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChannelRepo {

    private UserDao userDao;
    private ChannelDao channelDao;
    private ApiInterface client;
    SharedPreferences prefs;

    private LiveData<Token> token;
    private LiveData<List<News>> listNews;

    public ChannelRepo(Application application) {
        prefs = application.getSharedPreferences("com.example.app", Context.MODE_PRIVATE);
        client = RetroInstance.getRetroClient().create(ApiInterface.class);
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.getUserDao();
        channelDao = db.getChannelDao();
    }

    public void getAllChatsFromServer(){
        String token = prefs.getString("token","");
        Call<List<Channel>> call = client.getAllChannels("Bearer "+token);
        call.enqueue(new Callback<List<Channel>>() {
            @Override
            public void onResponse(Call<List<Channel>> call, Response<List<Channel>> response) {
                System.out.println("response from get all Channels: "+ response.body());
                System.out.println(response.body().get(0).getName());
//                System.out.println(response.body().get(0).getText());

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        channelDao.insertAll(response.body());
//                        newsDao.insert(response.body().get(0));
                    }
                }).start();
            }

            @Override
            public void onFailure(Call<List<Channel>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public LiveData<List<Channel>> getAllChannelsFromRoom(){
        return channelDao.getAll();
    }

    //TODO доделать функции создания, удаления чатов
    public void createNewChannel(){}
}
