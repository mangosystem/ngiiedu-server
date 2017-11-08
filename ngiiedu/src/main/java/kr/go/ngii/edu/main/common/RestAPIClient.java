package kr.go.ngii.edu.main.common;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.net.URLCodec;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;

public class RestAPIClient {
	
	private static final String REST_BASE_URI = LocalResourceBundle.PINOGIO_API_SERVER;
	private static final String REST_PROJECT_ID = LocalResourceBundle.PINOGIO_API_PROJECT_ID;
	private RestTemplate restTemplate;
	public RestAPIClient() {
//		restTemplate = getRestTempalte();
		restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		restTemplate.getMessageConverters().add(converter);
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
	
	public Map<String, Object> getResponseBody(EnumRestAPIType EnumRestAPIType, Map<String, String> pathParam) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//	    headers.set("Accept", "application/json");
		
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + EnumRestAPIType.code());
	    URI uriParam = builder.buildAndExpand(pathParam).toUri();
	    ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, typeRef);
		Map<String, Object> body = result.getBody();
		return body;
	}
	
	
	public Map<String, Object> getResponseBody(EnumRestAPIType EnumRestAPIType, Map<String, String> pathParam, Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + EnumRestAPIType.code());
	    for( Map.Entry<String, String> elem : param.entrySet()) {
	    	builder.queryParam(elem.getKey(), elem.getValue());
	    }
	    
	    URI uriParam;
	    if (pathParam.isEmpty()) {
	    	uriParam = builder.build().toUri();
	    } else {
	    	uriParam = builder.buildAndExpand(pathParam).toUri();
	    }
	    
	    ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, 
				typeRef);
		Map<String, Object> body = result.getBody();
		return body;
	}


	
	public Map<String, Object> getResponseBody(EnumRestAPIType EnumRestAPIType, String pathParam, Map<String, String> param) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + pathParam);
	    for( Map.Entry<String, String> elem : param.entrySet()) {
	    	builder.queryParam(elem.getKey(), elem.getValue());
	    }
	    
	    URI uriParam = builder.build().toUri();
	    ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, 
				typeRef);
		Map<String, Object> body = result.getBody();
		return body;
	}
}
