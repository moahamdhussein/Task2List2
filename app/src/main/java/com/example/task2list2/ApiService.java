package com.example.task2list2;

import retrofit2.Call;
import retrofit2.http.GET;

interface ApiService {

    @GET("products")
    Call<UpperClassPojo> getProducts();

}
