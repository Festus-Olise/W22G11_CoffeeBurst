package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView coffeeRecyclerView;
    private RecyclerView teaRecyclerView;

    // Arraylist for storing data
    private ArrayList<ProductModel> coffeeModelArrayList;
    private ArrayList<ProductModel> teaModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        coffeeRecyclerView = findViewById(R.id.idRVCoffee);
        teaRecyclerView = findViewById(R.id.idRVTea);

        // here we have created new array list and added data to it.
        coffeeModelArrayList = new ArrayList<>();
        coffeeModelArrayList.add(new ProductModel("Cappuchino", 4, R.drawable.cappuchino));
        coffeeModelArrayList.add(new ProductModel("Americano", 3, R.drawable.americano));
        coffeeModelArrayList.add(new ProductModel("Latte", 4, R.drawable.latte));
        coffeeModelArrayList.add(new ProductModel("Mocca", 4, R.drawable.mocca));

        teaModelArrayList = new ArrayList<>();
        teaModelArrayList.add(new ProductModel("Green Tea", 4, R.drawable.greantea));
        teaModelArrayList.add(new ProductModel("Matcha Latte", 4, R.drawable.matcha));
        teaModelArrayList.add(new ProductModel("Chai", 4, R.drawable.chai));


        ProductAdapter courseAdapter = new ProductAdapter(this, coffeeModelArrayList);
        ProductAdapter teaAdapter = new ProductAdapter(this, teaModelArrayList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        coffeeRecyclerView.setLayoutManager(linearLayoutManager);
        coffeeRecyclerView.setAdapter(courseAdapter);

        teaRecyclerView.setLayoutManager(linearLayoutManager2);
        teaRecyclerView.setAdapter(teaAdapter);

        coffeeRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, coffeeRecyclerView,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(CatalogActivity.this, ProductCardActivity.class);
                        ProductAdapter.Viewholder mod = (ProductAdapter.Viewholder) coffeeRecyclerView.findViewHolderForAdapterPosition(position);

                        String name = (String) Objects.requireNonNull(mod).productNameTV.getText();
                        intent.putExtra("name", name);
                        //String imgTitle = (String) Objects.requireNonNull(mod).productIV.get;
                        //ImageView img = (ImageView) findViewById(R.id.imageView);
                        int imageId = Objects.requireNonNull(mod).productIV.getId();
                        intent.putExtra("imgId", imageId);
                        System.out.println(name);
                        System.out.println(imageId);
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        System.out.println("@");
                    }
                })
        );
    }
}
