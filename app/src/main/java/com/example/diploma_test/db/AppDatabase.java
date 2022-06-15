package com.example.diploma_test.db;

import android.content.Context;

import com.example.diploma_test.dao.ChannelDao;
import com.example.diploma_test.dao.NewsDao;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.entity.Channel;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.onlyfortests.GitHubRepo;
import com.example.diploma_test.onlyfortests.GitHubRepoDao;
import com.example.diploma_test.entity.Token;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {GitHubRepo.class, User.class, Token.class, News.class, Channel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
   private static AppDatabase instance;

   public static AppDatabase getInstance(Context context) {
      if (instance == null) {
         System.out.println("APPDATABASE INSTANCE IS NULL CREATING ONE");
         instance= Room.databaseBuilder(context.getApplicationContext(),
                 AppDatabase.class, "database-name").build();
      }
      return instance;
   }

   public abstract GitHubRepoDao gitHubRepoDao();
   public abstract UserDao getUserDao();
   public abstract TokenDao getTokenDao();
   public abstract NewsDao getNewsDao();
   public abstract ChannelDao getChannelDao();
}