package com.example.diploma_test.onlyfortests;

import android.app.Application;
import android.content.Context;

import com.example.diploma_test.api.ApiInterface;
import com.example.diploma_test.db.AppDatabase;
import com.example.diploma_test.api.RetroInstance;
import com.example.diploma_test.entity.User;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepoTest {

   private static GithubRepoTest instance;
   // в MutableLiveData можно пихать List или ArrayList
   private ArrayList<User> dataset;
   private GitHubRepoDao gitHubRepoDao;
   private ApiInterface client;
   private MutableLiveData<List<GitHubRepo>> repos;

   public GithubRepoTest(Application application) {
       client = RetroInstance.getRetroClient().create(ApiInterface.class);
       AppDatabase db = AppDatabase.getInstance(application);
       gitHubRepoDao = db.gitHubRepoDao();
   }


   public void makeApiCall(Context context){
           // Create a very simple REST adapter which points the GitHub API endpoint.

           Call<List<GitHubRepo>> call =
                   client.reposForUser("TheLastAirbender");

// Execute the call asynchronously. Get a positive or negative callback.
           call.enqueue(new Callback<List<GitHubRepo>>() {
              @Override
              public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                 // The network call was a success and we got a response
                 // TDO: use the repository list and display it
                 List<GitHubRepo> list = response.body();
                 System.out.println(list.get(4).getName());
                 //repos.setValue(list);
                  new Thread(new Runnable() {
                    public void run() {
                       gitHubRepoDao.deleteAll();
                       gitHubRepoDao.insertAll(list);
                       System.out.println("INSIDE THREAD"+gitHubRepoDao.getAll().getValue());
                    }
                 }).start();
              }

              @Override
              public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                 // the network call was a failure
                 // TDO: handle error
                 System.out.println("Error calling API");
              }
           });
        }

   public LiveData<List<GitHubRepo>> getRepos (Context context) {
//      makeApiCall(context);

      return gitHubRepoDao.getAll();
   }

}
