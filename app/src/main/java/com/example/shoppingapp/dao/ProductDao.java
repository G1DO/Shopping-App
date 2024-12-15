package com.example.shoppingapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.shoppingapp.models.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insertProduct(Product product); // Insert a product

    @Query("SELECT * FROM products")
    List<Product> getAllProducts(); // Retrieve all products

    @Query("SELECT * FROM products WHERE id = :id")
    Product getProductById(int id); // Fetch a single product by ID

    @Update
    void updateProduct(Product product); // Update an existing product

    @Query("DELETE FROM products WHERE id = :id")
    void deleteProductById(int id); // Delete a product by ID
}
