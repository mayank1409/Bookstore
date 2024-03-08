package com.bookstore.entity;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Book implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -603380563872373466L;
	private Integer bookId;
	private String title;
	private String author;
	private String description;
	private String isbn;
	private byte[] image;
	private float price;
	private Date publishDate;
	private Date lastUpdateTime;
	private Category category;
	private Set<Review> reviews = new HashSet<Review>(0);
	private Set<OrderDetail> orderDetails = new HashSet<OrderDetail>(0);

	private String base64EncodeImage;

	public Book() {
	}

	public Book(Integer bookId, String title, String author, String description, String isbn, byte[] image, float price,
			Date publishDate, Date lastUpdateTime, Category category) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
		this.category = category;
		this.base64EncodeImage = Base64.getEncoder().encodeToString(image);
	}

	public Book(Integer bookId, String title, String author, String description, String isbn, byte[] image, float price,
			Date publishDate, Date lastUpdateTime, Set<Review> reviews, Set<OrderDetail> orderDetails) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.description = description;
		this.isbn = isbn;
		this.image = image;
		this.price = price;
		this.publishDate = publishDate;
		this.lastUpdateTime = lastUpdateTime;
		this.reviews = reviews;
		this.orderDetails = orderDetails;
		this.base64EncodeImage = Base64.getEncoder().encodeToString(image);
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public byte[] getImage() {
		return this.image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Set<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public Set<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(Set<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getBase64EncodeImage() {
		return base64EncodeImage;
	}

	public void setBase64EncodeImage(String base64EncodeImage) {
		this.base64EncodeImage = base64EncodeImage;
	}

}
