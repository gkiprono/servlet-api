package com.example.service;

import com.example.dao.UserData;
import com.example.dao.UserDataInterface;
import com.example.models.Users;
import com.example.utils.EncryptDecrypt;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;

public class AuthenticationService {


    public boolean isValid(String userName, String password, InputStream inputStream){

        UserDataInterface userDataInterface = new UserData();
        Users currentUser = null;

        currentUser = userDataInterface.getUser(userName,inputStream);
        System.out.println(currentUser);

        return EncryptDecrypt.verifyUserPassword(password, currentUser.getPassword(), currentUser.getSalt());
    }
}
