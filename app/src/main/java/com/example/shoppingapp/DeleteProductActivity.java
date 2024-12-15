package com.example.shoppingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.shoppingapp.database.AppDatabase;

public class DeleteProductActivity extends AppCompatActivity {
    private EditText productIdEditText;
    private Button deleteProductButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_product);

        productIdEditText = findViewById(R.id.editTextDeleteProductId);
        deleteProductButton = findViewById(R.id.buttonDeleteProduct);

        deleteProductButton.setOnClickListener(v -> deleteProduct());
    }

    private void deleteProduct() {
        String idStr = productIdEditText.getText().toString();

        if (idStr.isEmpty()) {
            Toast.makeText(this, "Please enter a product ID", Toast.LENGTH_SHORT).show();
            return;
        }

        int id = Integer.parseInt(idStr);

        AppDatabase db = AppDatabase.getInstance(this);
        db.productDao().deleteProductById(id);

        Toast.makeText(this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}
