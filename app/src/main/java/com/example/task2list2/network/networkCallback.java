package com.example.task2list2.network;

import com.example.task2list2.model.Products;

import java.util.ArrayList;

public interface networkCallback {
   public void onSuccessResults(ArrayList<Products> products);
   public void onFailureResult(String msg);
}
