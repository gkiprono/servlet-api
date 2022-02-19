package com.example.utils;

import com.example.dao.UserData;
import com.example.dao.UserDataInterface;
import com.example.models.Users;

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
        Users user = userDataInterface.getUser(2);
        System.out.println(user);

        System.out.println(EncryptDecrypt.verifyUserPassword("Qwerty1234@",user.getPassword(), user.getSalt()));

        System.out.println();

        // manual test get all
        userDataInterface.getAllUsers().stream().forEach(System.out::println);

        // manual test update
        String salt = EncryptDecrypt.getSalt(15);
        String password = EncryptDecrypt.generateSecurePassword("Qwerty1234@", salt);

        // builder design pattern
        Users userA = new Users.PersonBuilder("Gabriel", "Keton")
                .userId(1)
                .userName("songgabe")
                .accountId(14789865)
                .email("songHane@.com")
                .salt(salt)
                .password(password)
                .build();

        // insert user into database
        userDataInterface.setUser(userA);

        // get all users with password lens less than 10 (un-hashed passwords)
        List<Users> usersToBFixed;
        usersToBFixed = userDataInterface.getAllUsers().stream()
                .filter(x -> x.getPassword().length() < 15).collect(Collectors.toList());

        // generate salt values
        usersToBFixed.forEach(
                x -> x.setSalt(EncryptDecrypt.getSalt(15))
        );

        // hash passwords
        usersToBFixed.forEach(
                x -> x.setPassword(EncryptDecrypt.generateSecurePassword(x.getPassword(), x.getSalt()))
        );



        // finally, update the accounts
        usersToBFixed.forEach(
                userDataInterface::updateAccount
        );

    }

}
