package kr.go.ngii.edu.main.courses.work.service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import kr.go.ngii.edu.BaseTest;
import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.service.CourseTeamMemberService;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.mapper.WorkOutputMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;


public class WorkOutputServiceTest extends BaseTest{
	
	@Autowired
	private CourseWorkSubService courseWorkSubService;

	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;
	
	@Autowired
	private CourseTeamMemberService courseTeamMemberService;
	
	@Autowired
	private WorkOutputMapper workOutputMapper;
	
	
	@Test
	public void layerCreateTest() {
//		int courseWorkSubId, int outputUserid, String outputType, String title
		
		// 레이어 생성 테스트
		int courseWorkSubId = 2;
		int outputUserid = 1;
		String outputType = "layer";
		String title = "create test";
		
		// session 에서 가져온 user id 에서 team id 조회
		// course work id 조회
		CourseWorkSub cwsParam = new CourseWorkSub();
		cwsParam.setIdx(courseWorkSubId);
		cwsParam = courseWorkSubService.get(cwsParam);
		System.out.println("course work id : " + cwsParam.getCourseWorkId());
		int courseWorkId = cwsParam.getCourseWorkId();
		
		// course id 조회
		CourseWork cwParam = new CourseWork();
		cwParam.setIdx(cwsParam.getCourseWorkId());
		cwParam = courseWorkService.get(cwParam);
		System.out.println("course id : " + cwParam.getCourseId());
		int courseId = cwParam.getCourseId();
		
		System.out.println("courseId : " + courseId + ", outputUserid : " + outputUserid);
		// team id 조회
		CourseTeamMember ctm = courseTeamMemberService.getByCourseIdAndMemberId(courseId, outputUserid);
		int temaId = ctm.getTeamId();
		System.out.println("temaId : " + temaId);
		// output division => team? 개인?
		String outputDivision = "1";
		
		// pinogio id 생성
		RestAPIClient rc = new RestAPIClient();
		ObjectMapper mapper = new ObjectMapper();
		
		String createdPinogioId = "";
		if ("layer".equals(outputType.trim().toLowerCase())) {
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
			Map<String, String> uriParams = new HashMap<String, String>();
			Map<String, String> params = new HashMap<String, String>();
			params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
			params.put("title", title);
	        Map<String, Object> inputDatasetParam = new HashMap<>();
	        Map<String, Object> sourcesParam = new HashMap<>();
	        ArrayNode filterArray = mapper.createArrayNode();
	        inputDatasetParam.put("type", "dataset");
	        inputDatasetParam.put("datasetId", "d=r7oFXBrCYl");
	        inputDatasetParam.put("filter", filterArray);
	        sourcesParam.put("inputDataset", inputDatasetParam);
			params.put("sources", StringUtil.mapToString(sourcesParam));
			Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
			Map<String, Object> resultData = (Map<String, Object>) r.get("data");
			Map<String, Object> resultCode = (Map<String, Object>) r.get("meta");
			String resultCodeStatus = (String) resultCode.get("code").toString();
			System.out.println("resultCodeStatus : " + resultCodeStatus);
			createdPinogioId = (String) resultData.get("layerId").toString();
			System.out.println("resultPinogioId : " + createdPinogioId);
		} else if ("dataset".equals(outputType.trim().toLowerCase())) {
			
			
		} else if ("maps".equals(outputType.trim().toLowerCase())) {
			// items는 추후 등록?
		}
		
		WorkOutput woParam = new WorkOutput();
		woParam.setCourseWorkSubId(courseWorkSubId);
		woParam.setOutputDivision(outputDivision);
		woParam.setPinogioOutputId(createdPinogioId);
		woParam.setOutputTeamId(temaId);
		woParam.setOutputUserid(outputUserid);
		woParam.setOutputType(outputType);
		
		workOutputMapper.create(woParam);
		System.out.println("--------------------");
		System.out.println(woParam);
	}
	
	
	@Test
	public void layerDeleteTest() {
//		int courseWorkSubId, int outputUserid, String outputType, String title
		
		// 레이어 생성 테스트
		int courseWorkSubId = 2;
		int outputUserid = 1;
		String outputType = "layer";
		String title = "create test";
		
		// session 에서 가져온 user id 에서 team id 조회
		// course work id 조회
		CourseWorkSub cwsParam = new CourseWorkSub();
		cwsParam.setIdx(courseWorkSubId);
		cwsParam = courseWorkSubService.get(cwsParam);
		System.out.println("course work id : " + cwsParam.getCourseWorkId());
		int courseWorkId = cwsParam.getCourseWorkId();
		// course id 조회
		CourseWork cwParam = new CourseWork();
		cwParam.setIdx(cwsParam.getCourseWorkId());
		cwParam = courseWorkService.get(cwParam);
		System.out.println("course id : " + cwParam.getCourseId());
		int courseId = cwParam.getCourseId();
		
		System.out.println("courseId : " + courseId + ", outputUserid : " + outputUserid);
		// team id 조회
		CourseTeamMember ctm = courseTeamMemberService.getByCourseIdAndMemberId(courseId, outputUserid);
		int temaId = ctm.getTeamId();
		System.out.println("temaId : " + temaId);
		// output division => team? 개인?
		String outputDivision = "1";
		
		// pinogio id 생성
		RestAPIClient rc = new RestAPIClient();
		ObjectMapper mapper = new ObjectMapper();
		
		String createdPinogioId = "";
		if ("layer".equals(outputType.trim().toLowerCase())) {
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
			Map<String, String> uriParams = new HashMap<String, String>();
			Map<String, String> params = new HashMap<String, String>();
			params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
			params.put("title", title);
	        Map<String, Object> inputDatasetParam = new HashMap<>();
	        Map<String, Object> sourcesParam = new HashMap<>();
	        ArrayNode filterArray = mapper.createArrayNode();
	        inputDatasetParam.put("type", "dataset");
	        inputDatasetParam.put("datasetId", "d=r7oFXBrCYl");
	        inputDatasetParam.put("filter", filterArray);
	        sourcesParam.put("inputDataset", inputDatasetParam);
			params.put("sources", StringUtil.mapToString(sourcesParam));
			Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
			Map<String, Object> resultData = (Map<String, Object>) r.get("data");
			Map<String, Object> resultCode = (Map<String, Object>) r.get("meta");
			String resultCodeStatus = (String) resultCode.get("code").toString();
			System.out.println("resultCodeStatus : " + resultCodeStatus);
			createdPinogioId = (String) resultData.get("layerId").toString();
			System.out.println("resultPinogioId : " + createdPinogioId);
		} else if ("dataset".equals(outputType.trim().toLowerCase())) {
			
			
		} else if ("maps".equals(outputType.trim().toLowerCase())) {
		}
		
		WorkOutput woParam = new WorkOutput();
		woParam.setCourseWorkSubId(courseWorkSubId);
		woParam.setOutputDivision(outputDivision);
		woParam.setPinogioOutputId(createdPinogioId);
		woParam.setOutputTeamId(temaId);
		woParam.setOutputUserid(outputUserid);
		woParam.setOutputType(outputType);
		
		workOutputMapper.create(woParam);
		System.out.println("--------------------");
		System.out.println(woParam);
	}
	
	@Test
	public void stringFormatTest() {
		
		
		Object[] params = new Object[]{"hello", "!"};
		String msg = MessageFormat.format("{0} world {1}", params);
		System.out.println(msg);
		
		
		String format = "My name is ${b}. ${a} ${b}.";

		Map<String, String> values = new HashMap<String, String>();
//		values.put("a", "James");
//		values.put("b", "Bond");

		System.out.println(format(format, values)); 
		
	}
	
	public static String format(String format, Map<String, String> values) {
	    StringBuilder formatter = new StringBuilder(format);
	    List<Object> valueList = new ArrayList<Object>();

	    Matcher matcher = Pattern.compile("\\$\\{(\\w+)}").matcher(format);

	    while (matcher.find()) {
	        String key = matcher.group(1);

	        String formatKey = String.format("${%s}", key);
	        int index = formatter.indexOf(formatKey);

	        if (index != -1) {
	            formatter.replace(index, index + formatKey.length(), "%s");
	            valueList.add(values.get(key));
	        }
	    }

	    return String.format(formatter.toString(), valueList.toArray());
	}
	
	
	
	
}
