package com.example.diploma_test.dao;

import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.User;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> listUsers);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
