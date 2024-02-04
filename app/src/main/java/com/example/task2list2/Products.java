package com.example.task2list2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

class Products {
   private String title,description,brand ;
   @SerializedName("thumbnail")
   private String url;
   private double price;
   private float rating;


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
              "title='" + title + '\'' +
              ", description='" + description + '\'' +
              ", brand='" + brand + '\'' +
              ", url='" + url + '\'' +
              ", rating=" + rating +
              ", price=" + price +
              '}';
   }

}
