package com.example.diploma_test.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "message", foreignKeys = @ForeignKey(entity = Channel.class, parentColumns = "channel_id", childColumns = "chat_id"))
public class Message {
    @PrimaryKey
    private long message_id;
    @ColumnInfo(name = "chat_id")
    private int chat_id;
    @ColumnInfo(name = "author_id")
    private int author_id;
    @ColumnInfo(name = "message_text")
    private String text;

    public Message(long message_id, int chat_id, int author_id, String text) {
        this.message_id = message_id;
        this.chat_id = chat_id;
        this.author_id = author_id;
        this.text = text;
    }

    public long getMessage_id() {
        return message_id;
    }

    public int getChat_id() {
        return chat_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public String getText() {
        return text;
    }


}
