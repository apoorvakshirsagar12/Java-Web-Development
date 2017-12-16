package com.mvc.controller;

import javax.servlet.http.HttpSession;

import com.mvc.dao.ShowProfileDao;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="manager_bean")
@SessionScoped

public class showManagerProfile 
{
	private String mgrName="";

	public String getMgrName() {
		return mgrName;
	}

	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
	
	public String showProfile()
	{
		System.out.println(mgrName);
		String mgrid;
		mgrid=ShowProfileDao.getDetails(mgrName);
		
		 HttpSession session;
	     FacesContext facesContext = FacesContext.getCurrentInstance();
	     session = (HttpSession) facesContext.getExternalContext().getSession(true);
         facesContext.getExternalContext().getSessionMap().put("mid",mgrid);
	       
         return "managerProfile?faces-redirect=true";
	}
	public String submitManager()
	{
		try
		{
		ShowProfileDao.submitMgr();
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("message1", "You selected the Manager successfully");
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "You selected the Manager successfully",""));
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return "selectManager";
	}
}
