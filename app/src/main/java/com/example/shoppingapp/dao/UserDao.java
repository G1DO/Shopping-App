package com.example.shoppingapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.shoppingapp.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    default void insertUser(User user) {

    }

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    default User loginUser(String email, String password) {
        return null;
    }

    @Query("SELECT * FROM users")
    default List<User> getAllUsers() {
        return null;
    }
}
