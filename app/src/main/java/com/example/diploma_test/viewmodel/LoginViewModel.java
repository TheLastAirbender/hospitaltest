package com.example.diploma_test.viewmodel;

import android.app.Application;

import com.example.diploma_test.entity.Token;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.utility_pojos.LoginRequest;
import com.example.diploma_test.repo.UserRepo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginViewModel extends AndroidViewModel {
   private UserRepo loginRepository;
   public  MutableLiveData<User> user;
   //public MutableLiveData<List<News>> mAllPlaces;
//   public final LiveData<Boolean> login;
   public final LiveData<Token> token;
   public final LiveData<List<User>> users;


   public LoginViewModel(@NonNull Application application){
      super(application);
      //
      /*if (newsList != null) {
         return;
      }*/
      // здесь сначала вытаскиваем все данные из хранилища
      loginRepository = new UserRepo(application);
      token = loginRepository.getToken();
      users = loginRepository.getAllUsers();
      //mAllPlaces = mRepository.getNewsfeed();
   }

   public void getUsersRequestToRepo(String token){
      loginRepository.getAllUsersFromServer(token);
   }

   public LiveData<List<User>> observableUsersList(){
      //return loginRepository.getAllUsers();
      return users;
   }

   public void login (String username, String password) {
//      MutableLiveData<Boolean> returnable = new MutableLiveData();

      LoginRequest loginRequest = new LoginRequest(username,password);
      loginRepository.login(loginRequest);
//      if (response != null ) {
//         returnable.setValue(true);
//      }
//      System.out.println(response);
      //return loginRepository.login(loginRequest).getValue();
//      System.out.println("preparing to return bool login from ViewModel");
//      return returnable;
   };

   public LiveData<Token> getToken(){
//      MutableLiveData<Token> tokenMutableLiveData = new MutableLiveData<>();
      return loginRepository.getToken();
//      return tokenMutableLiveData.setValue(token);
   }

}