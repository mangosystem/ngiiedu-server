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
import org.springframework.web.bind.annotation.PathVariable;
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
import kr.go.ngii.edu.main.modules.module.model.Module;
import kr.go.ngii.edu.main.schools.model.School;
import kr.go.ngii.edu.main.schools.service.SchoolService;
import kr.go.ngii.edu.main.users.model.User;

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
			@RequestParam(value="limit", required=false, defaultValue="10") Integer limit,
			@RequestParam(value="category", required=false, defaultValue="") String category,
			@RequestParam(value="keyword", required=false, defaultValue="") String keyword,
			@RequestParam(value="schoolLevel", required=false, defaultValue="") String schoolLevel,
			HttpSession session) throws Exception {
		
		List<School> list = schoolService.list(offset, limit, category, keyword, schoolLevel);
		
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}

	/**
	 * API 학교 목록 조회하기
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sync/api", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> apiList(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {
		
		String sPage="0";
		String sList="50";
		
		String addr = "http://api.data.go.kr/openapi/elesch-mskul-lc-std?serviceKey=";
		String serviceKey = "Yi8DoSjyAGhSiZy4cRAPT614KxQFYsGlhE%2Fh7WPOaG5A5pqT%2FYrHYDdwZ0Mefa%2B1Ducm62vuAUeg0nkY1%2BRZrw%3D%3D";
		String parameter = "";

		parameter = parameter + "&" + "s_page="+sPage;
		parameter = parameter + "&" + "s_list="+sList;
		parameter = parameter + "&" + "type=json";
		
		addr = addr + serviceKey + parameter;
		URL url = new URL(addr);
		
        // HTTP Connection 구하기 
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        conn.setRequestMethod("GET"); 
        
        // 연결 타임아웃 설정 
        conn.setConnectTimeout(3000); // 3초 
        // 읽기 타임아웃 설정 
        conn.setReadTimeout(3000); // 3초 
        
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
        ArrayList<HashMap<String,Object>> map = new ArrayList<HashMap<String,Object>>();
		
		try {

			ObjectMapper mapper = new ObjectMapper();

			// convert JSON string to Map
			map = mapper.readValue(result, new TypeReference<ArrayList<HashMap<String,Object>>>(){});
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<ResponseData>(responseBody(map), HttpStatus.OK);
	}
	
	/**
	 * API 동기화 
	 * 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sync/apiupload", method=RequestMethod.POST)
			public ResponseEntity<ResponseData> insertAPI(		
			@RequestParam(value="editColumn[]", required=true, defaultValue="0") List<String> editColumn
			) throws Exception {
		
		List<String> apiColumn = new ArrayList<String>();
	
		String[] dbColumn = {
	        "schoolId",
	        "schoolName",
	        "schoolLevel",
	        "schoolStatus",
	        "schoolEduOfficeName",
	        "schoolEduOfficeCode",
	        "schoolSidoOfficeName",
	        "schoolSidoOfficeCode",
	        "schoolAddr",
	        "schoolAddrRoad",
	        "schoolBuildDate",
	        "schoolEstablishType",
	        "schoolLat",
	        "schoolLon",
	        "schoolBranchType",
	        "schoolReferenceDate",
	        "schoolDataCreateDate",
	        "schoolDateEditDate"
	     };
		
		//시간측정
		long start = System.currentTimeMillis();

		
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

		//         HTTP Connection 구하기 
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        
        // 요청 방식 설정 ( GET or POST or .. 별도로 설정하지않으면 GET 방식 )
        conn.setRequestMethod("GET"); 
        
        // 연결 타임아웃 설정 
        conn.setConnectTimeout(3000); // 3초 
        // 읽기 타임아웃 설정 
        conn.setReadTimeout(3000); // 3초 

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
        
        ArrayList<HashMap<String,String>> map = new ArrayList<HashMap<String,String>>();
		
		try {

			ObjectMapper mapper = new ObjectMapper();

			// convert JSON string to Map
			map = mapper.readValue(result, new TypeReference<ArrayList<HashMap<String,String>>>(){});
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//기존 데이터 개수
		int oldRowCount = schoolService.count();
		//api 데이터 개수
		int apiRowCount =map.size();
		System.out.println("schoolServiceCount : "+schoolService.count());
		
		for(int i=0;i<map.size();i++){
			
			
			schoolService.createAPI(
					map.get(i).get(editColumn.get(0)),
					map.get(i).get(editColumn.get(1)),
					map.get(i).get(editColumn.get(2)),
					map.get(i).get(editColumn.get(3)),
					map.get(i).get(editColumn.get(4)),
					Integer.parseInt(map.get(i).get(editColumn.get(5)).toString()),
					map.get(i).get(editColumn.get(6)),
					Integer.parseInt(map.get(i).get(editColumn.get(7)).toString()),
					map.get(i).get(editColumn.get(8)),
					map.get(i).get(editColumn.get(9)),
					map.get(i).get(editColumn.get(10)),
					map.get(i).get(editColumn.get(11)),
					map.get(i).get(editColumn.get(12)),
					map.get(i).get(editColumn.get(13)),
					map.get(i).get(editColumn.get(14)),
					map.get(i).get(editColumn.get(15)),
					map.get(i).get(editColumn.get(16)),
					map.get(i).get(editColumn.get(17))
			);
		}
		
		int resultRowCount = schoolService.count();
		
		//중복 데이터 수
		int overlapRow = oldRowCount+ apiRowCount -resultRowCount;
		//신규 데이터 수
		int newRow = apiRowCount - overlapRow ;
		System.out.println("schoolServiceCount : "+schoolService.count());
		System.out.println("overlap : " + overlapRow);
		System.out.println("newRow : " + newRow);
		
		//중복신규데이터를 보내기
        HashMap<String,Integer> returnValue = new HashMap<String,Integer>();
        returnValue.put("overlapRow",overlapRow);
        returnValue.put("newRow",newRow);

        //끝시간
        long end = System.currentTimeMillis();
        System.out.println( "실행 시간 : " + ( end - start )/1000.0 );
        
		return new ResponseEntity<ResponseData>(responseBody(returnValue), HttpStatus.OK);
		
	}
	
	/**
	 * 인증키 재발급
	 * 
	 * @param idx
	 * 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/{idx}/authkey/modify", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> modifyAuthkey(
			@PathVariable(value="idx") Integer idx
			) throws Exception {
		System.out.println("idx : "+idx);
		schoolService.modifyAuthkey(idx);
		String schoolAuthkey = schoolService.getAuthkey(idx);
		return new ResponseEntity<ResponseData>(responseBody(schoolAuthkey), HttpStatus.OK);
	}
	
	/**
	 * 인증키 조회
	 * 
	 * @param idx
	 * 
	 * @return 
	 * @throws Exception
	 */
	@RequestMapping(value="/{idx}/authkey", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getAuthkey(
			@PathVariable(value="idx") Integer idx
			) throws Exception {
		System.out.println("idx : "+idx);
		String schoolAuthkey = schoolService.getAuthkey(idx);
		return new ResponseEntity<ResponseData>(responseBody(schoolAuthkey), HttpStatus.OK);
	}
	
	/**
	 * 학교정보 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{schoolId}", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> get(
			@PathVariable("schoolId") Integer schoolId,
			HttpSession session) throws Exception {

		School list = schoolService.get(schoolId);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
	/**
	 * 학교 목록 삭제하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{schoolId}", method=RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<ResponseData> delete(
			@PathVariable("schoolId") Integer schoolId,
			HttpSession session) throws Exception {

		boolean result = schoolService.delete(schoolId);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 학교 목록 생성하기
	 * 
	 * @param 
	 * @param 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<ResponseData> create(
			@RequestParam(value="schoolId", required=false) String schoolId, 
			@RequestParam(value="schoolName", required=false) String schoolName,
			@RequestParam(value="schoolLevel", required=false) String schoolLevel,
			@RequestParam(value="schoolStatus", required=false) String schoolStatus,
			@RequestParam(value="schoolEduOfficeName", required=false) String schoolEduOfficeName,
			@RequestParam(value="schoolEduOfficeCode", required=false) Integer schoolEduOfficeCode,
			@RequestParam(value="schoolSidoOfficeName", required=false) String schoolSidoOfficeName,
			@RequestParam(value="schoolSidoOfficeCode", required=false) Integer schoolSidoOfficeCode,
			@RequestParam(value="schoolAddr", required=false) String schoolAddr,
			@RequestParam(value="schoolBuildDate", required=false) String schoolBuildDate,
			@RequestParam(value="schoolEstablishType", required=false) String schoolEstablishType,
			@RequestParam(value="schoolLat", required=false) String schoolLat,
			@RequestParam(value="schoolLon", required=false) String schoolLon,
			@RequestParam(value="schoolBranchType", required=false) String schoolBranchType,
			@RequestParam(value="schoolAddrRoad", required=false) String schoolAddrRoad,
			@RequestParam(value="schoolRefDate", required=false) String schoolRefDate,
			@RequestParam(value="schoolCreateDate", required=false) String schoolCreateDate,
			@RequestParam(value="schoolEditDate", required=false) String schoolEditDate,
			HttpSession session) throws Exception {
		        
		School result = schoolService.create(schoolId, schoolName, schoolLevel, schoolStatus, schoolEduOfficeName, schoolEduOfficeCode, schoolSidoOfficeName, schoolSidoOfficeCode, schoolAddr,
				schoolBuildDate, schoolEstablishType, schoolLat, schoolLon, schoolBranchType, schoolAddrRoad, schoolRefDate, schoolCreateDate, schoolEditDate);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	

	/**
	 * 학교 목록 변경하기
	 * 
	 * @param idx
	 * @param 
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/{idx}", method=RequestMethod.PUT)
	public @ResponseBody ResponseEntity<ResponseData> modify(
			@RequestParam(value="idx", required=false) Integer idx, 
			@RequestParam(value="schoolId", required=false) String schoolId, 
			@RequestParam(value="schoolName", required=false) String schoolName,
			@RequestParam(value="schoolLevel", required=false) String schoolLevel,
			@RequestParam(value="schoolStatus", required=false) String schoolStatus,
			@RequestParam(value="schoolEduOfficeName", required=false) String schoolEduOfficeName,
			@RequestParam(value="schoolEduOfficeCode", required=false) Integer schoolEduOfficeCode,
			@RequestParam(value="schoolSidoOfficeName", required=false) String schoolSidoOfficeName,
			@RequestParam(value="schoolSidoOfficeCode", required=false) Integer schoolSidoOfficeCode,
			@RequestParam(value="schoolAddr", required=false) String schoolAddr,
			@RequestParam(value="schoolBuildDate", required=false) String schoolBuildDate,
			@RequestParam(value="schoolEstablishType", required=false) String schoolEstablishType,
			@RequestParam(value="schoolLat", required=false) String schoolLat,
			@RequestParam(value="schoolLon", required=false) String schoolLon,
			@RequestParam(value="schoolBranchType", required=false) String schoolBranchType,
			@RequestParam(value="schoolAddrRoad", required=false) String schoolAddrRoad,
			@RequestParam(value="schoolReferenceDate", required=false) String schoolReferenceDate,
			@RequestParam(value="schoolDataCreateDate", required=false) String schoolDataCreateDate,
			@RequestParam(value="schoolDateEditDate", required=false) String schoolDateEditDate,
			HttpSession session) throws Exception {
		
		School result = schoolService.modify(idx, schoolId, schoolName, schoolLevel, schoolStatus, schoolEduOfficeName, schoolEduOfficeCode, schoolSidoOfficeName, schoolSidoOfficeCode, schoolAddr,
				schoolBuildDate, schoolEstablishType, schoolLat, schoolLon, schoolBranchType, schoolAddrRoad, schoolReferenceDate, schoolDataCreateDate, schoolDateEditDate);
		return new ResponseEntity<ResponseData>(responseBody(result), HttpStatus.OK);
	}
	
	/**
	 * 인증키로 학교정보 조회하기
	 * 
	 * @param idx
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/authkey/{schoolAuthkey}/get", method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<ResponseData> getToAuthkey(
			@PathVariable("schoolAuthkey") String schoolAuthkey,
			HttpSession session) throws Exception {

		School list = schoolService.get(schoolAuthkey);
		return new ResponseEntity<ResponseData>(responseBody(list), HttpStatus.OK);
	}
	
}
