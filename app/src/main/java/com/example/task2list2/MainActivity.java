package com.example.task2list2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    ArrayList<Products> productsList;
    public static final String BASE_URL = "https://dummyjson.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getProducts().enqueue(new Callback<UpperClassPojo>() {
            @Override
            public void onResponse(Call<UpperClassPojo> call, Response<UpperClassPojo> response) {
                if (response.body()!=null) {
                    MyAdapter myAdapter = new MyAdapter(getApplicationContext(), response.body().getProducts());
                    recyclerView.setAdapter(myAdapter);
                }

            }

            @Override
            public void onFailure(Call<UpperClassPojo> call, Throwable t) {

            }
        });




//        handler = new Handler() {
//            @Override
//            public void handleMessage(@NonNull Message msg) {
//                super.handleMessage(msg);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//                layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//                recyclerView.setLayoutManager(layoutManager);
//                MyAdapter myAdapter  = new MyAdapter(getApplicationContext(),productsList);
//                recyclerView.setAdapter(myAdapter);
//                myAdapter.notifyDataSetChanged();
//            }
//        };
//        new Thread(new Thread(() -> {
//            String jsonStr = new HttpHandler().makeServiceCall(reqUrl);
//            if (jsonStr != null) {
//                try {
//                    JSONObject jsonObject = new JSONObject(jsonStr);
//                    JSONArray contacts = jsonObject.getJSONArray("products");
//                    for (int i = 0; i < contacts.length(); i++) {
//                        JSONObject c = contacts.getJSONObject(i);
//                        String title = c.getString("title");
//                        String description = c.getString("description");
//                        String price = c.getString("price");
//                        String rating = c.getString("rating");
//                        String brand = c.getString("brand");
//                        String imageUrl = c.getString("thumbnail");
//                        Products products = new Products();
//                        products.setTitle(title);
//                        products.setDescription(description);
//                        products.setPrice(Double.parseDouble(price));
//                        products.setRating(Float.parseFloat(rating));
//                        products.setBrand(brand);
//                        products.setUrl(imageUrl);
//
//                        productsList.add(products);
//                    }
//                    Log.i("Test", productsList.get(29).getTitle());
//                    handler.sendEmptyMessage(0);
//                } catch (JSONException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        })).start();
    }

    //    public class HttpHandler {
//
//        private final String TAG = HttpHandler.class.getSimpleName();
//
//        public HttpHandler() {
//        }
//
//        public String makeServiceCall(String reqUrl) {
//            String response = null;
//            try {
//                URL url = new URL(reqUrl);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//                // read the response
//                InputStream in = new BufferedInputStream(conn.getInputStream());
//                response = convertStreamToString(in);
//            } catch (MalformedURLException e) {
//                Log.e(TAG, "MalformedURLException: " + e.getMessage());
//            } catch (ProtocolException e) {
//                Log.e(TAG, "ProtocolException: " + e.getMessage());
//            } catch (IOException e) {
//                Log.e(TAG, "IOException: " + e.getMessage());
//            } catch (Exception e) {
//                Log.e(TAG, "Exception: " + e.getMessage());
//            }
//            return response;
//        }
//
//        private String convertStreamToString(InputStream is) {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//            StringBuilder sb = new StringBuilder();
//
//            String line;
//            try {
//                while ((line = reader.readLine()) != null) {
//                    sb.append(line).append('\n');
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    is.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            return sb.toString();
//        }
//    }
//
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Context context;
        private ArrayList<Products> products;
        private static final String TAG = "MyAdapter";

        public MyAdapter(Context context, ArrayList<Products> products) {
            this.context = context;
            this.products = products;
        }

        public void updateData(ArrayList<Products> viewModels) {
            products.clear();
            products.addAll(viewModels);
            notifyDataSetChanged();
        }

        public void addItem(int position, Products viewModel) {
            products.add(position, viewModel);
            notifyItemInserted(position);
        }

        public void removeItem(int position) {
            products.remove(position);
            notifyItemRemoved(position);
        }

        @NonNull
        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.list_item, parent, false);
            ViewHolder vh = new ViewHolder(v);
            Log.i(TAG, "onCreateViewHolder: ");
            return vh;
        }


        @Override
        public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {

            holder.tvTitle.setText(products.get(position).getTitle());
            holder.tvDescription.setText(products.get(position).getDescription());
            holder.tvPrice.setText(products.get(position).getPrice() + "");
            holder.tvBrand.setText(products.get(position).getBrand());
            Glide.with(context).load(products.get(position).getUrl()).override(700, 700).into(holder.imageView);
            Log.i(TAG, "onBindViewHolder: " + position);
        }

        @Override
        public int getItemCount() {
            return products.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView imageView;
            public TextView tvTitle, tvPrice, tvBrand, tvDescription;
            public View layout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView;
                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvDescription = itemView.findViewById(R.id.tvDescription);
                tvBrand = itemView.findViewById(R.id.tvBrand);
                imageView = itemView.findViewById(R.id.imageView);
                Log.i(TAG, "ViewHolder: ");
            }
        }
    }

}

