package com.example.diploma_test.onlyfortests;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "githubrepo")
public class GitHubRepo {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    public GitHubRepo() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}