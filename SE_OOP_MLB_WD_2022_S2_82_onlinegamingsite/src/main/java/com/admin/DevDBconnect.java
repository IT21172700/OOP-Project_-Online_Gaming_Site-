package com.admin;

import java.sql.Connection;
import java.sql.DriverManager;

public class DevDBconnect {
	
	private static String url = "jdbc:mysql://localhost:3306/onlinegamingsite";
	private static String userName = "root";
	private static String password = "IT21158186";
	private static Connection con;
	
	public static Connection getConnection() {
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,userName,password);
			
		}
		catch (Exception e) {
			System.out.println("Database Connection is not Success!");
		}
		
		return con;
		
	}

}
