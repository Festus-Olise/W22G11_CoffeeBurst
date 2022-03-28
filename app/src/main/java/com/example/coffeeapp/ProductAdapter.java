package com.example.coffeeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.Viewholder> {

    private Context context;
    private ArrayList<ProductModel> productModelArrayList;

    // Constructor
    public ProductAdapter(Context context, ArrayList<ProductModel> courseModelArrayList) {
        this.context = context;
        this.productModelArrayList = courseModelArrayList;
    }

    @NonNull
    @Override
    public ProductAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        ProductModel model = productModelArrayList.get(position);
        holder.productNameTV.setText(model.getProduct_name());
        holder.productPriceIV.setText("" + model.getProduct_price());
        holder.productIV.setImageResource(model.getProduct_image());
    }

    @Override
    public int getItemCount() {
        return productModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView productIV;
        public TextView productNameTV, productPriceIV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            productIV = itemView.findViewById(R.id.idIVProductImage);
            productNameTV = itemView.findViewById(R.id.idTVProductName);
            productPriceIV = itemView.findViewById(R.id.idTVProductPrice);
        }
    }
}
