/*
* Provides new id's by looking at existing ones
*
* */
package com.example.service;


import com.example.utils.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class IdProvider {
    private static Connection connection;
    private static PreparedStatement statement;
    private static ResultSet resultSet;

    private IdProvider(){
        connection = DatabaseConnection.getConnection();
    }

    public static int getNextUserId(){
        return fetchIds();
    }

    public static int getNextAccountId(){
        return fetchAccountId();
    }

    public static int getNextUserId(InputStream str){
        return fetchIds();
    }

    public static int getNextAccountId(InputStream str){
        return fetchAccountId();
    }

    /* Helper functions to generate unique account id and user id
    *
    * */
    private static int fetchIds(){
        ArrayList<Integer> ids = new ArrayList<>();
        new IdProvider();
        String query = "SELECT id FROM users";

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                ids.add(resultSet.getInt("id"));
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        // return the max id value
        int next = ids.stream().max((val1,val2)->val1 - val2).orElseThrow(NoSuchElementException::new);
        return next + 1;
    }

    private static int fetchAccountId(){
        ArrayList<Integer> ids = new ArrayList<>();
        new IdProvider();
        String query = "SELECT account_id FROM users";

        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                ids.add(resultSet.getInt("account_id"));
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        // return the max id value
        int next = ids.stream().max((val1,val2)->val1 - val2).orElseThrow(NoSuchElementException::new);
        return next + 1010;
    }

    // close resources
    private static void closeResources() {
        try {
            if (connection != null && !statement.isClosed()) {
                statement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
