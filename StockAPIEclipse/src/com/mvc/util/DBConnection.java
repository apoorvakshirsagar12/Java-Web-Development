package com.mvc.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection
{
	private static DBConnection db=new DBConnection();
	static Connection connection=null;
	static String db_url = "jdbc:mysql://localhost:3306/my_stock";
	//public static String hostName = "localhost";
    //public static String portNumber = "3306";
    //public static String databaseName = "my_stock";
    public static String userName = "root";
    public static String password = "root";
    
    private DBConnection()
    {
    	
    }
    public static Connection createConnection()
	{
    	try
    	{
    		if(connection==null)
    		{
    			Class.forName("com.mysql.jdbc.Driver");
    			connection = DriverManager.getConnection(db_url,userName, password);
    			System.out.println("Printing connection object "+connection);
    		}
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return connection;
    }
}