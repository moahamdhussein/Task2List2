package com.example.task2list2.Fav.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task2list2.R;
import com.example.task2list2.getAllProducts.view.AddToFav;
import com.example.task2list2.model.Products;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
   private Context context;
   private List<Products> products;
   private static final String TAG = "MyAdapter";
   private onRemoveClick listener;
   public MyAdapter(Context context, ArrayList<Products> products, onRemoveClick listener) {
      this.context = context;
      this.products = products;
      this.listener = listener;
   }

   public void setList(List<Products> products){
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
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View v = inflater.inflate(R.layout.list_item_fav, parent, false);
      ViewHolder vh = new ViewHolder(v);
      Log.i(TAG, "onCreateViewHolder: ");
      return vh;
   }


   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

      holder.tvTitle.setText(products.get(position).getTitle());
      holder.tvDescription.setText(products.get(position).getDescription());
      holder.tvPrice.setText(products.get(position).getPrice() + "");
      holder.tvBrand.setText(products.get(position).getBrand());
      Glide.with(context).load(products.get(position).getUrl()).override(700, 700).into(holder.imageView);
      holder.btnSave.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            listener.onRemoveClicked(products.get(holder.getAdapterPosition()));
         }
      });
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
      public Button btnSave;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         layout = itemView;
         tvTitle = itemView.findViewById(R.id.tvTitle);
         tvPrice = itemView.findViewById(R.id.tvPrice);
         tvDescription = itemView.findViewById(R.id.tvDescription);
         tvBrand = itemView.findViewById(R.id.tvBrand);
         imageView = itemView.findViewById(R.id.imageView);
         btnSave = itemView.findViewById(R.id.btn_remove);
         Log.i(TAG, "ViewHolder: ");
      }
   }
}
