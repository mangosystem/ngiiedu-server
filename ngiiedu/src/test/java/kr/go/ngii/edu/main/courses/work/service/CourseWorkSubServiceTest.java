package kr.go.ngii.edu.main.courses.work.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.common.enums.EnumWorkOutputType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

public class CourseWorkSubServiceTest extends BaseTest {

	@Autowired
	private CourseWorkSubService courseWorkSubService;

	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;

	@Test
	public void testCourseWorkSubList() {
//		CourseWork courseWork = new CourseWork();
//		courseWork.setModuleWorkId(3);
//		List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(courseWork);
//
//		System.out.println("size : " + list.size());
//
//		for (CourseWorkSubOutputWithModuleWorkSub m : list) {
//			System.out.println(m.getModuleWorkSubName());
//
//			List<CourseWorkSubOutputInfo> l = m.getCourseWorkSubOutputInfoList();
//
//			System.out.println("------------------------------------------");
//
//			for (CourseWorkSubOutputInfo ll : l) {
//				System.out.println(ll.getPinogioOutputId());
//				System.out.println(ll.getOutputName());
//			}
//			System.out.println("------------------------------------------");
//			System.out.println();
//
//		}
	}

	@Test
	public void testCourseWorkSubList2() {

//		CourseWork param = new CourseWork();
//		param.setIdx(12);
//		param = courseWorkService.get(param);
//
//		List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(12);
//
//		System.out.println("size : " + list.size());
//
//		for (CourseWorkSubOutputWithModuleWorkSub m : list) {
//			System.out.println(m.getModuleWorkSubName());
//
//			List<CourseWorkSubOutputInfo> l = m.getCourseWorkSubOutputInfoList();
//
//			System.out.println("------------------------------------------");
//
//			for (CourseWorkSubOutputInfo ll : l) {
//				System.out.println(ll);
//			}
//			System.out.println("------------------------------------------");
//			System.out.println();
//
//		}

	}

