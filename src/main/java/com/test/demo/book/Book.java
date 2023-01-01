package com.test.demo.book;

public class Book {
	
	private int bookcode;
	private String title;
	private String author;
	private String description;
	private String releaseDate;
	private int pageNumbers;
	private String category;
	private String photo;
	private int price;
	
	
	
	


	

	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public Book() {
		super();
	}


	public Book(String title, String author, String description, String releaseDate, int pageNumbers, String category) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.releaseDate = releaseDate;
		this.pageNumbers = pageNumbers;
		this.category = category;
	}


	public Book(int bookcode, String title, String author, String description, String releaseDate, int pageNumbers,
			String category) {
		super();
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.description = description;
		this.releaseDate = releaseDate;
		this.pageNumbers = pageNumbers;
		this.category = category;
	}
	
	public Book(int bookcode, String title, String author, String description, String releaseDate, int pageNumbers,
			String category, String photo, int price) {
		super();
		this.bookcode = bookcode;
		this.title = title;
		this.author = author;
		this.description = description;
		this.releaseDate = releaseDate;
		this.pageNumbers = pageNumbers;
		this.category = category;
		this.photo = photo;
		this.price = price;
	}


	public int getBookcode() {
		return bookcode;
	}


	public void setBookcode(int bookcode) {
		this.bookcode = bookcode;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public int getPageNumbers() {
		return pageNumbers;
	}


	public void setPageNumbers(int pageNumbers) {
		this.pageNumbers = pageNumbers;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}

	


	
	
}
