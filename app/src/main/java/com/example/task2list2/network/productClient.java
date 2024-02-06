package com.example.task2list2.network;


import com.example.task2list2.model.UpperClassPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class productClient {
    private static final String BASE_URL = "https://dummyjson.com/";
    private ApiService service;

    private static productClient client = null;

    private productClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static productClient getInstance() {
        if (client == null) {
            client = new productClient();
        }
        return client;
    }

    public void makeNetworkCall(networkCallback callback) {
        Call<UpperClassPojo> call = service.getProducts();
        call.enqueue(new Callback<UpperClassPojo>() {
            @Override
            public void onResponse(Call<UpperClassPojo> call, Response<UpperClassPojo> response) {
                callback.onSuccessResults(response.body().getProducts());
            }
            @Override
            public void onFailure(Call<UpperClassPojo> call, Throwable t) {
                callback.onFailureResult(t.getMessage());
                t.printStackTrace();
            }
        });
    }
}
