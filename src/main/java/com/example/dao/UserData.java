package com.example.dao;

import com.example.models.Users;
import com.example.utils.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@SuppressWarnings("DuplicatedCode")
public class UserData implements UserDataInterface{
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private String query;

    @Override
    public Users getUser(int userId) {
        Users user = new Users();
        query = "SELECT * FROM users WHERE id = ?";

        connection = DatabaseConnection.getConnection();

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user.setUserId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setUserName(resultSet.getString("username"));
            user.setAccountId(resultSet.getInt("account_id"));
            user.setPassword(resultSet.getString("hash_pass"));
            user.setSalt(resultSet.getString("salt_value"));

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeResources();
            return user;
        }

    }

    @Override
    public ArrayList<Users> getAllUsers() {
        query = "SELECT * FROM users";
        connection = DatabaseConnection.getConnection();

        ArrayList<Users> usersList = new ArrayList<>();

        try{
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Users user = new Users();

                user.setUserId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setUserName(resultSet.getString("username"));
                user.setAccountId(resultSet.getInt("account_id"));
                user.setPassword(resultSet.getString("hash_pass"));
                user.setSalt(resultSet.getString("salt_value"));

                usersList.add(user);
            }

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeResources();
            return usersList;
        }

    }

    @Override
    public boolean setUser(Users user) {
        boolean success = false;
        connection = DatabaseConnection.getConnection();

        String query = "INSERT INTO users(id,email,first_name,last_name,account_id,username,hash_pass,salt_value) " +
                "values(?,?,?,?,?,?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,user.getUserId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setInt(5,user.getAccountId());
            preparedStatement.setString(6,user.getUserName());
            preparedStatement.setString(7,user.getPassword());
            preparedStatement.setString(8, user.getSalt());

            preparedStatement.execute();
            success = true;

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        return success;
    }

    // update username and password
    @Override
    public void updateAccount(Users user) {

        connection = DatabaseConnection.getConnection();

        String query = "UPDATE users SET hash_pass = ?, salt_value= ? WHERE  id = ? ";

        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getSalt());
            preparedStatement.setInt(3, user.getUserId());

            preparedStatement.executeUpdate();

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeResources();
        }

    }

    @Override
    public Users getUser(String username, InputStream inputStream) {
        connection = DatabaseConnection.getConnection(inputStream);

        Users user = new Users();
        query = "SELECT * FROM users WHERE username = ?";


        try{
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            user.setUserId(resultSet.getInt("id"));
            user.setEmail(resultSet.getString("email"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setUserName(resultSet.getString("username"));
            user.setAccountId(resultSet.getInt("account_id"));
            user.setPassword(resultSet.getString("hash_pass"));
            user.setSalt(resultSet.getString("salt_value"));

        }catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeResources();
            return user;
        }
    }

    // handles post from servlet
    @Override
    public boolean setUser(Users user, InputStream inputStream) {
        boolean success = false;
        connection = DatabaseConnection.getConnection(inputStream);

        String query = "INSERT INTO users(id,email,first_name,last_name,account_id,username,hash_pass,salt_value) " +
                "values(?,?,?,?,?,?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1,user.getUserId());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4,user.getLastName());
            preparedStatement.setInt(5,user.getAccountId());
            preparedStatement.setString(6,user.getUserName());
            preparedStatement.setString(7,user.getPassword());
            preparedStatement.setString(8, user.getSalt());

            preparedStatement.execute();
            success = true;

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            closeResources();
        }

        return success;
    }

    // close resources
    private void closeResources() {
        try {
            if (connection != null && !preparedStatement.isClosed()) {
                preparedStatement.close();
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
