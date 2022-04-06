package com.example.coffeeapp.databases;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.coffeeapp.interfaces.OrdersDao;
import com.example.coffeeapp.interfaces.ProductDao;
import com.example.coffeeapp.models.Orders;
import com.example.coffeeapp.models.Product;

@Database(entities = {Product.class, Orders.class},version=1,exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
    public abstract OrdersDao orderDao();
}
