package com.example.coffeeapp.models;

// Class to hold product title, image, and price
public class Product {

    private String product_name;
    private int product_price;
    private int product_image;

    // Constructor
    public Product(String product_name, int course_rating, int course_image) {
        this.product_name = product_name;
        this.product_price = course_rating;
        this.product_image = course_image;
    }

    // Getter and Setter
    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public int getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int product_image) {
        this.product_image = product_image;
    }
}

