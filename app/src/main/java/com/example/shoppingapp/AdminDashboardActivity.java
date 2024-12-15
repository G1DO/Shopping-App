package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    private Button addProductButton, editProductButton, deleteProductButton, viewCategoriesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        addProductButton = findViewById(R.id.buttonAddProduct);
        editProductButton = findViewById(R.id.buttonEditProduct);
        deleteProductButton = findViewById(R.id.buttonDeleteProduct);
        viewCategoriesButton = findViewById(R.id.buttonViewCategories);

        addProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, AddProductActivity.class);
            startActivity(intent);
        });

        editProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, EditProductActivity.class);
            startActivity(intent);
        });

        deleteProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, DeleteProductActivity.class);
            startActivity(intent);
        });

        viewCategoriesButton.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ManageCategoriesActivity.class);
            startActivity(intent);
        });
    }
}
