package com.example.task2list2;

import java.util.ArrayList;

class UpperClassPojo {
   private ArrayList<Products> products;
   private int total, skip,limit;

   public ArrayList<Products> getProducts() {
      return products;
   }

   public void setProducts(ArrayList<Products> products) {
      this.products = products;
   }

   public int getTotal() {
      return total;
   }

   public void setTotal(int total) {
      this.total = total;
   }

   public int getSkip() {
      return skip;
   }

   public void setSkip(int skip) {
      this.skip = skip;
   }

   public int getLimit() {
      return limit;
   }

   public void setLimit(int limit) {
      this.limit = limit;
   }
}
