package com.assginment.employmangment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmpoyeeNotFoundException  extends RuntimeException{

	public EmpoyeeNotFoundException(String string) {
		super(string);
	}
	
}
