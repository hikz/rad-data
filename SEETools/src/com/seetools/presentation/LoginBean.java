package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.seetools.presentation.common.SessionManager;

@ManagedBean
@RequestScoped
public class LoginBean implements Serializable {

	 
	private String name;
	private String password;
	private boolean loggedIn = false;
	
	@ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
	
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String welcome(){
		//boolean isLoggedIn = false;
		
		if(!this.isLoggedIn()){
			return "/xhtml/login/login";
		} else {
			return "/xhtml/tools/hipConverterTool";
		}
	}
	
	public String login(){
		String loginNavigation = "";
		 try {
	            /*Authentication request = new UsernamePasswordAuthenticationToken(this.getName(), this.getPassword());
	            Authentication result = authenticationManager.authenticate(request);
	            SecurityContextHolder.getContext().setAuthentication(result);*/
			 	if(this.getName().equals("munir") && this.getPassword().equals("munir")){
			 		this.setLoggedIn(true);
		            loginNavigation = "/xhtml/tools/hipConverterTool";
			 	}
	            
	        } catch (AuthenticationException e) {
	            loginNavigation = "failure";
	        	e.printStackTrace();
	        }
		 return loginNavigation;
	}
	
	public String logout(){
		SessionManager.logout();
		return "/xhtml/login/login";
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }
 
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
