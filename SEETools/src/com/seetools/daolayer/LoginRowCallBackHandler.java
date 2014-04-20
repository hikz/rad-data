package com.seetools.daolayer;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.seetools.dto.EmailDTO;
import com.seetools.dto.UserDTO;

public class LoginRowCallBackHandler implements RowCallbackHandler {

	UserDTO userDto = new UserDTO();
	
	@Override
	public void processRow(ResultSet rs) throws SQLException {
		
		
		EmailDTO emailDto = new EmailDTO();
		emailDto.setEmailAddress(rs.getString("EmailAddress"));
		userDto.setEmailDto(emailDto);
		userDto.setUserId(rs.getString("UserID"));
		userDto.setPassword(rs.getString("Password"));
		
		userDto.setFirstName(rs.getString("FirstName"));
		userDto.setLastName(rs.getString("LastName"));
		userDto.setMobileNumber(rs.getString("MobileNumber"));
		userDto.setMembershipId(rs.getString("MembershipID"));
		userDto.setCreatedByUserId(rs.getString("CreatedByUserID"));
		userDto.setCreatedDate(rs.getTimestamp("CreatedDate"));
		userDto.setModifiedByUserId(rs.getString("ModifiedByUserId"));
		userDto.setModifiedDate(rs.getTimestamp("ModifiedDate"));
	}

	public UserDTO getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDTO userDto) {
		this.userDto = userDto;
	}

	
}
