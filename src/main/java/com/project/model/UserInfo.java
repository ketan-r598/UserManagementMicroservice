package com.project.model;


public class UserInfo {
	private String email;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String address;
//	private String role;
	
	public UserInfo() {

	}

	public UserInfo(String email, String firstName, String lastName, String contactNumber, String address) {
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserInfo [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNumber="
				+ contactNumber + ", address=" + address + "]";
	}
}
