package com.example.diploma_test.api;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface GitHubRepoDao {

    @Query("SELECT * FROM githubrepo")
    LiveData<List<GitHubRepo>> getAll();

    @Insert
    void insertAll(List<GitHubRepo> repos);

    @Query("DELETE FROM githubrepo")
    void deleteAll();
}
