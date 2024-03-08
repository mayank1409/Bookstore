package com.bookstore.entity;

import java.util.HashSet;
import java.util.Set;

public class Category implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5340565021854650246L;
	private Integer categoryId;
	private String name;
	private Set<Book> books = new HashSet<Book>(0);

	public Category(Integer categoryId, String name) {
		this(name);
		this.categoryId = categoryId;
	}

	public Category(String name) {
		this.name = name;
	}

	public Category() {

	}

	public Category(String name, Set<Book> books) {
		this.name = name;
		this.books = books;
	}

	public Integer getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Book> getBooks() {
		return this.books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

}
