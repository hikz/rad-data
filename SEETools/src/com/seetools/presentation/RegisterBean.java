package com.seetools.presentation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.seetools.businesslayer.SeeToolsRegisterServiceImpl;
import com.seetools.dto.EmailDTO;
import com.seetools.dto.UserDTO;

@ManagedBean(name="registerBean")
@RequestScoped
public class RegisterBean {

	private String emailAddress;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String password;
	
	 @Autowired @Qualifier("authMgr") 
	 private AuthenticationManager authMgr;
	 @Autowired 
	 private UserDetailsService userDetailsSvc;
	    
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String register(){
		
		UserDTO userDto=new UserDTO();
		userDto.setFirstName(this.getFirstName());
		userDto.setLastName(this.getLastName());
		userDto.setMobileNumber(this.getMobileNumber());
		userDto.setPassword(this.getPassword());
		EmailDTO emailDto = new EmailDTO();
		emailDto.setEmailAddress(this.getEmailAddress());
		
		userDto.setEmailDto(emailDto);
		
		SeeToolsRegisterServiceImpl seeToolsRegisterServiceImpl = new SeeToolsRegisterServiceImpl();
		userDto = seeToolsRegisterServiceImpl.processRegistration(userDto);
		
		try {
		      UserDetails userDetails = userDetailsSvc.loadUserByUsername(userDto.getUserId());
		      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
		      authMgr.authenticate(auth);
		 
		      // redirect into secured main page if authentication successful
		      if(auth.isAuthenticated()) {
		        SecurityContextHolder.getContext().setAuthentication(auth);
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
	
	
	/*private void authenticateUserAndSetSession(UserDTO user, HttpServletRequest request) {
		
	    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
	            user.getUserId(), user.getPassword());

	    // generate session if one doesn't exist
	    request.getSession();

	    token.setDetails(new WebAuthenticationDetails(request));
	    Authentication authenticatedUser = authenticationManager.authenticate(token);

	    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
	}*/
}
