package com.example.task2list2.getAllProducts.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.task2list2.Fav.Controller.FavList;
import com.example.task2list2.R;
import com.example.task2list2.getAllProducts.view.GoToInfoScreen;
import com.example.task2list2.network.networkCallback;
import com.example.task2list2.network.productClient;
import com.example.task2list2.getAllProducts.view.AddToFav;
import com.example.task2list2.getAllProducts.view.MyAdapter;
import com.example.task2list2.model.Products;
import com.example.task2list2.model.Repository;

import java.util.ArrayList;

public class GetAllProduct extends AppCompatActivity implements networkCallback , AddToFav , GoToInfoScreen {
    private static final String TAG = "GetAllProduct";

    RecyclerView recyclerView ;
    StaggeredGridLayoutManager layoutManager;
    MyAdapter myAdapter;
    productClient client;
    Button btnSave;

    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_product);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        repository = Repository.getInstance(this);

        client = productClient.getInstance();
        client.makeNetworkCall(this);
    }

    @Override
    public void onSuccessResults(ArrayList<Products> products) {
        myAdapter = new MyAdapter(this,products,this,this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onFailureResult(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFavProductClick(Products products) {
        repository.insert(products);
    }

    @Override
    public void navigateToInfo(Products products) {
        Intent intent = new Intent(GetAllProduct.this , FavList.class);
        startActivity(intent);
    }
}