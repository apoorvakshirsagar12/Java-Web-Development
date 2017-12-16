package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mvc.util.DBConnection;
import java.sql.PreparedStatement;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class LoginDao 
{
    public String checkAdmin(String uname,String passwd)
    {
        Connection conn = null;
        Statement statement = null;
	ResultSet resultSet = null;
	String userNameDB = "";
	String passwordDB = "";     
        String roleDB = "";       
        try
	{
        	System.out.println("in dao");
            conn = DBConnection.createConnection();
            statement = conn.createStatement(); 
            resultSet = statement.executeQuery("select U_UserName,U_Password,U_Role from users where U_UserName like ('"+uname+"')");           
            while(resultSet.next())
            {
            	userNameDB = resultSet.getString("U_UserName"); 
		passwordDB = resultSet.getString("U_Password");
                roleDB = resultSet.getString("U_Role");
            }
	}
	catch(SQLException e)
	{
            e.printStackTrace();
	}
        if(uname.equals(userNameDB) && passwd.equals(passwordDB) && roleDB.equalsIgnoreCase("admin"))
        {
            return "valid"; 
        }
        else
        {
            return "Incorrect username and password";
        }
    }
    
    public String checkManager(String uname,String passwd)
    {
    	System.out.println("in checkmanager");
        Connection conn = null;
        Statement statement = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
    	HttpSession session;
	ResultSet resultSet = null;
	String userNameDB = "";
	String passwordDB = "";
        String statusDB = "";  
        try
	{
            conn=DBConnection.createConnection();
            statement = conn.createStatement(); 
            resultSet = statement.executeQuery("select U_Userid,U_UserName,U_Password,U_Status from users where U_UserName like ('"+uname+"')"); 
            while(resultSet.next())
            {
            	
            	session = (HttpSession) facesContext.getExternalContext().getSession(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid",resultSet.getString("U_Userid"));
            	userNameDB = resultSet.getString("U_UserName"); 
		passwordDB = resultSet.getString("U_Password");
                statusDB = resultSet.getString("U_Status");
                System.out.println(resultSet.getString("U_Userid")+userNameDB+passwordDB+statusDB);
            }
	}
	catch(SQLException e)
	{
            e.printStackTrace();
	}
        if(uname.equals(userNameDB) && passwd.equals(passwordDB) && statusDB.equalsIgnoreCase("approved"))
        {
            return "valid"; 
        }
        else
        {
            return "Invalid User";
        }
    }
    
    
    public String checkUser(String uname,String passwd, String role)
    {
        Connection conn = null;
        Statement statement = null;
	ResultSet resultSet = null;
	FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpSession session;
	String userNameDB = "";
	String passwordDB = "";
        String roleDB = "";

        try
	{
            conn=DBConnection.createConnection();
            statement = conn.createStatement(); 
            resultSet = statement.executeQuery("select U_Userid,U_UserName,U_Password,U_Role from users where U_UserName like ('"+uname+"')"); 
            while(resultSet.next())
            {
            	 session = (HttpSession) facesContext.getExternalContext().getSession(true);
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("uid",resultSet.getString("U_Userid"));
              
            	userNameDB = resultSet.getString("U_UserName"); 
		passwordDB = resultSet.getString("U_Password");
                roleDB = resultSet.getString("U_Role");
            }
            
	}
	catch(SQLException e)
	{
            e.printStackTrace();
	}
        if(uname.equals(userNameDB) && passwd.equals(passwordDB) && role.equalsIgnoreCase(roleDB))
        {
            return "valid"; 
        }
        else
        {
            return "Invalid User";
        }
    }
    
    public String getName(String uname)
    {
        Connection conn;
        Statement stmt;
        String fnameDB = null; 
        String lnameDB = null; 
        try
        {
            conn=DBConnection.createConnection();
            stmt = conn.createStatement(); 
            ResultSet res =stmt.executeQuery("select U_FirstName,U_LastName from users where U_UserName like ('"+uname+"')");
            if(res.next())
            {
                fnameDB = res.getString("U_FirstName");
                lnameDB = res.getString("U_LastName");
            }       
	}
	catch(SQLException e)
	{
            e.printStackTrace();
	}
        return fnameDB+" "+lnameDB;
    }
}
