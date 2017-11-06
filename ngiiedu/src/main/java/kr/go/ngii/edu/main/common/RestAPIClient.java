package kr.go.ngii.edu.main.common;

import java.net.URI;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;

public class RestAPIClient {
	
	private static final String REST_BASE_URI = LocalResourceBundle.PINOGIO_API_SERVER;
	private static final String REST_PROJECT_ID = LocalResourceBundle.PINOGIO_API_PROJECT_ID;
	private RestTemplate restTemplate;
	public RestAPIClient() {
//		restTemplate = getRestTempalte();
		restTemplate = new RestTemplate();
	}
	
	private RestTemplate getRestTempalte() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(1000 * 60 * 5); // 5분
		factory.setConnectTimeout(5000);
		return new RestTemplate(factory);
	}
	
	public Map<String, Object> getResponseBody(String apiType, Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI+apiType);
	    URI uriParam = builder.buildAndExpand(param).toUri();
	    ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, HttpMethod.GET, entity, typeRef);
		return result.getBody();
	}
	
	public Map<String, Object> getResponseBody(EnumRestAPIType EnumRestAPIType, Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI+EnumRestAPIType.code());
	    URI uriParam = builder.buildAndExpand(param).toUri();
	    ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, typeRef);
		Map<String, Object> body = result.getBody();
		return body;
	}
	
//	private ResponseEntity<Map<String, Object>> exchange(uriParam, method, entity) {
//		
//		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, typeRef);
//		return null;
//	}
	
	

}
