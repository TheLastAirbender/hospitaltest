package com.example.diploma_test.entity;

import java.util.Date;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "news")
public class News {
   @PrimaryKey
   private int news_id;
   @ColumnInfo(name = "news_author")
   private String author;
   @ColumnInfo(name = "news_datetimePosted")
   private Date datetimePosted;
   @ColumnInfo(name = "news_text")
   private String text;
   //private Image img;
   @ColumnInfo(name = "news_imgSource")
   private String imgSource;

   public News(String author, Date datetimePosted, String text, String imgSource) {
      this.author = author;
      this.datetimePosted = datetimePosted;
      this.text = text;
      this.imgSource = imgSource;
   }

   public void setNews_id(int news_id) {
      this.news_id = news_id;
   }

   public int getNews_id() {
      return news_id;
   }

   public Date getDatetimePosted() {
      return datetimePosted;
   }

   public String getAuthor() {
      return author;
   }

   public String getText() {
      return text;
   }

   public String getImgSource() {
      return imgSource;
   }
}
