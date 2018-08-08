package com.rakesh.dairy.model;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public class AbstractResponse implements Serializable {

	private Integer status = HttpStatus.OK.value();
	private Object data;
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
	
}
