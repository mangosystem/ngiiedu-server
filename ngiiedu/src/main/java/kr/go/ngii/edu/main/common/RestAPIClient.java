package kr.go.ngii.edu.main.common;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;

public class RestAPIClient {
	
	private static final String REST_BASE_URI = LocalResourceBundle.PINOGIO_API_SERVER;
	private RestTemplate restTemplate;
	private String apiKey = "";
	
	public RestAPIClient() {
		restTemplate = new RestTemplate();
	}
	
	public RestAPIClient(String apiKey) {
		this.apiKey = apiKey;
		restTemplate = new RestTemplate();
	}
	
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	
	public Map<String, Object> getResponseBody(EnumRestAPIType enumType, Map<String, String> pathParam) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + format(enumType.code(), pathParam));
		    return getResponseExchange(builder, enumType.method());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String, Object> getResponseBody(EnumRestAPIType enumType, Map<String, String> pathParam, Map<String, String> param) {
		try {
			if (pathParam == null || pathParam.isEmpty()) {
				return getResponseBody(enumType, enumType.code(), param);
			}
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + format(enumType.code(), pathParam));
			if (param != null && !param.isEmpty()) {
				for( Map.Entry<String, String> elem : param.entrySet()) {
					builder.queryParam(elem.getKey(), elem.getValue());
				}
			}
		    return getResponseExchange(builder, enumType.method());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Map<String, Object> getResponseBody(EnumRestAPIType enumType, String pathParam, Map<String, String> param) {
		try {
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + pathParam);
		    for( Map.Entry<String, String> elem : param.entrySet()) {
		    	builder.queryParam(elem.getKey(), elem.getValue());
		    }
		    return getResponseExchange(builder, enumType.method());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getResponseBodyWithFiles(EnumRestAPIType enumType, Map<String, String> pathParam, Map<String, Object> param, MultipartFile file) {
		try {
			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + enumType.code());
			
			MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
			
			if (param != null && !param.isEmpty()) {
				for( Map.Entry<String, Object> elem : param.entrySet()) {
					multiValueMap.add(elem.getKey(), elem.getValue());
				}
			}
			
			ByteArrayResource resource = new ByteArrayResource(file.getBytes()){
				@Override
				public String getFilename() throws IllegalStateException {
					return file.getOriginalFilename();
				}
			};
			multiValueMap.add("ufile", resource);
			URI uriParam = builder.build().encode("UTF-8").toUri();
			return restTemplate.postForObject(uriParam, multiValueMap, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public String getResponseBodyForObject(EnumRestAPIType enumType, Map<String, String> pathParam, Map<String, String> param) {
//		try {
////			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + enumType.code());
//			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + format(enumType.code(), pathParam));
//			
//			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
//			
//			if (param != null && !param.isEmpty()) {
//				for( Map.Entry<String, String> elem : param.entrySet()) {
//					multiValueMap.add(elem.getKey(), elem.getValue());
//				}
//			}
//			URI uriParam = builder.build().encode("UTF-8").toUri();
////			return restTemplate.postForObject(uriParam, multiValueMap, String.class);
//			return restTemplate.postForObject(uriParam, multiValueMap, String.class);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
//	public Map<String, Object> getResponseBodyWithLinkedMap(EnumRestAPIType enumType, Map<String, String> pathParam, Map<String, String> param) {
//		try {
////			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + enumType.code());
//			UriComponentsBuilder builder;
//			if (pathParam == null || pathParam.isEmpty()) {
//				builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + enumType.code());
//			} else {
//				builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + format(enumType.code(), pathParam));
//			}
//			
//			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
//			
//			if (param != null && !param.isEmpty()) {
//				for( Map.Entry<String, String> elem : param.entrySet()) {
//					multiValueMap.set(elem.getKey(), elem.getValue());
//				}
//			}
//			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//			headers.set("apikey", "cacbf08b5c7a4b49a1d06ecb8c0278af");
//			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, headers);
//	    	ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {};
//			URI uriParam = builder.build().encode("UTF-8").toUri();
//			
//			 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//	        messageConverters.add(new MappingJackson2HttpMessageConverter());
//	        messageConverters.add(new FormHttpMessageConverter());
//	        restTemplate.getMessageConverters().addAll(messageConverters);
//		        
//			ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, enumType.method(), requestEntity, typeRef);
//			Map<String, Object> body = result.getBody();
//			return body;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	public Map<String, Object> getResponseBodyWithLinkedMap(EnumRestAPIType enumType, Map<String, String> pathParam, 
			Map<String, String> param, String key) {
		try {
			UriComponentsBuilder builder;
			if (pathParam == null || pathParam.isEmpty()) {
				builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + enumType.code());
			} else {
				builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + format(enumType.code(), pathParam));
			}
			
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
			
			if (param != null && !param.isEmpty()) {
				for( Map.Entry<String, String> elem : param.entrySet()) {
					multiValueMap.set(elem.getKey(), elem.getValue());
				}
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			if ("".equals(key)) {
				key = this.apiKey;
			}
			headers.set("apikey", key);
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(multiValueMap, headers);
	    	ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {};
			URI uriParam = builder.build().encode("UTF-8").toUri();
			
			 List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	        messageConverters.add(new MappingJackson2HttpMessageConverter());
	        messageConverters.add(new FormHttpMessageConverter());
	        restTemplate.getMessageConverters().addAll(messageConverters);
		        
			ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, enumType.method(), requestEntity, typeRef);
			Map<String, Object> body = result.getBody();
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	private Map<String, Object> getResponseExchange(UriComponentsBuilder builder, HttpMethod method) {
		try {
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		    if (!"".equals(this.apiKey)) {
		    	headers.set("apikey", this.apiKey);
		    }
		    URI uriParam = builder.build().encode("UTF-8").toUri();
		    ParameterizedTypeReference<Map<String, Object>> typeRef = 
					new ParameterizedTypeReference<Map<String, Object>>() {};
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, method, entity, typeRef);
			Map<String, Object> body = result.getBody();
			return body;
		} catch (Exception e) {         
			e.printStackTrace();
		}
		return null;
	}
	
	private static String format(String format, Map<String, String> values) {
		
	    StringBuilder formatter = new StringBuilder(format);
	    List<Object> valueList = new ArrayList<Object>();
	    Matcher matcher = Pattern.compile("\\{(\\w+)}").matcher(format);

	    while (matcher.find()) {
	        String key = matcher.group(1);

	        String formatKey = String.format("{%s}", key);
	        int index = formatter.indexOf(formatKey);

	        if (index != -1) {
	            formatter.replace(index, index + formatKey.length(), "%s");
	            valueList.add(values.get(key));
	        }
	    }
	    return String.format(formatter.toString(), valueList.toArray());
	}
}