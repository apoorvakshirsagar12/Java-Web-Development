package com.mvc.dao;

import java.sql.*;
import com.mvc.util.DBConnection;
import javax.faces.context.FacesContext;

public class RegisterDao 
{
    public Connection conn = null; 
    PreparedStatement pstmt = null;
    ResultSet res = null;
    int i,j;
    
    public void userRegister(String fname,String lname,String address,String email,String passwd,String role, String phone)
    {     
        try
        {
            String status=null;
            double balance=100000;
            conn=DBConnection.createConnection();
            if(role.equalsIgnoreCase("user"))
            {
            	status="approved";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query="insert into users(`U_FirstName`,`U_LastName`,`U_Address`,`U_Phone`,`U_UserName`,`U_Password`,`U_Role`,`U_Status`,`balance`) "
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"','"+balance+"')";
               System.out.println(query);
            	pstmt.executeUpdate(query);
            	System.out.println("record inserted successfully");
               
            }
            if(role.equalsIgnoreCase("manager"))
            {
            	status="pending";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query2="insert into users(`U_FirstName`,`U_LastName`,`U_Address`,`U_Phone`,`U_UserName`,`U_Password`,`U_Role`,`U_Status`,`balance`) "
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"','"+balance+"')";
            	System.out.println(query2);
                pstmt.executeUpdate(query2);
                System.out.println("record inserted successfully");
                
            }
             System.out.println("Data added successfully...");
             pstmt.close();
             conn.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        
    }
    
    public void update(String fname,String lname,String address,String phone,String email,String username)
    {
    	conn=DBConnection.createConnection();
    	String query="update users set U_UserName=?,U_FirstName=?,U_LastName=?,U_Phone=?,U_Address=? where U_UserName=?";
    	try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, phone);
			pstmt.setString(5, address);
			pstmt.setString(6, username);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }
}