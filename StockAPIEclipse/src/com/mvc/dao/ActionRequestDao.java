
package com.mvc.dao;

import com.mvc.util.DBConnection;
import java.sql.*;

public class ActionRequestDao {

private Connection conn;

public ActionRequestDao()
	{
		conn=DBConnection.createConnection();
	}

public void approveRequest(int u_id)
	{
		try 
		{
			String username="";
                        String fname="";
                        String lname="";
			String query2="select U_UserName,U_FirstName,U_LastName from users where U_Userid=?";
			PreparedStatement pstmt=conn.prepareStatement(query2);
			pstmt.setInt(1, u_id );
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				username=rs.getString("U_UserName");
				fname=rs.getString("U_FirstName");
				lname=rs.getString("U_LastName");
			}
			
			String query="update users set U_Status=? where U_Userid=?";
                        //String query3="insert into tbl_manager(users_Userid) ";
			PreparedStatement pstmt2=conn.prepareStatement(query);
			pstmt2.setString(1, "approved");
			pstmt2.setInt(2, u_id);
			pstmt2.executeUpdate();
		
			System.out.println("Request approved");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void declineRequest(int u_id)
	{
		try 
		{
			String username="";
                        String fname="";
                        String lname="";
			String query2="select U_UserName,U_FirstName,U_LastName from users where U_Userid=?";
			PreparedStatement pstmt=conn.prepareStatement(query2);
			pstmt.setInt(1, u_id );
			ResultSet rs=pstmt.executeQuery();
			if(rs.next())
			{
				username=rs.getString("U_UserName");
				fname=rs.getString("U_FirstName");
				lname=rs.getString("U_LastName");
			}
			
			String query="update users set U_Status=? where U_Userid=?";
			PreparedStatement pstmt2=conn.prepareStatement(query);
			pstmt2.setString(1, "declined");
			pstmt2.setInt(2, u_id);
			pstmt2.executeUpdate();
		
			System.out.println("Request declined");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
    
}

