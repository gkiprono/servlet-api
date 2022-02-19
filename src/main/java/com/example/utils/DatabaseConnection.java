package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DatabaseConnection {
	private static Connection conn;
	// 	jdbc:postgresql://hostname:port/databaseName
	private static String USERNAME;
	private static String PASSWORD;
	private static String DATABASE_URL;


	static {
		try {
			InputStream stream = ClassLoader.getSystemResourceAsStream("database.properties");
			Properties properties = new Properties();
			properties.load(new FileInputStream("database.properties"));
			String user = properties.getProperty("USER");
			String pwd = properties.getProperty("PASSWORD");
			String url = properties.getProperty("DATABASE");
			
			// get them from sys variables
			USERNAME = System.getenv(user);
			PASSWORD = System.getenv(pwd);
			DATABASE_URL = System.getenv(url);

		} catch (IOException e) {

		}
	}

	//get connection
	public static Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn == null || conn.isClosed()) {
				try {
					conn = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


}
