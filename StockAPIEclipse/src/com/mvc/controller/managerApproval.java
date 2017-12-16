
package com.mvc.controller;

import com.mvc.util.DBConnection;
import java.util.*;
import java.io.*;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/managerApproval")
public class managerApproval extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public managerApproval() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
	Connection conn = null;
        
        try 
        {
            String output = "";
            conn=DBConnection.createConnection();
            Statement statement = conn.createStatement();
            System.out.println("1");
            String query = "select U_Userid,U_FirstName,U_LastName,U_Address,U_Phone,U_Address,mgt_fees from users where U_Status='pending'";
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            System.out.println("2");
            output = output + "<table class='table table-striped table-list' height=30px border=5px'>";	
            output = output + "<th>User ID</th><th>Name</th><th>Address</th><th>Contact No.</th><th>Management Fees(%)</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				int user_id = rs.getInt("U_Userid");
				String first_name = rs.getString("U_FirstName");
				String last_name = rs.getString("U_LastName");
                String phone = rs.getString("U_Phone");
                String address = rs.getString("U_Address");
                double fees = rs.getDouble("mgt_fees");
                                
				System.out.println("Inside try");

				output = output + "<td>" + user_id + "</td><td>"+ first_name + " " + last_name + "</td><td>" + address + "</td><td>"
				+ phone + "</td><td>" + fees + "</td>";
				
				output = output + "</tr>";
			}
			output = output + "</table>";
			
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
