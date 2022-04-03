package com.example.coffeeapp.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.coffeeapp.models.Product;

import java.util.List;
@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertProducts(Product... products);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long[] insertProductsFromList(List<Product> products);

    @Query("SELECT * from products")
    List<Product> GetAllProducts();

    @Query("SELECT * FROM products WHERE catogory IN (:StudIds)")
    List<Product> GetStudentsInfoFromIds(List<String> StudIds);

}
