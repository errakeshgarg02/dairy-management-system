package com.rakesh.dairy.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class AbstractResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1164775550482344004L;
	private Integer status = HttpStatus.OK.value();
	private Object data;
	private String message;
	private String error;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
}
