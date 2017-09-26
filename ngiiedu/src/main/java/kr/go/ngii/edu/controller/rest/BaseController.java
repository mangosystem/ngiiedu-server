package kr.go.ngii.edu.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
	
	protected final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );
	
	
	
	protected ResponseData responseBody(Object data) {
		ResponseData body = new ResponseData();
		body.setResponse(data);
		return body;
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> exception(Exception e) {
		return new ResponseEntity<ResponseData>(responseError(e), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	protected ResponseData responseError(Exception e) {
		ResponseData responseData = new ResponseData();
		responseData.setError(e);
		return responseData; 
	}
	
}
