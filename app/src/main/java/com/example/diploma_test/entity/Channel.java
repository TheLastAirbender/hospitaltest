package com.example.diploma_test.entity;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "channel")
public class Channel {
    @PrimaryKey
    private  int channel_id;
    @ColumnInfo(name = "channel_name")
    private String name;
    @ColumnInfo(name = "creator_id")
    private int creator_id;
    @ColumnInfo(name="members")
    private int[] members;
}
