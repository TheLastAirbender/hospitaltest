package com.example.diploma_test.api;

import android.app.Application;
import android.content.Context;

import com.example.diploma_test.MainActivity;
import com.example.diploma_test.dao.NewsDao;
import com.example.diploma_test.dao.TokenDao;
import com.example.diploma_test.dao.UserDao;
import com.example.diploma_test.entity.News;
import com.example.diploma_test.entity.User;
import com.example.diploma_test.repo.TestRepo;
import com.example.diploma_test.entity.Token;
import com.example.diploma_test.db.Converters;

import java.util.Date;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


@Database(entities = {GitHubRepo.class, User.class, Token.class, News.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
   private static AppDatabase instance;

   // Я ТАК ПОНИМАЮ, ТУТ ТОЖЕ СИНГЛТОН БУДЕТ
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

}