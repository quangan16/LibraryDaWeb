package com.test.demo.order;

import java.util.Date;

public class Order {
	int ID;
	String bookTitle;
	int quantity;
	Date orderTime;
	String username;
	float totalPayment;
	
	public Order() {
		
	}
	
	public Order(String bookTitle, int quantity, String username, float totalPayment) {
		super();
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.username = username;
		this.totalPayment = totalPayment;
	}

	public Order(String bookTitle, int quantity, String username) {
		super();
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.username = username;
	}

	public Order(String bookTitle, int quantity, Date orderTime, String username) {
		super();
	
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.username = username;
	}
	
	public Order(int iD, String bookTitle, int quantity, Date orderTime, String username) {
		super();
		ID = iD;
		this.bookTitle = bookTitle;
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.username = username;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	
	
}
