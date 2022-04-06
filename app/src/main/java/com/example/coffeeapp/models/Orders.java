package com.example.coffeeapp.models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

// Class to hold order details
@Entity(tableName = "orders", primaryKeys = {"orderid", "product_code"},
        foreignKeys = @ForeignKey(entity = Product.class, parentColumns = "product_code",
        childColumns = "product_code", onDelete = ForeignKey.CASCADE))
public class Orders {
    @ColumnInfo(name = "orderid")
    @NonNull
    private String OrderId;

    @ColumnInfo(name = "product_code")
    @NonNull
    private String product_code;

    @ColumnInfo (name = "product_name")
    private String product_name;

    @ColumnInfo(name = "product_price")
    @NonNull
    private double product_price;

    @ColumnInfo(name = "quantity")
    @NonNull
    private int Quantity;

    public Orders() {

    }

    public Orders(@NonNull String orderId, @NonNull String product_code, String product_name, double product_price, int quantity) {
        OrderId = orderId;
        this.product_code = product_code;
        this.product_name = product_name;
        this.product_price = product_price;
        Quantity = quantity;
    }

    @NonNull
    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(@NonNull String orderId) {
        OrderId = orderId;
    }

    @NonNull
    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(@NonNull String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
