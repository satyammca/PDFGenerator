package com.competency.pdfgenerator.examples.beans;

public class PersonalDetailsModel {
	private long userId;
	private String emailId;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private PersonalDetailsLocation personalDetailsLocation;
	private String userExperience;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
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
	public PersonalDetailsLocation getPersonalDetailsLocation() {
		return personalDetailsLocation;
	}
	public void setPersonalDetailsLocation(
			PersonalDetailsLocation personalDetailsLocation) {
		this.personalDetailsLocation = personalDetailsLocation;
	}
	public String getUserExperience() {
		return userExperience;
	}
	public void setUserExperience(String userExperience) {
		this.userExperience = userExperience;
	}
	
}
