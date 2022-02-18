package com.example.service;

public class AuthenticationService {

    public static boolean isValid(String user, String password){
        return user.equals("admin") && password.equals("root");
    }
}
