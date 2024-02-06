package com.example.task2list2;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.task2list2.Fav.Controller.FavList;
import com.example.task2list2.database.AppDataBase;
import com.example.task2list2.getAllProducts.Controller.GetAllProduct;


public class MainActivity extends AppCompatActivity {




   Button btnProductList , btnFav;
    private static final String TAG = "MainActivity";
    AppDataBase appDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnProductList = findViewById(R.id.btn_productList);
        btnFav =findViewById(R.id.btn_Fav);

        btnProductList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GetAllProduct.class);
                startActivity(intent);
            }
        });


        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, FavList.class);
                startActivity(intent);
            }
        });
    }


}

