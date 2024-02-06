package com.example.task2list2.Fav.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.example.task2list2.Fav.view.onRemoveClick;
import com.example.task2list2.R;
import com.example.task2list2.Fav.view.MyAdapter;
import com.example.task2list2.model.Products;
import com.example.task2list2.model.Repository;

import java.util.ArrayList;
import java.util.List;

public class FavList extends AppCompatActivity implements onRemoveClick {
    RecyclerView recyclerView ;
    LinearLayoutManager layoutManager;
    MyAdapter myAdapter;

    Repository repository ;
    private static final String TAG = "FavList";
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fav_list);
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        repository = Repository.getInstance(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);



        LiveData<List<Products>> data = repository.getStoredData();
        myAdapter = new MyAdapter(this,new ArrayList<>(),this);
        recyclerView.setAdapter(myAdapter);
        data.observe(this, new Observer<List<Products>>() {
            @Override
            public void onChanged(List<Products> products) {

                myAdapter.setList(products);
                myAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onRemoveClicked(Products products) {
        repository.delete(products);
    }
}