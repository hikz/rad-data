package com.seetools.businesslayer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.daolayer.RegisterDAOImpl;
import com.seetools.dto.UserBean;
import com.seetools.presentation.common.SEEUtilities;
import com.seetools.util.SendEmail;

public class SeeToolsRegisterServiceImpl {

	public UserBean processRegistration(UserBean userDto){
		
		userDto.setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setCreatedByUserId("test");
		userDto.setModifiedByUserId("test");
		
		userDto.getEmail().setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmail().setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmail().setCreatedByUserId("test");
		userDto.getEmail().setModifiedByUserId("test");
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
		
		
		//TODO Need to have transactions.
		UserBean user =  registerDAOImpl.registerUser(userDto);
		applicationContext.close();

		this.sendRegistrationConfirmationEmail(user.getEmail().getEmailAddress());
		return user;
	}
	
	private void sendRegistrationConfirmationEmail(String emailAddress){
		
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmail(emailAddress);
	}
}
