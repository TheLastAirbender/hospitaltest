package com.example.diploma_test.entity;

public class MessageForTest {
   String author;
   String text;
   String time;

   public MessageForTest(String author, String text, String time) {
      this.author = author;
      this.text = text;
      this.time = time;
   }

   public String getAuthor() {
      return author;
   }

   public String getText() {
      return text;
   }

   public String getTime() {
      return time;
   }
}
