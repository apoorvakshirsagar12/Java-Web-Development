package com.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.mvc.util.DBConnection;

public class StockRequestDao 
{
    public static String requestSubmit(String type,double amount) 
    {
    	try
    	{
    	Connection conn = DBConnection.createConnection();
    	Statement stmt = conn.createStatement();
        ResultSet res = null;
    	
   		System.out.println("in requestsubmit");
    		Integer uid = Integer.parseInt((String) FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getSessionMap().get("uid"));
    		System.out.println(uid);
    		String query="select mgr_id from users where (U_Userid='" + uid + "')";
    		res = stmt.executeQuery(query);
    		 if(res.next()==false)//if not
             {
             	System.out.println("no manager selected");
             	FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Please select the manager first",""));
             }
    		 else
    		{
    			 int mgr_id=res.getInt("mgr_id");
    			 System.out.println(mgr_id);
    			 res=null;
    			 res = stmt.executeQuery("select balance from users where (U_Userid='" + uid + "')");
    			 res.next();
    			 double balance=res.getDouble("balance");
    			 System.out.println(balance);
    			 if(balance<amount)
    			 {
    				 System.out.println("insufficient balance");
    				 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You don't have sufficient balance, please change the amount.",""));
    			 }
    			 else
    			 {
    				 String query2="insert into stock_requests_manager (`uid`,`mgr_id`,`amt`,`type`) "
    				+ "values ('"+uid+"','"+mgr_id+"','"+amount+"','"+type+"')";
    				 stmt.executeUpdate(query2);
    				 System.out.println("request submitted");
    				 FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Your request has been submitted successfully.",""));
    			 }
    		}
    		
    		
    	}
    	catch(Exception ex)
    	{
    		ex.printStackTrace();
    	}
    	return "stockRequestUser";
    }
}
