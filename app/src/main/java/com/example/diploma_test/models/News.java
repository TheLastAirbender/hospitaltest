package com.example.diploma_test.models;

import android.media.Image;

public class News {
   private String heading;
   private String text;
   //private Image img;
   private String imgSource;

   public News(String heading, String text, String imgSource) {
      this.heading = heading;
      this.text = text;
      this.imgSource = imgSource;
   }

   public String getHeader() {
      return heading;
   }

   public String getText() {
      return text;
   }
}
