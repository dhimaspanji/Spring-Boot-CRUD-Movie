package com.example.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exceptionDetail;
    private Object fieldValue;
    
    public RecordNotFoundException(String exceptionDetail, int fieldValue) {
        super();
        this.exceptionDetail = exceptionDetail;
        this.fieldValue = fieldValue;
    }
    
    public String getExceptionDetail() {
        return exceptionDetail;
    }
    
    public Object getFieldValue() {
        return fieldValue;
    }
}