	private RestTemplate getRestTempalte() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(1000 * 60 * 5); // 5분
		factory.setConnectTimeout(5000);
		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}
	
	private String jsonStringFromObject(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

	@Test
	public void testRestApi1() throws Exception {
		
		RestTemplate restTemplate = new RestTemplate();
		java.util.Map<String, String> vars = new HashMap<String, String>();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		vars.put("project_id", "p=pppppppp");
//		String result = restTemplate.getForObject("http://1.234.82.19:8083/pinogio-web/api/v1/projects/{project_id}.json", String.class, vars);
		Map<String, Object> result = restTemplate.getForObject("http://1.234.82.19:8083/pinogio-web/api/v1/projects/{project_id}.json", Map.class, vars);
	
//		System.out.println(result);
		System.out.println(result.get("data"));
		
	}
	
	private HttpEntity<?> apiClientHttpEntity(String appType, String params) {
        
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Content-Type", "application/" + appType);
         
        if ( "".equals(params) || StringUtils.isEmpty(params) )
            return new HttpEntity<Object>(requestHeaders);
        else
            return new HttpEntity<Object>(params, requestHeaders);
    }
	
	 private String getApiServerUrl() {
		 return "http://http://1.234.82.19:8083/pinogio-web/api/v1";
	 }



	@Test
	public void testRestApi2() throws Exception {  

		
		String url = "http://1.234.82.19:8083/pinogio-web/api/v1/datasets/{dataset_id}.json";
		RestTemplate restTemplate = new RestTemplate();
	    
//	    HttpHeaders headers = new HttpHeaders();
//	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Accept", "application/json");
		
		
	    Map<String, String> uriParams = new HashMap<String, String>();
	    uriParams.put("dataset_id", "d=r7oFXBrCYl");
	    
	    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url)
	            // Add query parameter
	            .queryParam("description", "aa")
	            .queryParam("metadata", "aa");
	    
	    
//	    ParameterizedTypeReference<List<PngoProject>> typeRef = 
//	    		new ParameterizedTypeReference<List<PngoProject>>() {};
		ParameterizedTypeReference<Map<String, Object>> typeRef = 
				new ParameterizedTypeReference<Map<String, Object>>() {};
		
	    
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
//	    HttpEntity<String> entity = new HttpEntity<String>(headers);
	    ResponseEntity<Map<String, Object>> result = 
	    		restTemplate.exchange(builder.buildAndExpand(uriParams).toUri() , HttpMethod.PUT, entity, typeRef);

	    Map<String, Object> r = result.getBody();
	    
	    System.out.println(r.size());
	    
//	    for (PngoProject p : r) {
//	    	System.out.println(p.getId() + ", " +p.getProjectId());
//	    }
	    System.out.println(result.getBody().get("data"));
	    
//	    PngoProject pp = new PngoProject();
//	    pp = StringUtil.convertMapToObject(result.getBody().get("data"), pp);
	}
	
	@Test
	public void testRestApi3() {
		System.out.println(LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		System.out.println(LocalResourceBundle.PINOGIO_API_SERVER);
		Map<String, String> uriParams = new HashMap<String, String>();
	    uriParams.put("dataset_id", "d=r7oFXBrCYl");
		RestAPIClient rc = new RestAPIClient();
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams);
		System.out.println(r.get("data"));
		List<Map<String, Object>> ra = (List<Map<String, Object>>) r.get("data");
		Map<String, Object> r1 = ra.get(0);
		System.out.println(r1);
		
		
		System.out.println(r1.get("id").toString());
		System.out.println(r1.get("projectId"));
		System.out.println(r1.get("datasetId"));
		
//		List<HashMap<String,Object>> map = new ArrayList<HashMap<String,Object>>();
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//			map = mapper.readValue(r.get("data").toString(), new TypeReference<ArrayList<HashMap<String,Object>>>(){});
//			System.out.println(map.size());
//			System.out.println(map.get(0).get("id"));
//			
//		} catch (JsonParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (JsonMappingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void testCourseWorkSubList3() {

//		CourseWork param = new CourseWork();
//		param.setIdx(12);
//		param = courseWorkService.get(param);
//
//		List<CourseWorkSubOutputWithModuleWorkSub> list = courseWorkSubService.list(12);
//
//		System.out.println("size : " + list.size());
//
//		for (CourseWorkSubOutputWithModuleWorkSub m : list) {
//			System.out.println(m.getModuleWorkSubName());
//
//			List<CourseWorkSubOutputInfo> l = m.getCourseWorkSubOutputInfoList();
//
//			System.out.println("------------------------------------------");
//			RestAPIClient rc = new RestAPIClient();
//
//			for (CourseWorkSubOutputInfo ll : l) {
//				Map<String, String> uriParams = new HashMap<String, String>();
//				if("dataset".equals(ll.getOutput_type())) {
//					uriParams.put("dataset_id", ll.getPinogioOutputId());
//					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams);
//					ll.setPngoData(r.get("data"));
//					System.out.println(r.get("data"));
//				} else if("layer".equals(ll.getOutput_type())) {
//					uriParams.put("layer_id", ll.getPinogioOutputId());
//					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams);
//					ll.setPngoData(r.get("data"));
//					System.out.println(r.get("data"));
//				} else if("maps".equals(ll.getOutput_type())) {
//					uriParams.put("maps_id", ll.getPinogioOutputId());
//					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams);
//					ll.setPngoData(r.get("data"));
//					System.out.println(r.get("data"));
//				}
//			}
//			System.out.println("------------------------------------------");
//			System.out.println();
//		}
//		System.out.println(list);

	}
	
	@Test 
	public void ttt () {
		
		Map<String, String> sourceParam = new HashMap<String, String>();
		sourceParam.put("type", "dataset");
		String aa = sourceParam.get("a");
		System.out.println(aa);
	}
	
	@Test 
	public void createCourseWorkSub () {
		
		String layerTitle = "aaa";
		int courseWorkId = 12;
		int courseWorkSubId = 2;
		int moduleWorkSubId = 2;
		
		// dataset 조회
		List<WorkOutput> workOutputList = workOutputService.getListByCourseWorkId(courseWorkId);
		System.out.println(workOutputList);

		int workOutputListSize = workOutputList.size();
		String pinogioDatasetId = "";
		if (workOutputListSize > 0) {
			// workOutputListSize 은 dataset의 수 
			// workOutputList 가 여러개일떄 처리 핗요함
			pinogioDatasetId = workOutputList.get(0).getPinogioOutputId();
		} else {
			// dataset이 없습니다
			System.out.println("dataset 없음!");
		}
		
		System.out.println("pinogioDatasetId : " + pinogioDatasetId);

		// pinogio create
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> contentParams = new HashMap<String, Object>();
        Map<String, Object> sourceParam = new HashMap<String, Object>();
        ArrayNode filterArray = mapper.createArrayNode();
        contentParams.put("type", "dataset");
        // pinogioDatasetId
        contentParams.put("datasetId", "d=r7oFXBrCYl");
        contentParams.put("filter", filterArray);
        sourceParam.put("inputDataset", contentParams);
        
        Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		params.put("title", layerTitle);
		params.put("sources", StringUtil.mapToString(sourceParam));
		
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
		System.out.println(r);
		Map resultMetaStatus = (Map)r.get("meta");
		System.out.println("resultMetaStatus : " + resultMetaStatus.get("code"));
				
		// work_output
		WorkOutput workOutput = new WorkOutput();
		workOutput.setCourseWorkSubId(courseWorkSubId);
		workOutput.setOutputUserid(1);
		workOutput.setOutputTeamId(1);
		workOutput.setOutputDivision("1");
	}
	
	@Test
	public void workCourseSubListTest() {
		
		List<CourseWorkSubInfo> list = courseWorkSubService.list(28);
		
		for (CourseWorkSubInfo cwsi : list) {
			List<WorkOutput> workOutputList = cwsi.getWorkOutputList();
			for (WorkOutput wo : workOutputList) {
				Map<String, String> uriParams = new HashMap<String, String>();
				Object pngoData = this.requestPngoData(wo.getPinogioOutputId(), wo.getOutputType());
				wo.setPngoData(pngoData);
				try {
					String outputName = ((LinkedHashMap<String, String>) pngoData).get("title");
					wo.setOutputName(outputName);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(list);
	}
	
	private Object requestPngoData(String pngoId, String outputType) {
		if ("layer".equals(outputType)) {
			Map<String, Object> r;
			RestAPIClient rc = new RestAPIClient();
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.LAYER.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams);
			return r.get("data");
		} else if ("maps".equals(outputType)) {
			Map<String, Object> r;
			RestAPIClient rc = new RestAPIClient();
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.MAPS.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams);
			return r.get("data");
		} else if ("dataset".equals(outputType)) {
			Map<String, Object> r;
			RestAPIClient rc = new RestAPIClient();
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.DATASET.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams);
			return r.get("data");
		}
		return null;
	}
	
	@Test
	public void tt() {
		System.out.println(EnumWorkOutputType.valueOf(EnumWorkOutputType.findKey("layer")).idField());
	}
}
