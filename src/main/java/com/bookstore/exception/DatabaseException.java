package com.bookstore.exception;

public class DatabaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9059917702393061584L;

	private String mesage;

	public DatabaseException(String mesage) {
		super();
		this.mesage = mesage;
	}

	public String getMesage() {
		return mesage;
	}

	public void setMesage(String mesage) {
		this.mesage = mesage;
	}

}
