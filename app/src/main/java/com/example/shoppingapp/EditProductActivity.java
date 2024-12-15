package com.example.shoppingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.database.AppDatabase;
import com.example.shoppingapp.models.Product;

public class EditProductActivity extends AppCompatActivity {

    private EditText productIdEditText, productNameEditText, productCategoryEditText,
            productPriceEditText, productStockEditText;
    private Button updateProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        productIdEditText = findViewById(R.id.editTextProductId);
        productNameEditText = findViewById(R.id.editTextProductName);
        productCategoryEditText = findViewById(R.id.editTextProductCategory);
        productPriceEditText = findViewById(R.id.editTextProductPrice);
        productStockEditText = findViewById(R.id.editTextProductStock);
        updateProductButton = findViewById(R.id.buttonUpdateProduct);

        updateProductButton.setOnClickListener(v -> updateProduct());
    }

    private void updateProduct() {
        String idStr = productIdEditText.getText().toString().trim();
        String name = productNameEditText.getText().toString().trim();
        String category = productCategoryEditText.getText().toString().trim();
        String priceStr = productPriceEditText.getText().toString().trim();
        String stockStr = productStockEditText.getText().toString().trim();

        if (idStr.isEmpty() || name.isEmpty() || category.isEmpty() || priceStr.isEmpty() || stockStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);
        double price = Double.parseDouble(priceStr);
        int stock = Integer.parseInt(stockStr);

        new Thread(() -> {
            AppDatabase db = AppDatabase.getInstance(this);
            Product product = db.productDao().getProductById(id);

            if (product == null) {
                runOnUiThread(() -> Toast.makeText(this, "Product not found", Toast.LENGTH_SHORT).show());
                return;
            }

            product.setName(name);
            product.setCategory(category);
            product.setPrice(price);
            product.setStockQuantity(stock);

            db.productDao().updateProduct(product);

            runOnUiThread(() -> {
                Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            });
        }).start();
    }
}
