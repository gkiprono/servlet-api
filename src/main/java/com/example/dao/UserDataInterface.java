package com.example.dao;

import com.example.models.Users;

import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

public interface UserDataInterface {
    // get one user by user id
    public Users getUser(int userId);

    // get all users
    public ArrayList<Users> getAllUsers();

    // set one user
    public boolean setUser(Users user);

    // set one user
    public boolean setUser(Users user, InputStream inputStream);

    // update a user -> many possibilities
    public void updateAccount(Users user);

    // get user by userName
    public Users getUser(String username, InputStream inputStream);


}
