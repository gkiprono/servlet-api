package com.example.dao;

import com.example.models.Users;

import java.util.ArrayList;

public interface UserDataInterface {
    // get one user by user id
    public Users getUser(int userId);

    // get all users
    public ArrayList<Users> getAllUsers();

    // set one user
    public void setUser(Users user);

    // update a user -> many possibilities
    public void updateAccount(Users user);


}
