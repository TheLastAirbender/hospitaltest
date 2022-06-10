package com.example.diploma_test.dao;

import com.example.diploma_test.entity.News;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news")
    LiveData<List<News>> getAll();

    @Query("SELECT * FROM news WHERE news_id = :id")
    News getById(long id);

    @Insert
    void insertAll(List<News> news);
    @Insert
    void insert(News news_item);

    @Update
    void update(News news_item);

    @Delete
    void delete(News news_item);
}
