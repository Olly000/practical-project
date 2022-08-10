package com.qa.exceptions;

import java.rmi.UnexpectedException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.EXPECTATION_FAILED, reason = "Entity could not be deleted")
public class NoDeleteException extends UnexpectedException {

	
	private static final long serialVersionUID = 1L;
	private static String detailMessage = "Entity was not deleted";

	public NoDeleteException() {
		super(detailMessage );
		
	}

}
