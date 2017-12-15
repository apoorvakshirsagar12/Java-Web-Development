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

@WebServlet("/userAccountBalance")
public class userAccountBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public userAccountBalance() {
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
            System.out.println(uid);
            String query = "select balance from users where (U_Userid='" + uid + "')";
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            System.out.println("2");
            output = output + "<div style='height=20px; width=100px;'>";	
            

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
                double balance = rs.getDouble("balance");
                                
				System.out.println(balance);
				
				output = output + "<h4>Current Balance: $" + balance + "</h4>";
			}
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
