package kr.go.ngii.edu.controller.rest;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.http.HttpStatus;

@XmlRootElement(name = "response")
public class ResponseData {

	private Map<String, Object> response = null;


	public ResponseData() {
		this.response = new HashMap<String, Object>();
	}
	
	
	public Map<String, Object> getResponse() {
		return response;
	}
	
	public void setResponse(HttpStatus status, Object result) {
//		response.put("code", status.value());
		response.put("data", result);
	}
	
	public void setResponse(Object result) {
//		response.put("code", HttpStatus.OK);
		response.put("data", result);
	}
	
	public void setError(Exception e) {
		response.put("code", HttpStatus.INTERNAL_SERVER_ERROR);
		response.put("message", e.getMessage());
	}
	

}
