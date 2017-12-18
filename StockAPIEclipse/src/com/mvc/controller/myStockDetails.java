package com.mvc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mvc.util.DBConnection;

@WebServlet("/myStockDetails")
public class myStockDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public myStockDetails() {
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
            String query = "select stock_symbol,qty from user_stock where (uid='" + uid + "')";
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            System.out.println("2");
            output = output + "<table class='table table-striped table-list' height=30px border=5px'>";	
            output = output + "<th>Stock Symbol</th><th>Quantity</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				String ss = rs.getString("stock_symbol");
                int qty = rs.getInt("qty");
                
				System.out.println("Inside try");

				output = output + "<td>"+ ss + "</td><td>" + qty + "</td>";
				
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
