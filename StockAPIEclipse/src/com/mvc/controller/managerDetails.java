package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/managerDetails")
public class managerDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public managerDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = null;
		
		try 
        {
            String output = "";
            String role="manager";
            String status="approved";
            Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid"));
          
            conn=DBConnection.createConnection();
            Statement statement = conn.createStatement();
            System.out.println(uid);
            String query = "select U_Userid,U_FirstName,U_LastName,U_Phone,U_Address from users where (U_Role='"+role+"') and (U_Status='"+status+"')";
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            System.out.println("2");
            output = output + "<table class='table table-striped table-list' height=30px border=5px'>";	
            output = output + "<th>U_Userid</th><th>First Name</th><th>Last Name</th><th>Phone No.</th><th>Address</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				int userid = rs.getInt("U_Userid");
				String fname = rs.getString("U_FirstName");
                String lname = rs.getString("U_LastName");
                String phone = rs.getString("U_Phone");
                String address = rs.getString("U_Address");
                                
				System.out.println("Inside try");

				output = output + "<td>" + userid + "</td><td>" + fname + "</td><td>" + lname + "</td><td>" + phone + "</td><td>" + address + "</td>";
				
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
