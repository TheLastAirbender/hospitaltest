package com.example.diploma_test.dao;

import com.example.diploma_test.entity.User;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.pojos.LoginResponse;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TokenDao {
    @Query("SELECT * FROM token_table LIMIT 1")
    LiveData<Token> getToken();

    @Insert
    void insert(Token token);
//
//    @Update
//    void update(User user);
//
//    @Delete
//    void delete(User user);
}
