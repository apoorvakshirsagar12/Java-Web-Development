package com.mvc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.faces.context.FacesContext;

import com.mvc.dao.RegisterDao;
import com.mvc.util.DBConnection;

public class ProfileBean {
	String username = ((String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("username"));
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String address;
	private int uid;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public Connection conn;
	RegisterDao obj;
	ProfileBean() {
		obj = new RegisterDao();

		conn = DBConnection.createConnection();
		String query = "select * from users where U_UserName like ('" + username + "')";
		try {

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				fname = rs.getString("U_FirstName");
				lname = rs.getString("U_LastName");
				email = rs.getString("U_UserName");
				phone = rs.getString("U_Phone");
				address = rs.getString("U_Address");
				uid = rs.getInt("U_Userid");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String updateUser() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		try {
			obj.update(fname, lname, address, phone, email, username);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		facesContext.getExternalContext().getSessionMap().put("message1", "User updated successfully..");
		return "userUpdateProfile?faces-redirect=true";

	}
}