package com.mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.mvc.dao.RegisterDao;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="register_bean")

public class Register 
{
    String fname;
    String lname;
    String address;
    String email;
    String uname;
    String passwd;
    String role;
    String phone;
    double fees;
    
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

	RegisterDao obj=new RegisterDao();

    public Register() {
    }

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

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public String registerUser()
    {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println("In registerUser() method...");
        try
        {
        	System.out.println(fname+lname+address+email+passwd+role+ phone+fees);
            obj.userRegister(fname, lname, address, email, passwd, role, phone, fees);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
        facesContext.getExternalContext().getSessionMap().put("message", "User registered successfully..");
        return "index?faces-redirect=true";
    }
}
