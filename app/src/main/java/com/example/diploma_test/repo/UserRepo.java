package com.example.diploma_test.repo;

import android.app.Application;
import android.service.autofill.UserData;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.api.AppDatabase;
import com.example.diploma_test.api.GitHubRepo;
import com.example.diploma_test.api.GitHubRepoDao;
import com.example.diploma_test.api.RetroInstance;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.pojos.LoginRequest;
import com.example.diploma_test.pojos.LoginResponse;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepo {

    private static UserRepo instance;
    // в MutableLiveData можно пихать List или ArrayList
    private ArrayList<User> dataset;
    //
    private UserDao userDao;
    private TokenDao tokenDao;
    private ApiInterface client;

    private LiveData<List<User>> listUsers;

    public UserRepo(Application application) {
        client = RetroInstance.getRetroClient().create(ApiInterface.class);
        AppDatabase db = AppDatabase.getInstance(application);
        tokenDao = db.getTokenDao();
        userDao = db.getUserDao();

        listUsers = userDao.getAll();


    }

    /*public LiveData<Token> register(User user) {
        MutableLiveData<Token> registerResponse = new MutableLiveData<>();

        Call<Token> call = client.register(user);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
        return registerResponse;
    }*/
    public void getAllUsersFromServer(String token) {
//        if (tokenDao.getToken().getValue() != null) {
            Call<List<User>> call = client.getAllUsers(token);
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    System.out.println("get all users inside callback " + response.body().get(5).getFirstname());
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            userDao.insertAll(response.body());
                        }
                    }).start();

                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {

                }
            });
//        }

    }

    public LiveData<List<User>> getAllUsers() {
        System.out.println("Get All users from Repo");
//        return userDao.getAll();
        return listUsers;
    }

    public void login(LoginRequest loginRequest) {
        //setUsers();
//        MutableLiveData<Token> loginResponse = new MutableLiveData<>();

        Call<Token> call = client.login(loginRequest);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                System.out.println("login "+response);
                new Thread(new Runnable() {
                    public void run() {
                        if (response.body() != null) {
                            tokenDao.insert(response.body());
                            System.out.println("Token inserted in DB");
                        }
//                        Token response1 = response.body();
//                        loginResponse.postValue(response1);
//                        loginResponse.setValue(response1);
                        //System.out.println(response.body());
                    }
                }).start();

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {

            }
        });
        //System.out.println("token" + tokenDao.getToken().get(0).getToken());
//        return loginResponse;
    }

    public LiveData<Token> getToken(){
        System.out.println("Returning now Token from TOKEN DAO");
        return tokenDao.getToken();
    }
    /*public LiveData<User> login(String username, String password){
        // для начала заполним, потом убрать
        //setUsers();

        MutableLiveData<User> currentUser = new MutableLiveData<>();
        // запрос к АПИшке

        //-- для теста --
        currentUser.setValue(dataset.get(1));
        //
        return currentUser;
    }*/

    private void setUsers(){
        dataset = new ArrayList<>();
//        dataset.add(new User(1,"klena92@mail.ru","pass1"));
//        dataset.add(new User(1,"skrapivnoy@yandex.ru","pass2"));
        User user = new User(42,"serejkaa@mail.ru","pass1","Sergey","Krapivnoy");
        Call<Token> call = client.register(user);
        call.timeout();
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                System.out.println("response: "+response);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                //System.out.println("was a failure");

            }
        });

    }
}
