package kr.go.ngii.edu.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.ngii.edu.controller.rest.BaseController;
import kr.go.ngii.edu.controller.rest.ResponseData;
import kr.go.ngii.edu.main.schools.model.School;
import kr.go.ngii.edu.main.schools.service.SchoolService;

@Controller
@RequestMapping("/api/v1/schools")
public class SchoolController extends BaseController{
	@Autowired
	private SchoolService schoolService;


	/**
	 * 학교 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> list(
			@RequestParam(value="offset", required=false, defaultValue="0") Integer offset, 
			@RequestParam(value="limit", required=false, defaultValue="0") Integer limit,
			HttpSession session) throws Exception {

		List<School> list = null;
//		list = schoolService.list();

		if (offset==0 && limit==0) {
			list = schoolService.list();

		} else {
			list = schoolService.list(offset, limit);
		}

		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * 학교 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/api", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> apiList(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String sPage="0";
		String sList="100";
		
		String addr = "http://api.data.go.kr/openapi/elesch-mskul-lc-std?serviceKey=";
		String serviceKey = "Yi8DoSjyAGhSiZy4cRAPT614KxQFYsGlhE%2Fh7WPOaG5A5pqT%2FYrHYDdwZ0Mefa%2B1Ducm62vuAUeg0nkY1%2BRZrw%3D%3D";
		String parameter = "";

		parameter = parameter + "&" + "s_page="+sPage;
		parameter = parameter + "&" + "s_list="+sList;
		parameter = parameter + "&" + "type=json";
		
		addr = addr + serviceKey + parameter;
		URL url = new URL(addr);
		
	    // 문자열로 URL 표현
        System.out.println("URL :" + url.toExternalForm());
        
        // HTTP Connection 구하기 
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        conn.setRequestMethod("GET"); 
        
        // 연결 타임아웃 설정 
        conn.setConnectTimeout(3000); // 3초 
        // 읽기 타임아웃 설정 
        conn.setReadTimeout(3000); // 3초 
        
        // 요청 방식 구하기
        System.out.println("getRequestMethod():" + conn.getRequestMethod());
        // 응답 콘텐츠 유형 구하기
        System.out.println("getContentType():" + conn.getContentType());
        // 응답 코드 구하기
        System.out.println("getResponseCode():"    + conn.getResponseCode());
        // 응답 메시지 구하기
        System.out.println("getResponseMessage():" + conn.getResponseMessage());
        
        
        // 응답 헤더의 정보를 모두 출력
        for (Map.Entry<String, List<String>> header : conn.getHeaderFields().entrySet()) {
            for (String value : header.getValue()) {
                System.out.println(header.getKey() + " : " + value);
            }
        }
        String result;
        // 응답 내용(BODY) 구하기        
        try (InputStream in = conn.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            
            byte[] buf = new byte[1024 * 8];
            int length = 0;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            result = new String(out.toByteArray());
        }
        
        System.out.println(result);
		
		
		
        ArrayList<HashMap<String,Object>> map = new ArrayList<HashMap<String,Object>>();
		
		try {

			ObjectMapper mapper = new ObjectMapper();
//			String json = "{\"name\":\"mkyong\", \"age\":29}";

//			List<Map<String, Object>> map = new ArrayList<HashMap<String, Object>>();

			// convert JSON string to Map
			map = mapper.readValue(result, new TypeReference<ArrayList<HashMap<String,Object>>>(){});
			
			System.out.println(map.get(0).toString());
			System.out.println(map.toString());
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Iterator iterator = map.get(0).entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry entry = (Entry)iterator.next();
			System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
		}

		
		return new ResponseEntity<ResponseData>(responseBody(map), HttpStatus.OK);
		
		
		
	}



}
