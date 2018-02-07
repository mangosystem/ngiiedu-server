package kr.go.ngii.edu.controller.rest;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.go.ngii.edu.main.users.model.User;

public class BaseController {

	protected final Logger LOGGER = LoggerFactory.getLogger( this.getClass() );


	protected ResponseData responseBody(Object data) {
		ResponseData body = new ResponseData();
		body.setResponse(data);
		return body;
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseData> exception(Exception e) {
		return new ResponseEntity<ResponseData>(responseError(e), HttpStatus.OK);
	}

	protected ResponseData responseError(Exception e) {
		ResponseData responseData = new ResponseData();
		responseData.setError(e);
		return responseData; 
	}
	
	
	protected boolean isAdmin(HttpSession session) {

		try {
			User user = (User)session.getAttribute("USER_INFO");
			if (user == null) {
				return false;

			} else {
				if ("3".equals(user.getUserDivision().trim())) {
					return true;
				} else {
					return false;
				}
			}

		} catch (Exception e) {
			return false;
		}
	}


}
