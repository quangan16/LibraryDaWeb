package com.test.demo.user;


public class User {

	private int ID;
	private String name;
	private int age;
	private String phoneNum;
	private String username;
	private String password;
	private String role;

	public User() {

	}

	public User(String userName, String userPassword) {
		this.username = userName;
		this.password = userPassword;
	}
	
	public User(String name, int age, String phoneNum, String username, String password) {
		super();
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
		this.username = username;
		this.password = password;
	}
	
	
	public User(int iD, String name,  int age, String phoneNum, String username, String password) {
		ID = iD;
		this.name = name;
		this.phoneNum = phoneNum;
		this.age = age;
		this.username = username;
		this.password = password;
	}

	public User(int iD, String name, int age, String phoneNum, String username, String password, String role) {
		super();
		ID = iD;
		this.name = name;
		this.age = age;
		this.phoneNum = phoneNum;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
