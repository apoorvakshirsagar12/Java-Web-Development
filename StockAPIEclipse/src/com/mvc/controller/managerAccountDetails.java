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

@WebServlet("/managerAccountDetails")
public class managerAccountDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public managerAccountDetails() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = null;
		System.out.print("in servlet");
		try 
        {
            String output = "";
            Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid"));
          
            conn=DBConnection.createConnection();
            Statement statement = conn.createStatement();
            System.out.println(uid);
            String query = "select tu.date,su.symbol,su.price,su.qty,su.amt,su.type,tu.balance from stock_requests_manager su,tbl_user tu where (su.mgr_id = tu.user_id) and (su.mgr_id='" + uid + "') and (tu.date = su.date) order by date desc";
            statement.executeQuery(query);
            ResultSet rs = statement.getResultSet();
            System.out.println("2");
            output = output + "<table class='table table-striped table-list' height=30px border=5px'>";	
            output = output + "<th>Date</th><th>Stock Symbol</th><th>Price</th><th>Quantity</th><th>Amount</th><th>Transaction</th><th>Balance</th>";

			System.out.println("3");
			while (rs.next()) {
				System.out.println("5");
				output = output + "<tr>";
				Timestamp dt = rs.getTimestamp("date");
				String ss = rs.getString("symbol");
				double price = rs.getDouble("price");
                int qty = rs.getInt("qty");
                double amt = rs.getDouble("amt");
                String action = rs.getString("type");
                double balance = rs.getDouble("balance");
                                
				System.out.println("Inside try");

				output = output + "<td>" + dt + "</td><td>"
						+ ss + "</td><td>$" + price + "</td><td>" + qty + "</td><td>$" + amt + "</td><td>" + action + "</td><td>" + balance + "</td>";
				
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
