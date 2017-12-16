
package com.mvc.dao;

import com.mvc.util.DBConnection;
import java.sql.*;

public class ActionRequestDao {

public static void approveReq(int u_id)
	{
		System.out.println(u_id);
		Connection conn=null;
		PreparedStatement pstmt=null;
		conn=DBConnection.createConnection();
		String query="update users set U_Status=? where U_Userid=?";
		try 
		{	
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1,"approved");
			pstmt.setInt(2, u_id);
			pstmt.executeUpdate();
		
			System.out.println("Request approved");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void declineReq(int u_id)
	{
			System.out.println(u_id);
			Connection conn=null;
			PreparedStatement pstmt=null;
			conn=DBConnection.createConnection();
			String query="update users set U_Status=? where U_Userid=?";
			try 
			{	
				pstmt=conn.prepareStatement(query);
				pstmt.setString(1,"declined");
				pstmt.setInt(2, u_id);
				pstmt.executeUpdate();
			
				System.out.println("Request declined");
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
	}
    
}

