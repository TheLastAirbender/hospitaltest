package com.example.diploma_test.utility_pojos;

public class NewsInNewsfeed {
   String author;
   String datetimeposted;
   String message;

   public NewsInNewsfeed(String author, String datetimeposted, String message) {
      this.author = author;
      this.datetimeposted = datetimeposted;
      this.message = message;
   }

   public String getAuthor() {
      return author;
   }

   public String getDatetimeposted() {
      return datetimeposted;
   }

   public String getMessage() {
      return message;
   }
}
