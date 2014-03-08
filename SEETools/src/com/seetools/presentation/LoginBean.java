package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	private String name;
 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String welcome(){
		boolean isLoggedIn = false;
		
		if(!isLoggedIn){
			return "login";
		} else {
			return "failure";
		}
	}
	
	public String login(){
		
		
		if(getName().equals("munir")){
			return "welcome";
		} else {
			return "failure";
		}
	}
	
}
