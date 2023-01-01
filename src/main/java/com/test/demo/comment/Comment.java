package com.test.demo.comment;

import java.util.Date;

public class Comment {
	int ID;
	String username;
	String content;
	int bookCode;
	Date time;
	float starPoint;
	
	
	public Comment() {
		
	}
	
	public Comment(int iD, String content, String username, int bookCode,Date time,  float starPoint) {
		super();
		ID = iD;
		this.content = content;
		this.bookCode = bookCode;
		this.username = username;
		this.time = time;
		this.starPoint = starPoint;
		
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getBookCode() {
		return bookCode;
	}
	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}
	
	
	public float getStarPoint() {
		return starPoint;
	}
	public void setStarPoint(float starPoint) {
		this.starPoint = starPoint;
	}
	
}
