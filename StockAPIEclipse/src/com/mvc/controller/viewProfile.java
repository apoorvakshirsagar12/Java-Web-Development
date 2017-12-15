
package com.mvc.controller;

import com.mvc.util.DBConnection;
import java.sql.*;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "viewProfile", urlPatterns = {"/viewProfile"})
public class viewProfile extends HttpServlet {

    public String fname,lname,address,phone,email;
    public Connection conn;
    public Statement stmt;
    public ResultSet res;
    
    public viewProfile()
    {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        String uname= (String) request.getSession().getAttribute("User_Id");
        System.out.println(uname);
        int u_id;
        try
        {
            conn=DBConnection.createConnection();
            stmt=conn.createStatement();
            String query="select * from users where U_UserName='"+uname+"'";
            res=stmt.executeQuery(query);
            res.next();
            u_id=res.getInt("U_Userid");
            fname=res.getString("U_FirstName");
            lname=res.getString("U_LastName");
            address=res.getString("U_Address");
            phone=res.getString("U_Phone");
            email=res.getString("U_UserName");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        
        
    }

}
