package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/currentManager")
public class currentManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public currentManager() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = null;
		
		try 
        {
            String output = "";
            Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid"));
          
            conn=DBConnection.createConnection();
            Statement statement = conn.createStatement();
            ResultSet rs=null;
            System.out.println(uid);
            String query = "select mgr_id from users where (U_Userid='" + uid + "')";
            rs=statement.executeQuery(query);	
            rs.next();
            int mgr_id = rs.getInt("mgr_id");
            String fname="";
            String lname="";
			System.out.println(mgr_id);
			
			rs=null;
			rs=statement.executeQuery("select U_FirstName,U_LastName from users where (U_Userid='"+mgr_id+"')");
			 output = output + "<div style='height=20px; width=100px;'>";
			while(rs.next())
			{
				fname=rs.getString("U_FirstName");
				lname=rs.getString("U_LastName");
			}
			output = output + "<h4>Current Manager: " +fname+" "+lname+ "</h4>";
			output = output + "</div>";
			
			rs.close();
			out.println(output);
			System.out.println("4");
			statement.close();

		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
