package pack2;

import java.sql.Connection;
import java.sql.DriverManager;

public class GameDBconnect {
	
	private static String url = "jdbc:mysql://localhost:3306/game";
	private static String userName = "root";
	private static String password = "Love@9717";
	private static Connection con;
	
	public static Connection getConnection() {
		
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url,userName,password);
			System.out.println("Database Connection is Success!");
		}
		catch (Exception e) {
			System.out.println("Database Connection is Not Success!");
		}
		
		return con;
		
	}

}
