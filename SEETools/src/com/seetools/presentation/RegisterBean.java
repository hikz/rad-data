package com.seetools.presentation;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.dto.UserBean;
import com.seetools.presentation.common.SessionManager;

@ManagedBean(name="registerBean")
@SessionScoped
public class RegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Autowired @Qualifier("authMgr") 
	 private AuthenticationManager authMgr;
	 @Autowired 
	 private UserDetailsService userDetailsSvc;
	 
	 @ManagedProperty(value="#{user}")
	 private UserBean user;
	 
	 
	public String confirmRegistration(){
		System.out.println("sending to confirm registration");
		this.setUser((UserBean)SessionManager.getSessionAttribute("user"));
		SessionManager.addSessionAttribute("user",this.user);
		return "confirmRegistration";
	}
	
	
	public String register(){
		
		SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl = new SeeToolsRegisterServiceImpl();
		this.setUser((UserBean)SessionManager.getSessionAttribute("user"));
		user = seeToolsRegisterServiceImpl.processRegistration(this.getUser());
		
		try {
			  System.out.println("Before load method");
		      //UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getEmail().getEmailAddress());
		     // UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getEmail().getEmailAddress(), user.getPassword(), userDetails.getAuthorities());
		     // System.out.println("After load method");
		     // authMgr.authenticate(auth);
		 
		      // redirect into secured main page if authentication successful
		     // if(auth.isAuthenticated()) {
		      //  SecurityContextHolder.getContext().setAuthentication(auth);
		      //  System.out.println("session id - " + RequestContextHolder.currentRequestAttributes().getSessionId());
		        return "registerSuccess";
		     // }
		    } catch (Exception e) {
		      //logger.debug("Problem authenticating user" + userDto.getUserId(), e);
		    	e.printStackTrace();
		    }
		 
		    return "redirect:/xhtml/login/error";
	}
	public AuthenticationManager getAuthMgr() {
		return authMgr;
	}
	public void setAuthMgr(AuthenticationManager authMgr) {
		this.authMgr = authMgr;
	}
	public UserDetailsService getUserDetailsSvc() {
		return userDetailsSvc;
	}
	public void setUserDetailsSvc(UserDetailsService userDetailsSvc) {
		this.userDetailsSvc = userDetailsSvc;
	}


	public UserBean getUser() {
		return user;
	}


	public void setUser(UserBean user) {
		this.user = user;
	}
	
	
	/*private void authenticateUserAndSetSession(UserBean user, HttpServletRequest request) {
		
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	            user.getUserId(), user.getPassword());

	    // generate session if one doesn't exist
	    request.getSession();

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}*/
}
