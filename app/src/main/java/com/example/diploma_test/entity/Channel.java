package com.example.diploma_test.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "chats")
public class Channel {
    @PrimaryKey
    @SerializedName("id")
    private  int channel_id;
    @SerializedName("created_time")
    @ColumnInfo(name="created_time")
    private String created_time;
    @SerializedName("name")
    @ColumnInfo(name = "channel_name")
    private String name;
    @SerializedName("creator_id")
    @ColumnInfo(name = "creator_id")
    private int creator_id;
//    @ColumnInfo(name="members")
//    private int[] members;


    public Channel(int channel_id, String created_time, String name, int creator_id) {
        this.channel_id = channel_id;
        this.created_time = created_time;
        this.name = name;
        this.creator_id = creator_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getName() {
        return name;
    }

    public int getCreator_id() {
        return creator_id;
    }
}
