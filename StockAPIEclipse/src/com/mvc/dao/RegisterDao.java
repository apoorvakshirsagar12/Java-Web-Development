package com.mvc.dao;

import java.sql.*;
import com.mvc.util.DBConnection;
import javax.faces.context.FacesContext;

public class RegisterDao 
{
    public Connection conn = null; 
    PreparedStatement pstmt = null;
    ResultSet res = null;
    DBConnection db = new DBConnection();
    int i,j;
    
    public void userRegister(String fname,String lname,String address,String email,String passwd,String role, String phone)
    {     
        try
        {
            String status=null;
            conn=db.createConnection();
            if(role.equalsIgnoreCase("user"))
            {
            	status="approved";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query="insert into users('U_FirstName','U_LastName','U_Address','U_Phone','U_UserName','U_Password','U_Role','U_Status') "
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"')";
               System.out.println(query);
            	pstmt.executeUpdate(query);
               res=null;
               res=pstmt.executeQuery("select U_Userid from users where (U_UserName='" + email + "')");
               res.next();
               int uid=res.getInt("U_Userid");
               pstmt.executeUpdate("insert into tbl_user('user_id','balance') values('" + uid + "','100000')");
            }
            if(role.equalsIgnoreCase("manager"))
            {
            	status="pending";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query2="insert into users('U_FirstName','U_LastName','U_Address','U_Phone','U_UserName','U_Password','U_Role','U_Status') "
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"')";
            	System.out.println(query2);
                pstmt.executeUpdate(query2);
                res=null;
                res=pstmt.executeQuery("select U_Userid from users where (U_UserName='" + email + "')");
                res.next();
                int uid=res.getInt("U_Userid");
                pstmt.executeUpdate("insert into tbl_manager('uid','balance') values('" + uid + "','100000')");
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
    	conn=db.createConnection();
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