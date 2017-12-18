package com.mvc.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mvc.dao.RegisterDao;

@SessionScoped
@ManagedBean(name="updatemgrbean")

public class UpdateManagerBean
{
	String fname=(String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("first");
	
    String lname=(String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("last");
    
    String address=(String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("addr");
    
    String email=(String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("email");

    String phone=(String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("phn");
    
    double fees=Double.parseDouble((String) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSessionMap().get("fee"));
        
	 public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String updateUser()
	    {
	        System.out.println("In updateUser() method...");
	        try
	        {
	        	System.out.println(fname+lname+address+email+phone+fees);
	            RegisterDao.update(fname, lname, address,phone, email,fees);
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }    
	        return "updateManagerProfile?faces-redirect=true";
	    }
    
}
