package com.example.diploma_test.entity;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnegative;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "token_table")
public class Token {
    @NonNull
    @PrimaryKey
//    @SerializedName("token")
    public String token;
//    @SerializedName("tokenType")
    @ColumnInfo(name = "tokenType")
    public String tokenType;

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }
}
