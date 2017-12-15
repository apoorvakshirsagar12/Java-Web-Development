package com.mvc.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	public static Connection createConnection()
	{
		Connection conn = null;
		String db_url = "jdbc:mysql://localhost:3306/my_stock"; //MySQL URL and followed by the database name
		String db_username = "root"; //MySQL username
		String db_password = "root"; //MySQL password
		try 	
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver"); //loading mysql driver 
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} 
			conn = DriverManager.getConnection(db_url, db_username, db_password); //attempting to connect to MySQL database
			System.out.println("Printing connection object "+conn);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return conn; 
	}
}