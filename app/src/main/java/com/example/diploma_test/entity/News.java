package com.example.diploma_test.entity;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {
   @PrimaryKey
   private int id;
   @ColumnInfo(name = "news_author")
   private String senderId;
   @ColumnInfo(name = "news_datetimePosted")
   private Date time;
   @ColumnInfo(name = "news_text")
   private String message;
   //private Image img;
   @ColumnInfo(name = "news_imgSource")
   private String departmentId;

   public News(int id, String senderId, Date time, String message, String departmentId) {
      this.id = id;
      this.senderId = senderId;
      this.time = time;
      this.message = message;
      this.departmentId = departmentId;
   }

   public int getId() {
      return id;
   }

   public String getSenderId() {
      return senderId;
   }

   public Date getTime() {
      return time;
   }

   public String getMessage() {
      return message;
   }

   public String getDepartmentId() {
      return departmentId;
   }
}
