package com.training.bean;

public class CyclosBean {
	private String userName;
	private String password;
	private String memberName;
	private String amount;
    private String description;
	

	public CyclosBean() {
	}

	public CyclosBean(String userName, String password,String memberName,String amount,String description) {
		super();
		this.userName = userName;
		this.password = password;
		this.memberName=memberName;
		this.amount=amount;
		this.description=description;
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	public void setMemberName(String memberName) {
		this.memberName= memberName;
	}
	
	
	public String getAmount() {
		return amount;
	}
	
	public void setAmount(String amount) {
		this.amount= amount;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		 this.description=description;
	}

	@Override
	public String toString() {
		return "LoginBean [userName=" + userName + ", password=" + password + "]";
	}

}
