package com.example.diploma_test.viewmodel;

import com.example.diploma_test.entity.User;
import com.example.diploma_test.repo.UserRepo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
   private UserRepo mRepository;
   public  MutableLiveData<User> user;
   //public MutableLiveData<List<News>> mAllPlaces;

   public LoginViewModel(){
      //
      /*if (newsList != null) {
         return;
      }*/
      // здесь сначала вытаскиваем все данные из хранилища
      mRepository = UserRepo.getInstance();
      //mAllPlaces = mRepository.getNewsfeed();
   }

   public LiveData<User> login (String username, String password) {
      return mRepository.login(username, password);
   };


}