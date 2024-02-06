package com.example.task2list2.getAllProducts.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2list2.model.Products;
import com.example.task2list2.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private final Context context;
   private ArrayList<Products> products;
   private static final String TAG = "MyAdapter";
   private AddToFav listener;
   private GoToInfoScreen goToInfoScreen;
   public MyAdapter(Context context, ArrayList<Products> products,AddToFav listener,GoToInfoScreen goToInfoScreen ) {
      this.context = context;
      this.products = products;
      this.listener = listener;
      this.goToInfoScreen = goToInfoScreen;
   }

   public void setList(ArrayList<Products> products){
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
      holder.tvPrice.setText(String.valueOf(products.get(position).getPrice()));
      holder.tvBrand.setText(products.get(position).getBrand());
      Glide.with(context).load(products.get(position).getUrl()).override(700, 700).into(holder.imageView);
      holder.btnSave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            listener.onFavProductClick(products.get(holder.getAdapterPosition()));
         }
      });

      holder.layout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
goToInfoScreen.navigateToInfo(products.get(holder.getAdapterPosition()));         }
      });

      Log.i(TAG, "onBindViewHolder: " + position);
   }

   @Override
   public int getItemCount() {
      return products.size();
   }

   class ViewHolder extends RecyclerView.ViewHolder {
      public ImageView imageView;
      public TextView tvTitle, tvPrice, tvBrand;
      public View layout;


      public Button btnSave;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         layout = itemView;
         tvTitle = itemView.findViewById(R.id.tvTitle);
         tvPrice = itemView.findViewById(R.id.tvPrice);
         tvBrand = itemView.findViewById(R.id.tvBrand);
         imageView = itemView.findViewById(R.id.imageView);
         btnSave = itemView.findViewById(R.id.btn_save);
         Log.i(TAG, "ViewHolder: ");
      }
   }
}
