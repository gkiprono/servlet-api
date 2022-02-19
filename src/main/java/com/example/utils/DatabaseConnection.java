package com.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;


public class DatabaseConnection {
	private static Connection conn;
	// 	jdbc:postgresql://hostname:port/databaseName
	private static String USERNAME;
	private static String PASSWORD;
	private static String DATABASE_URL;
	private static Map<String, String> variables;


	private DatabaseConnection(){
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			variables = System.getenv();
			Properties properties = new Properties();

			InputStream input = new FileInputStream("C:\\Gabriel\\Intellij\\Spring-demo\\serverlet-demo\\database.properties");
			properties.load(input);

			String user = properties.getProperty("USER");
			String pwd = properties.getProperty("PASSWORD");
			String url = properties.getProperty("DATABASE");

			// get them from sys variables
			USERNAME = variables.get(user);
			PASSWORD = variables.get(pwd);
			DATABASE_URL = variables.get(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private DatabaseConnection(InputStream input){
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			variables = System.getenv();
			Properties properties = new Properties();
			properties.load(input);

			String user = properties.getProperty("USER");
			String pwd = properties.getProperty("PASSWORD");
			String url = properties.getProperty("DATABASE");

			// get them from sys variables
			USERNAME = variables.get(user);
			PASSWORD = variables.get(pwd);
			DATABASE_URL = variables.get(url);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	//get connection
	public static Connection getConnection(InputStream input) {
		new DatabaseConnection(input);

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

	public static Connection getConnection() {
		new DatabaseConnection();

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

	public static void doSome(){
		// initializer
	}


}
