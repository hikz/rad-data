package com.seetools.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String password;

	
	@ManagedProperty(value="#{authenticationManager}")
    private AuthenticationManager authenticationManager = null;
	
	 @ManagedProperty(value="#{userDetailsService}")
	    private UserDetailsService userDetailsService = null;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String login() throws IOException, ServletException {
		String loginNavigation = "";
		 try {
			 
			 /*	Authentication result = null;
	            Authentication request = new UsernamePasswordAuthenticationToken(this.getName(), this.getPassword());
	            result = authenticationManager.authenticate(request);
	            //SEcurity Context holds the session information required between requests.
	            SecurityContextHolder.getContext().setAuthentication(result);
	           
	            loginNavigation = "loginSuccess";*/
	            
	            
	            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

	            RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/xhtml/login/tools_login");

	            System.out.println("session id - " + context.getSessionId(true));
	            System.out.println("session id - " + RequestContextHolder.currentRequestAttributes().getSessionId());
	            dispatcher.forward((ServletRequest) context.getRequest(),
	                    (ServletResponse) context.getResponse());
	            
	            FacesContext.getCurrentInstance().responseComplete();
	            
	            /*System.out.println("session id - " +((WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails())
	    		        .getSessionId());*/
	            
	            
	            return null;
	        }catch (UsernameNotFoundException unfe) {
	        	FacesMessage badCredentialsMessage = new FacesMessage("Invalid credentials. Please login again");
	        	FacesContext.getCurrentInstance().addMessage(null, badCredentialsMessage);
	        	loginNavigation = "failure";
	        }
		 	catch (BadCredentialsException bce){
	        	FacesMessage badCredentialsMessage = new FacesMessage("Invalid credentials. Please login again");
	        	FacesContext.getCurrentInstance().addMessage(null, badCredentialsMessage);
	        	loginNavigation = "failure";
	        	//bce.printStackTrace();
	        }
		 	catch (AuthenticationException e) {
	            loginNavigation = "failure";
	        	//e.printStackTrace();
	        }
		 return loginNavigation;
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

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

}
