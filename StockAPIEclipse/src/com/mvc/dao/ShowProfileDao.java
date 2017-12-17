package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mvc.util.DBConnection;

public class ShowProfileDao 
{
	public static String getDetails(String name)
	{
		Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String mgrid="";
        try
        {
        	System.out.println("in dao");
            conn = DBConnection.createConnection();
            statement = conn.createStatement(); 
            resultSet = statement.executeQuery("select U_Userid from users where U_FirstName like '%"+name+"%' or U_LastName like '%"+name+"%'");           
            while(resultSet.next())
            {
            	mgrid = resultSet.getString("U_Userid"); 
            }
        }
	catch(SQLException e)
	{
            e.printStackTrace();
	}
        System.out.println("mgr: "+mgrid);
        return mgrid;
	}
	public static void submitMgr()
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		Integer mid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("mid"));
		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
		
		System.out.println(mid);
		System.out.println(uid);
		conn=DBConnection.createConnection();
    	String query="update users set mgr_id=? where U_Userid=?";
    	try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, mid);
			pstmt.setInt(2, uid);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		
	}
}
