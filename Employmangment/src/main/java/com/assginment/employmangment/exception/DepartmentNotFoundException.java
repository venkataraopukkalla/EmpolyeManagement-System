package com.assginment.employmangment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException  extends RuntimeException{

	public DepartmentNotFoundException(String string) {
		super(string);
	}
	
}
