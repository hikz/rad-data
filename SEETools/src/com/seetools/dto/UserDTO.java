package com.seetools.dto;

import java.sql.Timestamp;

public class UserDTO {

	private String userId;
	private String password;
	
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String membershipId;
	private String createdByUserId;
	private Timestamp createdDate;
	private String modifiedByUserId;
	private Timestamp modifiedDate;
	
	private EmailDTO emailDto;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getMembershipId() {
		return membershipId;
	}
	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
	public String getCreatedByUserId() {
		return createdByUserId;
	}
	public void setCreatedByUserId(String createdByUserId) {
		this.createdByUserId = createdByUserId;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getModifiedByUserId() {
		return modifiedByUserId;
	}
	public void setModifiedByUserId(String modifiedByUserId) {
		this.modifiedByUserId = modifiedByUserId;
	}
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public EmailDTO getEmailDto() {
		return emailDto;
	}
	public void setEmailDto(EmailDTO emailDto) {
		this.emailDto = emailDto;
	}
	
	
}
