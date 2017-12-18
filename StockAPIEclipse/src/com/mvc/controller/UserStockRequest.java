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

@WebServlet("/UserStockRequest")
public class UserStockRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserStockRequest() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		Connection conn = null;
	        
	        try 
	        {
	        	Integer mid = Integer.parseInt((String) FacesContext.getCurrentInstance()
	                    .getExternalContext()
	                    .getSessionMap().get("uid"));
	            String output = "";
	            conn=DBConnection.createConnection();
	            Statement statement = conn.createStatement();
	            System.out.println("mgr id"+mid);
	            String query = "select req_id,uid,amt,type from stock_requests_manager where (mgr_id = '"+mid+"') and (status = 'pending') ";
	            statement.executeQuery(query);
	            ResultSet rs = statement.getResultSet();
	            System.out.println("in select");
	            output = output + "<table class='table table-striped table-list' height=30px border=5px'>";	
	            output = output + "<th>Request ID</th><th>User Id</th><th>Amount</th><th>Transaction</th>";

				System.out.println("3");
				while (rs.next()) {
					System.out.println("5");
					output = output + "<tr>";
					int req_id = rs.getInt("req_id");
					int user_id = rs.getInt("uid");
					String type = rs.getString("type");
	                double amount = rs.getDouble("amt");
	                                
					System.out.println("Inside try");

					output = output + "<td>" + req_id + "</td><td>" + user_id + "</td><td>$"+ amount + "</td><td>" + type + "</td>";
					
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
