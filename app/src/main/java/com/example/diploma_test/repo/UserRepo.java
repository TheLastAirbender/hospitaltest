package com.example.diploma_test.repo;

import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.User;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;

public class UserRepo {

    private static UserRepo instance;
    // в MutableLiveData можно пихать List или ArrayList
    private ArrayList<User> dataset;

    public static UserRepo getInstance() {
        if (instance==null) {
            instance=new UserRepo();
        }
        return instance;
    }

    public LiveData<User> login(String username, String password){
        // для начала заполним, потом убрать
        setUsers();

        MutableLiveData<User> currentUser = new MutableLiveData<>();
        // запрос к АПИшке

        //-- для теста --
        currentUser.setValue(dataset.get(1));
        //
        return currentUser;
    }

    private void setUsers(){
        dataset = new ArrayList<>();
        dataset.add(new User(1,"klena92@mail.ru","pass1"));
        dataset.add(new User(1,"skrapivnoy@yandex.ru","pass2"));

    }
}
