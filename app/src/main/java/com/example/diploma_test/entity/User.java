package com.example.diploma_test.entity;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
   @PrimaryKey
   private Integer id;
   @ColumnInfo(name = "username")
   private String username;
   @ColumnInfo(name = "password")
   private String password;
   @SerializedName("firstName")
   @ColumnInfo(name = "firstName")
   private String firstname;
   @SerializedName("lastName")
   @ColumnInfo(name = "lastName")
   private String lastname;

   public User(Integer id, String username, String password, String firstname, String lastname) {
      this.id = id;
      this.username = username;
      this.password = password;
      this.firstname = firstname;
      this.lastname = lastname;
   }

   public Integer getId() {
      return id;
   }

   public String getUsername() {
      return username;
   }

   public String getPassword() {
      return password;
   }

   public String getFirstname() {
      return firstname;
   }

   public String getLastname() {
      return lastname;
   }
}
