package com.admin;

public class Devlist {
	
	private String devName;
	private String devEmail;
	private String devContactNo;
	private String devCategory;
	private String devPassword;

	
	//creating the constructor for variables
	public Devlist(String devName, String devEmail, String devContactNo, String devCategory, String devPassword) {
		this.devName = devName;
		this.devEmail = devEmail;
		this.devContactNo = devContactNo;
		this.devCategory = devCategory;
		this.devPassword = devPassword;
	}
	

	public String getdevName() {
		return devName;
	}

	public String getdevEmail() {
		return devEmail;
	}

	public String getdevContactNo() {
		return devContactNo;
	}	

	public String getdevCategory() {
		return devCategory;
	}
	
	public String getdevPassword() {
		return devPassword;
	}


}
