package com.example.diploma_test.dao;

import com.example.diploma_test.entity.Token;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TokenDao {
    @Query("SELECT * FROM token_table")
    LiveData<Token> getToken();

    @Query("SELECT * FROM token_table LIMIT 1")
    Token getTokenInstantsly();

    @Insert
    void insert(Token token);
//
//    @Update
//    void update(User user);
//
//    @Delete
//    void delete(User user);
}
