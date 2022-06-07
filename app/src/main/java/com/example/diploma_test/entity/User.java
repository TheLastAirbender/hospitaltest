package com.example.diploma_test.entity;

public class User {
   private Integer userId;
   private String username;
   private String password;

   public User(Integer userId, String username, String password) {
      this.userId = userId;
      this.username = username;
      this.password = password;
   }

   public String getUsername() {
      return username;
   }
}
