package com.example.task2list2.model;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;



@Entity(tableName = "product_table")
public class Products {
   @PrimaryKey
   @NonNull
   private int id;

   public Products(int id, String title, String description, String brand, String url, double price, float rating) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.brand = brand;
      this.url = url;
      this.price = price;
      this.rating = rating;
   }

   @ColumnInfo(name = "title")
   private String title;
   @ColumnInfo(name = "description")
   private String description;
   @ColumnInfo(name = "brand")
   private String brand ;
   @ColumnInfo(name = "image url")
   @SerializedName("thumbnail")
   private String url;
   @ColumnInfo(name = "price")
   private double price;
   @ColumnInfo(name = "rating")
   private float rating;


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;

   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public String getBrand() {
      return brand;
   }

   public void setBrand(String brand) {
      this.brand = brand;
   }

   public float getRating() {
      return rating;
   }

   public void setRating(float rating) {
      this.rating = rating;
   }

   public double getPrice() {
      return price;
   }

   public void setPrice(double price) {
      this.price = price;
   }

   @Override
   public String toString() {
      return "Products{" +
              "id=" + id +
              ", title='" + title + '\'' +
              ", description='" + description + '\'' +
              ", brand='" + brand + '\'' +
              ", url='" + url + '\'' +
              ", price=" + price +
              ", rating=" + rating +
              '}';
   }
}
