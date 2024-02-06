package com.example.task2list2.model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.task2list2.database.AppDataBase;
import com.example.task2list2.database.ProductDAO;

import java.util.List;

public class Repository {

   private Context context;
   private ProductDAO dao;

   private LiveData<List<Products>> data;
   private   static Repository repository=null;

   private Repository(Context context){
      this.context = context;
      AppDataBase db = AppDataBase.getInstance(context.getApplicationContext());
      dao = db.getProductDAO();
      data = dao.getAllProduct();
   }
   public static Repository getInstance(Context context){
      if (repository==null){
         repository = new Repository(context);
      }

      return repository;
   }

   public LiveData<List<Products>> getStoredData(){
      return data;
   }

   public void delete(Products products){
      new Thread(() -> {
         dao.deleteProduct(products);
      }).start();
   }

   public void insert(Products products){
      new Thread(() -> {
         dao.insertProduct(products);
      }).start();
   }
}
