package com.mvc.util;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;

public class DBConnection
{
    public static Connection connection;
    public static Connection createConnection()
    {
	try {
			MysqlDataSource db = new MysqlDataSource();
			db.setServerName(System.getenv("ICSI518_DB_HOST").toString());
			db.setPortNumber(Integer.parseInt(System.getenv("ICSI518_DB_PORT")));
			db.setDatabaseName(System.getenv("ICSI518_DB").toString());
			db.setUser(System.getenv("ICSI518_DB_USER").toString());
			db.setPassword(System.getenv("ICSI518_DB_PASSWD").toString());
			
			connection = db.getConnection();
			 
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return connection;
	}
}