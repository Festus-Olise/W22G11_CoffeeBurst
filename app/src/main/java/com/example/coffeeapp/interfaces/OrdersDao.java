package com.example.coffeeapp.interfaces;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.coffeeapp.models.Orders;

import java.util.List;

@Dao
public interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertOrder(Orders Order);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void InsertOrders(List<Orders> orders);

    @Query("SELECT * FROM orders")
    List<Orders> GetAllOrders();

    @Query("SELECT * FROM orders WHERE product_code IN (:ProdCode)")
    List<Orders> GetProductsFromIds(List<String> ProdCode);
}
