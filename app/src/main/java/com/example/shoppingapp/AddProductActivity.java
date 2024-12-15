package com.example.shoppingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.database.AppDatabase;
import com.example.shoppingapp.models.Product;

public class AddProductActivity extends AppCompatActivity {
    private EditText productNameEditText, productCategoryEditText, productPriceEditText, productStockEditText;
    private Button saveProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productNameEditText = findViewById(R.id.editTextProductName);
        productCategoryEditText = findViewById(R.id.editTextProductCategory);
        productPriceEditText = findViewById(R.id.editTextProductPrice);
        productStockEditText = findViewById(R.id.editTextProductStock);
        saveProductButton = findViewById(R.id.buttonSaveProduct);

        saveProductButton.setOnClickListener(v -> saveProduct());
    }

    private void saveProduct() {
        String name = productNameEditText.getText().toString().trim();
        String category = productCategoryEditText.getText().toString().trim();
        String priceStr = productPriceEditText.getText().toString().trim();
        String stockStr = productStockEditText.getText().toString().trim();

        if (name.isEmpty() || category.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this); // Access the database instance
            Product product = new Product();
            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setStockQuantity(stock);

            db.productDao().insertProduct(product); // Use productDao to insert the product

            runOnUiThread(() -> {
                Toast.makeText(AddProductActivity.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
