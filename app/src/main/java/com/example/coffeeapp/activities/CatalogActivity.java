package com.example.coffeeapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.example.coffeeapp.databases.ProductDatabase;
import com.example.coffeeapp.interfaces.ProductDao;
import com.example.coffeeapp.models.Product;
import com.example.coffeeapp.R;
import com.example.coffeeapp.supplements.RecyclerItemClickListener;
import com.example.coffeeapp.adapters.ProductAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CatalogActivity extends AppCompatActivity {

    private RecyclerView coffeeRecyclerView;
    private RecyclerView teaRecyclerView;

    // Arraylist for storing data
    private ArrayList<Product> coffeeModelArrayList;
    private ArrayList<Product> teaModelArrayList;


    ProductDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        coffeeRecyclerView = findViewById(R.id.idRVCoffee);
        teaRecyclerView = findViewById(R.id.idRVTea);


        // here we have created new array list and added data to it.
        coffeeModelArrayList = new ArrayList<>();
        coffeeModelArrayList.add(new Product("Cappuchino", 4, R.drawable.cappuchino));
        coffeeModelArrayList.add(new Product("Americano", 3, R.drawable.americano));
        coffeeModelArrayList.add(new Product("Latte", 4, R.drawable.latte));
        coffeeModelArrayList.add(new Product("Mocca", 4, R.drawable.mocca));

        teaModelArrayList = new ArrayList<>();
        teaModelArrayList.add(new Product("Green Tea", 4, R.drawable.greantea));
        teaModelArrayList.add(new Product("Matcha Latte", 4, R.drawable.matcha));
        teaModelArrayList.add(new Product("Chai", 4, R.drawable.chai));


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
