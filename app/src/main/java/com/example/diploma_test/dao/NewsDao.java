package com.example.diploma_test.dao;

import com.example.diploma_test.entity.News;
import com.example.diploma_test.utility_pojos.NewsInNewsfeed;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NewsDao {
//    @Query("SELECT * FROM news")
//    LiveData<List<News>> getAll();

    @Query("SELECT * FROM news WHERE id = :id")
    News getById(long id);

    // Поиск новостей по вхождению в название паблика или текста новости
    //@Query("SELECT * FROM news WHERE ")

    //Нормальный поиск новостей, с джоинами
    @Query("SELECT (user.firstname || ' ' || user.lastName) as author, news.time as datetimeposted, news.message FROM news INNER JOIN user on news.senderId = user.id")
    LiveData<List<NewsInNewsfeed>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<News> news);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(News news_item);

    @Update
    void update(News news_item);

    @Delete
    void delete(News news_item);
}
