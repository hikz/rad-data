package com.seetools.daolayer.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.seetools.dto.EmailDTO;
import com.seetools.dto.UserDTO;

public class UserMapper implements RowMapper<UserDTO> {

	@Override
	public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserDTO userDto = new UserDTO();
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
		return userDto;
	}

}
