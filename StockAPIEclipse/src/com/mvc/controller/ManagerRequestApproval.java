package com.mvc.controller;

import com.mvc.dao.ActionRequestDao;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="request_bean")

public class ManagerRequestApproval 
{
    private ActionRequestDao dao;   

    public ManagerRequestApproval() 
    {
        dao=new ActionRequestDao();
    }
    public String checkRequest()
    {
    	System.out.println("in check request");
        FacesContext context=FacesContext.getCurrentInstance();
        Map<String,String> params=context.getExternalContext().getRequestParameterMap();
        int uid=Integer.parseInt(params.get("U_Userid"));
        String action=params.get("status");
        System.out.println(uid);
        System.out.println(action);
        
        if(action.equalsIgnoreCase("approve"))
        {
            try
            {
                dao.approveRequest(uid);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        else if(action.equalsIgnoreCase("decline"))
        {
            try
            {
                dao.declineRequest(uid);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return "approveRequests";
        
        
    }
}
