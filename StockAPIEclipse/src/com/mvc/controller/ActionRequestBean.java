package com.mvc.controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mvc.dao.ActionRequestDao;

@ManagedBean(name="request_bean")
@SessionScoped

public class ActionRequestBean 
{
	private int user_id;

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String approveRequest()
	{
		System.out.println("in approve servlet"+user_id);
		try
		{
			ActionRequestDao.approveReq(user_id);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Approved",""));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "approveRequests";
	}
	
	public String declineRequest()
	{
		System.out.print("in decline servlet"+user_id);
		try
		{
			ActionRequestDao.declineReq(user_id);
			FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Request Declined",""));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "approveRequests";
	}
}
