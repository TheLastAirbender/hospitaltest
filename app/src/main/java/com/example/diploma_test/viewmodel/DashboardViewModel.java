package com.example.diploma_test.viewmodel;

import android.app.Application;

import com.example.diploma_test.onlyfortests.GitHubRepo;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.onlyfortests.GithubRepoTest;
import com.example.diploma_test.repo.NewsRepo;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class DashboardViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<News> newsList;

    private NewsRepo newsRepo;
    public LiveData<List<News>> mAllNews;

    //test
    private LiveData<List<GitHubRepo>> gitRepos;

    // test
    private GithubRepoTest gitRepo;

    public DashboardViewModel(@NonNull Application application) {
        super(application);
//
//        if (newsList != null) {
//            return;
//        }
        // здесь сначала вытаскиваем все данные из хранилища
        newsRepo = new NewsRepo(application);
        //mAllNews = newsRepo.getAllNewsFromRoom();

//        mAllPlaces = mRepository.getNewsfeed();

//        gitRepo = new GithubRepoTest(application);
//        gitRepos = gitRepo.getRepos(application);
    }

    public LiveData<List<GitHubRepo>> getText() {
        if (gitRepos==null) {
            gitRepos = gitRepo.getRepos(getApplication());
        }
        return gitRepos;
    }

    public LiveData<List<NewsInNewsfeed>> observableListOfAllNews() {
        return newsRepo.getAllNewsFromRoom();
    }

    public void requestForAllNews() {
        newsRepo.getAllNewsFromServer();
    }

//    public void getRepos() {
//        gitRepos =  gitRepo.getRepos();
//    }
}