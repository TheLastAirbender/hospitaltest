package com.example.diploma_test.entity;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {
   @PrimaryKey
   private int id;
//   @ColumnInfo(name = "news_author")
  @ColumnInfo(name = "senderId")
   private String senderId;
//   @ColumnInfo(name = "news_datetimePosted")
   @ColumnInfo(name="time")
//   private Date time;
   private String time;
//   @ColumnInfo(name = "news_text")
   @ColumnInfo(name = "message")
   private String message;
   //private Image img;
//   @ColumnInfo(name = "news_imgSource")
   @ColumnInfo(name = "departmentId")
   private int departmentId;

   public News(int id, String senderId, String time, String message, int departmentId) {
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

   public String getTime() {
      return time;
   }

   public String getMessage() {
      return message;
   }

   public int getDepartmentId() {
      return departmentId;
   }
}
