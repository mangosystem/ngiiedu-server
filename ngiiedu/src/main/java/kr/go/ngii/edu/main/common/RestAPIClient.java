package kr.go.ngii.edu.main.common;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
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
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.ClassUtils;
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
		restTemplate = new RestTemplate();
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		restTemplate.getMessageConverters().add(converter);

//		String cahrSet = "UTF-8";
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
//		formHttpMessageConverter.setCharset(Charset.forName(cahrSet));
//		List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();
//		partConverters.add(new StringHttpMessageConverter(Charset.forName(cahrSet)));
//		partConverters.add(new ResourceHttpMessageConverter());
//		partConverters.add(new ByteArrayHttpMessageConverter());
//		formHttpMessageConverter.setPartConverters(partConverters);
//		messageConverters.add(formHttpMessageConverter);
//		messageConverters.add(new StringHttpMessageConverter(Charset.forName(cahrSet)));
//		restTemplate = new RestTemplate(messageConverters);
//		
//		for (HttpMessageConverter<?> hmc : restTemplate.getMessageConverters()) {
//		    if (hmc instanceof AllEncompassingFormHttpMessageConverter) {
//
//		        /** AllEncompassingFormHttpMessageConverter 생성자 내용 일부 가져와서 수정 **/
//		        List<HttpMessageConverter<?>> partConverterList = new ArrayList<HttpMessageConverter<?>>();
//		        partConverterList.add(new ByteArrayHttpMessageConverter());
//		        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName(cahrSet));
//		        stringHttpMessageConverter.setWriteAcceptCharset(false);
//		        partConverterList.add(stringHttpMessageConverter);
//		        partConverterList.add(new ResourceHttpMessageConverter());
//		        partConverterList.add(new SourceHttpMessageConverter());
//		        if (ClassUtils.isPresent("javax.xml.bind.Binder", AllEncompassingFormHttpMessageConverter.class.getClassLoader())) {
//		            partConverterList.add(new Jaxb2RootElementHttpMessageConverter());
//		        }
//		        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", AllEncompassingFormHttpMessageConverter.class.getClassLoader())
//		                && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator", AllEncompassingFormHttpMessageConverter.class.getClassLoader())) {
//		            partConverterList.add(new MappingJackson2HttpMessageConverter());
//		        }
//		        else if (ClassUtils.isPresent("org.codehaus.jackson.map.ObjectMapper", AllEncompassingFormHttpMessageConverter.class.getClassLoader())
//		                && ClassUtils.isPresent("org.codehaus.jackson.JsonGenerator", AllEncompassingFormHttpMessageConverter.class.getClassLoader())) {
//		            partConverterList.add(new MappingJackson2HttpMessageConverter());
//		        }
//
//		        ((AllEncompassingFormHttpMessageConverter) hmc).setPartConverters(partConverterList);
//		    }
//		}
		
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
		try {
			HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(REST_BASE_URI + pathParam);
		    for( Map.Entry<String, String> elem : param.entrySet()) {
		    	builder.queryParam(elem.getKey(), elem.getValue());
		    }
		    URI uriParam = builder.build().encode("UTF-8").toUri();
		    ParameterizedTypeReference<Map<String, Object>> typeRef = 
					new ParameterizedTypeReference<Map<String, Object>>() {};
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Map<String, Object>> result = restTemplate.exchange(uriParam, EnumRestAPIType.method(), entity, 
					typeRef);
			Map<String, Object> body = result.getBody();
		
			return body;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}