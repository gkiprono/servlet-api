package com.example.utils;

import com.example.dao.UserData;
import com.example.dao.UserDataInterface;
import com.example.models.Users;
import com.example.service.IdProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestConn {
    private  static PreparedStatement stmt;
    private static ResultSet resultSet;
    private static Connection connection;

    public static void main(String[] args) {
        UserDataInterface userDataInterface =  new UserData();

        // manual test get one
        Users user = userDataInterface.getUser(1);
        System.out.println(user);

        System.out.println(EncryptDecrypt.verifyUserPassword("Qwerty1234@",user.getPassword(), user.getSalt()));

        System.out.println();

        // manual test get all
        userDataInterface.getAllUsers().stream().forEach(System.out::println);

        // manual test update
        String salt = EncryptDecrypt.getSalt(15);
        String password = EncryptDecrypt.generateSecurePassword("gabby2311@", salt);

        // builder design pattern
        Users userA = new Users.PersonBuilder("Nissan", "Versa")
                .userId(IdProvider.getNextUserId())
                .userName("nissanversa")
                .accountId(IdProvider.getNextAccountId())
                .email("nissanversa@gmail.com")
                .salt(salt)
                .password(password)
                .build();

        // insert user into database
        if(userDataInterface.setUser(userA)){
            System.out.println(userA + " saved!");
        }

        // get all users with password lens less than 10 (un-hashed passwords)
        List<Users> usersToBFixed;
        usersToBFixed = userDataInterface.getAllUsers().stream()
                .filter(x -> x.getPassword().length() < 15).collect(Collectors.toList());

//        if (usersToBFixed.size() == 0) return;

        // generate salt values
        usersToBFixed.forEach(
                x -> x.setSalt(EncryptDecrypt.getSalt(15))
        );

        // hash passwords
        usersToBFixed.forEach(
                x -> x.setPassword(EncryptDecrypt.generateSecurePassword(x.getPassword(), x.getSalt()))
        );



        // finally, update the accounts
        if(usersToBFixed.size() != 0)
            usersToBFixed.forEach(userDataInterface::updateAccount
        );


        System.out.println(IdProvider.getNextUserId() + " userId");

        System.out.println(IdProvider.getNextAccountId() + " AccountId");

    }

}
