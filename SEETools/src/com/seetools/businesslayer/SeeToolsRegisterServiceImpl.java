package com.seetools.businesslayer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.seetools.daolayer.RegisterDAOImpl;
import com.seetools.dto.UserDTO;
import com.seetools.presentation.common.SEEUtilities;

public class SeeToolsRegisterServiceImpl {

	public UserDTO processRegistration(UserDTO userDto){
		
		userDto.setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.setCreatedByUserId("test");
		userDto.setModifiedByUserId("test");
		
		userDto.getEmailDto().setCreatedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmailDto().setModifiedDate(SEEUtilities.getCurrentTimeStamp());
		userDto.getEmailDto().setCreatedByUserId("test");
		userDto.getEmailDto().setModifiedByUserId("test");
		
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("seetools-bean-config.xml");
		
		RegisterDAOImpl registerDAOImpl = (RegisterDAOImpl)applicationContext.getBean("seeToolsRegisterDAO");
		return registerDAOImpl.registerUser(userDto);
	}
}
