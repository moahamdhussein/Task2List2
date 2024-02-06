package com.example.task2list2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.task2list2.model.Products;

import java.util.List;

@Dao
public interface ProductDAO {
 @Query("SELECT * FROM product_table")
 LiveData<List<Products> > getAllProduct();

 @Insert(onConflict = OnConflictStrategy.IGNORE)
 void insertProduct(Products products);

 @Delete
 void deleteProduct(Products products);

}
