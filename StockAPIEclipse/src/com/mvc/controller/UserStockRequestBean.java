package com.mvc.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SessionScoped
@ManagedBean(name="userstock_bean")
public class UserStockRequestBean
{
	private int req_id;

	public int getReq_id() {
		return req_id;
	}

	public void setReq_id(int req_id) {
		this.req_id = req_id;
	}
	
	public String proceedRequest()
	{
		String req=Integer.toString(req_id);
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("reqId",req);
		
		return "managerMyStock";
	}
	
}
