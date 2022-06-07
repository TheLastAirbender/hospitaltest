package com.example.diploma_test.entity;

import java.util.Date;

public class News {
   private String author;
   private Date datetimePosted;
   private String text;
   //private Image img;
   private String imgSource;

   public News(String author, Date datetimePosted, String text, String imgSource) {
      this.author = author;
      this.datetimePosted = datetimePosted;
      this.text = text;
      this.imgSource = imgSource;
   }

   public String getAuthor() {
      return author;
   }

   public String getText() {
      return text;
   }

   public Date getInfoAndDate() {return datetimePosted;}
}
