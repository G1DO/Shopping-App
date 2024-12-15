package com.example.shoppingapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.shoppingapp.dao.CategoryDao;
import com.example.shoppingapp.dao.ProductDao;
import com.example.shoppingapp.dao.UserDao;
import com.example.shoppingapp.models.Category;
import com.example.shoppingapp.models.Product;
import com.example.shoppingapp.models.User;

@Database(entities = {Category.class, Product.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;

    // Declare DAOs
    public abstract CategoryDao categoryDao(); // Category DAO
    public abstract ProductDao productDao();   // Product DAO
    public abstract UserDao userDao();         // User DAO

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "shopping_app_database")
                    .fallbackToDestructiveMigration() // Reset database during development if schema changes
                    .build();
        }
        return instance;
    }
}
