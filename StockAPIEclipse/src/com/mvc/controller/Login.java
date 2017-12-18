package com.mvc.controller;

import com.mvc.dao.LoginDao;

import javax.servlet.http.HttpSession;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name="login_bean")
@SessionScoped

public class Login
{
	private String uname;
    private String passwd;
    private String role;
    
    LoginDao ob=new LoginDao();
  
    public Login() {
    }
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
    
    public String validateAdmin()
    {
        String result;
        HttpSession session;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        result = ob.checkAdmin(uname, passwd);
        if (result.equals("valid"))
        {
            session = (HttpSession) facesContext.getExternalContext().getSession(true);
            facesContext.getExternalContext().getSessionMap().put("fname",ob.getName(this.getUname()) );
            
            return "adminHome?faces-redirect=true";
        }
        else
	{
            facesContext.getExternalContext().getSessionMap().put("message", "Incorrect username or password");
            return "adminLogin?faces-redirect=true";
	}    
    }
    

    public String validateUser()
    {
        String result;
        HttpSession session;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        System.out.println(uname);
        System.out.println(passwd);
        System.out.println(role);
        
        
        if(role.equalsIgnoreCase("manager"))
        {
            result = ob.checkManager(uname, passwd);
            
            if (result.equals("valid"))
            {
              session = (HttpSession) facesContext.getExternalContext().getSession(true);
                facesContext.getExternalContext().getSessionMap().put("fname",ob.getName(this.getUname()) );

                return "managerAccount?faces-redirect=true";
            }
            else
            {
                facesContext.getExternalContext().getSessionMap().put("message", "Incorrect username or password");
                return "index?faces-redirect=true";
            }       
        }
        else
        {
            result = ob.checkUser(uname, passwd, role);
            
            if (result.equals("valid"))
            {
            	System.out.println("valid");
            	session = (HttpSession) facesContext.getExternalContext().getSession(true);
                facesContext.getExternalContext().getSessionMap().put("fname",ob.getName(this.getUname()) );

                return "userAccount?faces-redirect=true";
            }
            else
            {
                facesContext.getExternalContext().getSessionMap().put("message", "Incorrect username or password");
                return "index?faces-redirect=true";
            }        
        }       
    }
    
    public String adminLogout() 
    {
        System.out.println("in logout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
          //      .handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
        return "adminLogin?faces-redirect=true";
    }
    
    public String logout() 
    {
        System.out.println("in logout");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler()
          //      .handleNavigation(FacesContext.getCurrentInstance(), null, "index.xhtml");
        return "index?faces-redirect=true";
    }
}
