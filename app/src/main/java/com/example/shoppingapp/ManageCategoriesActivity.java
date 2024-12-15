package com.example.shoppingapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoppingapp.database.AppDatabase;
import com.example.shoppingapp.models.Category;

import java.util.List;

public class ManageCategoriesActivity extends AppCompatActivity {
    private EditText categoryNameEditText;
    private Button addCategoryButton;
    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_categories);

        categoryNameEditText = findViewById(R.id.editTextCategoryName);
        addCategoryButton = findViewById(R.id.buttonAddCategory);
        categoriesRecyclerView = findViewById(R.id.recyclerViewCategories);

        addCategoryButton.setOnClickListener(v -> addCategory());

        setupRecyclerView();
        loadCategories();
    }

    private void setupRecyclerView() {
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadCategories() {
        AppDatabase db = AppDatabase.getInstance(this);
        List<Category> categoryList = db.categoryDao().getAllCategories();

        if (categoryAdapter == null) {
            categoryAdapter = new CategoryAdapter(categoryList);
            categoriesRecyclerView.setAdapter(categoryAdapter);
        } else {
            categoryAdapter.notifyDataSetChanged();
        }
    }

    private void addCategory() {
        String categoryName = categoryNameEditText.getText().toString();

        if (categoryName.isEmpty()) {
            Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show();
            return;
        }

        AppDatabase db = AppDatabase.getInstance(this);
        Category category = new Category();
        category.setName(categoryName);

        db.categoryDao().insertCategory(category);
        Toast.makeText(this, "Category added successfully", Toast.LENGTH_SHORT).show();

        categoryNameEditText.setText("");
        loadCategories();
    }
}
