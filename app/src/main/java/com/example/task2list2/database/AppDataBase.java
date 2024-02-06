package com.example.task2list2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.task2list2.model.Products;

@Database(entities = {Products.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
   private static AppDataBase instance = null;
   public abstract ProductDAO getProductDAO();

   public static synchronized AppDataBase getInstance(Context context){
       if (instance ==null){
           instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "ProductDb").build();

       }
       return instance;
   }

}
