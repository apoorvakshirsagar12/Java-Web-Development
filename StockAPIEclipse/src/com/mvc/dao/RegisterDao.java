package com.mvc.dao;

import java.sql.*;
import com.mvc.util.DBConnection;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class RegisterDao 
{
    public static Connection conn = null; 
    static PreparedStatement pstmt = null;
    PreparedStatement pstmt2 = null;
    ResultSet res = null;
    int i,j;
    
    public void userRegister(String fname,String lname,String address,String email,String passwd,String role, String phone,double fees)
    {     
        try
        {
            String status=null;
            double balance=100000;
            java.sql.Timestamp  sqlDate = new java.sql.Timestamp(new java.util.Date().getTime());
            conn=DBConnection.createConnection();
            if(role.equalsIgnoreCase("user"))
            {
            	status="approved";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query="insert into users(`U_FirstName`,`U_LastName`,`U_Address`,`U_Phone`,`U_UserName`,`U_Password`,`U_Role`,`U_Status`,`mgr_id`,`mgt_fees`,`balance`) "
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"','0','"+fees+"','"+balance+"')";
               	System.out.println(query);
               	pstmt=conn.prepareStatement(query);
            	pstmt.executeUpdate();
            	res = null;
    			res = pstmt.executeQuery("select U_Userid from users where (U_UserName='" + email + "')");
    			res.next();
    			int userid = res.getInt("U_Userid");
    			System.out.println("uid:"+userid);
    			String query2="insert into tbl_user(`user_id`,`balance`,`date`) values('"+userid+"','"+balance+"','"+sqlDate+"')";
    			System.out.println(query2);
    			pstmt2=conn.prepareStatement(query2);
            	pstmt2.executeUpdate();
            	System.out.println("record inserted successfully");
               
            }
            if(role.equalsIgnoreCase("manager"))
            {
            	status="pending";
            	System.out.println(fname+lname+address+phone+email+passwd+role+status);
            	String query3="insert into users(`U_FirstName`,`U_LastName`,`U_Address`,`U_Phone`,`U_UserName`,`U_Password`,`U_Role`,`U_Status`,`mgr_id`,`mgt_fees`,`balance`)"
                        + "values('"+fname+"','"+lname+"','"+address+"','"+phone+"','"+email+"','"+passwd+"','"+role+"','"+status+"','0','"+fees+"','0')";
            	System.out.println(query3);
            	pstmt=conn.prepareStatement(query3);
                pstmt.executeUpdate();
                System.out.println("record inserted successfully");
                
            }
             
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }        
        
    }
    
    public static void update(String fname,String lname,String address,String phone,String email,double fees)
    {
    	conn=DBConnection.createConnection();
    	Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap().get("uid"));
    	String query="update users set U_UserName=?,U_FirstName=?,U_LastName=?,U_Phone=?,U_Address=?,mgt_fees=? where (U_Userid='"+uid+"')";
    	try {
			pstmt=conn.prepareStatement(query);
			pstmt.setString(1, email);
			pstmt.setString(2, fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, phone);
			pstmt.setString(5, address);
			pstmt.setDouble(6, fees);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated the details",""));
    	
    }
}