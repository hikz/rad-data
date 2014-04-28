package com.seetools.presentation;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.dto.UserBean;

@ManagedBean(name="registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Autowired @Qualifier("authMgr") 
	 private AuthenticationManager authMgr;
	 @Autowired 
	 private UserDetailsService userDetailsSvc;
	 
	 private UserBean user = new UserBean();
	 
	    
	
	 @PostConstruct
	    public void init() {
	        user = new UserBean();
	    }
	 
	 
	public String register(){
		
		
		SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl = new SeeToolsRegisterServiceImpl();
		user = seeToolsRegisterServiceImpl.processRegistration(user);
		
		try {
		      UserDetails userDetails = userDetailsSvc.loadUserByUsername(user.getUserId());
		      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword(), userDetails.getAuthorities());
		      authMgr.authenticate(auth);
		 
		      // redirect into secured main page if authentication successful
		      if(auth.isAuthenticated()) {
		        SecurityContextHolder.getContext().setAuthentication(auth);
		        System.out.println("session id - " + RequestContextHolder.currentRequestAttributes().getSessionId());
		        return "registerSuccess";
		      }
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
