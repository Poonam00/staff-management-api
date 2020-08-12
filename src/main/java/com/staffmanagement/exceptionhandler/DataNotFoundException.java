package com.staffmanagement.exceptionhandler;

import org.springframework.dao.DataAccessException;

public class DataNotFoundException extends DataAccessException {

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
