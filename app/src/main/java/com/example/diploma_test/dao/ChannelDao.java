package com.example.diploma_test.dao;

import com.example.diploma_test.entity.Channel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ChannelDao {
    @Query("SELECT * FROM chats")
    LiveData<List<Channel>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Channel> listChats);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Channel chat);

    @Update
    void update(Channel chat);

    @Delete
    void delete(Channel chat);
}
