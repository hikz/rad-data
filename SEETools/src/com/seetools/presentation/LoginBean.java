package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

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
			return "notLoggedIn";
			//return "/xhtml/login/login";
		} else {
			return "alreadyLoggedIn";
			//return "/xhtml/tools/hipConverterTool";
		}
	}
	
	public String login(){
		String loginNavigation = "";
		 try {
			 
			 	Authentication result = null;
	            Authentication request = new UsernamePasswordAuthenticationToken(this.getName(), this.getPassword());
	            result = authenticationManager.authenticate(request);
	            SecurityContextHolder.getContext().setAuthentication(result);
	            this.setLoggedIn(true);
	            loginNavigation = "loginSuccess";
	        } catch (AuthenticationException e) {
	            loginNavigation = "failure";
	        	e.printStackTrace();
	        }
		 return loginNavigation;
	}
	
	public String logout(){
		 
		SecurityContextHolder.clearContext();
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
