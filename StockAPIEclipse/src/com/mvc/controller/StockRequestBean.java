package com.mvc.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.mvc.dao.StockRequestDao;

@SessionScoped
@ManagedBean(name="stockRequest")

public class StockRequestBean {
	
	String type;
	double amount;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String requestManager()
	{
		String result=StockRequestDao.requestSubmit(type,amount);
		return result;
	}
}
