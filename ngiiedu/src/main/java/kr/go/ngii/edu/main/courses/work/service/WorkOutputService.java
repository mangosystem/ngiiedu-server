package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.service.CourseTeamMemberService;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkMapper;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.mapper.WorkOutputMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Service
public class WorkOutputService extends BaseService {
	
	@Autowired
	private WorkOutputMapper workOutputMapper;
	
	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private CourseWorkSubService courseWorkSubService;
	
	@Autowired
	private CourseTeamMemberService courseTeamMemberService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
//	/**
//	 * 결과물을 등록 한다.
//	 * 
//	 * @param workId
//	 * @param moduleWorkIds
//	 * @return
//	 * @throws Exception
//	 */
//	public List<CourseWorkSubOutputInfo> create(int workId) throws Exception {
//
//		List<CourseWorkSubOutputInfo> createResult = new ArrayList<CourseWorkSubOutputInfo >();
//
//		CourseWork param = null;
//
////		for (int i=0; i<moduleWorkIds.size(); i++) {
////			param = new CourseWork();
////			param.setCourseId(courseId);
////			param.setModuleWorkId(moduleWorkIds.get(i));
////			param.setWorkSeq(i);
////			//param.setWorkTeamDivision(workTeamDivision);
////			param.setCreateDate(new Date());
////			param.setModifyDate(new Date());
////
////			workOutputMapper.create(param);
////
////			createResult.add(param);
////		}
//
//		return createResult;
//	}
	
	
	/**
	 * 결과물을 등록 한다.
	 * 
	 * @param workId
	 * @param moduleWorkIds
	 * @return
	 * @throws Exception
	 */
	public String create(int courseWorkSubId, int outputUserid, String outputType, String title) {
		
		// session 에서 가져온 user id 에서 team id 조회
		// course work id 조회
		CourseWorkSub cwsParam = new CourseWorkSub();
		cwsParam.setIdx(courseWorkSubId);
		courseWorkSubService.get(cwsParam);
		System.out.println("course work id : " + cwsParam.getCourseWorkId());
		int courseWorkId = cwsParam.getCourseWorkId();
		// course id 조회
		CourseWork cwParam = new CourseWork();
		cwParam.setIdx(cwsParam.getCourseWorkId());
		courseWorkService.get(cwParam);
		System.out.println("course id : " + cwParam.getCourseId());
		int courseId = cwParam.getCourseId();
		
		// team id 조회
		CourseTeamMember ctm = courseTeamMemberService.getByCourseIdAndMemberId(courseId, outputUserid);
		int temaId = ctm.getTeamId();
		
		// output division => team? 개인?
		String outputDivision = "1";
		
		// pinogio id 생성
		RestAPIClient rc = new RestAPIClient();
		ObjectMapper mapper = new ObjectMapper();
		
		String createdPinogioId = "";
		if ("layer".equals(outputType.trim().toLowerCase())) {
			// dataset 조회
			List<WorkOutput> workOutputList = workOutputService.getItemByCourseWorkId(courseWorkId);
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
			
			Map<String, String> uriParams = new HashMap<String, String>();
			Map<String, String> params = new HashMap<String, String>();
			params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
			params.put("title", title);
	        Map<String, Object> inputDatasetParam = new HashMap<>();
	        Map<String, Object> sourcesPaeam = new HashMap<>();
	        ArrayNode filterArray = mapper.createArrayNode();
	        inputDatasetParam.put("type", "dataset");
	        inputDatasetParam.put("datasetId", pinogioDatasetId);
	        inputDatasetParam.put("filter", filterArray);
	        sourcesPaeam.put("inputDataset", sourcesPaeam);
			params.put("sources", StringUtil.mapToString(sourcesPaeam));
			Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
			System.out.println(r);
//			createdPinogioId = ((Map)r.get("data")).get(key)
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
		//피노지오 id 리턴.
		return woParam.getPinogioOutputId();
	}
	
	
	public List<CourseWorkSubOutputInfo> list(CourseWorkSubOutputInfo workOutput) {
		return workOutputMapper.list(workOutput);
	}
	
	public CourseWorkSubOutputInfo get(CourseWorkSubOutputInfo workOutput) {
		return workOutputMapper.get(workOutput);
	}
	
	public CourseWorkSubOutputInfo modify(CourseWorkSubOutputInfo workOutput) {
		CourseWorkSubOutputInfo params = new CourseWorkSubOutputInfo();
//		params.setIdx(idx);
//		params.setStatus(status);
//		params.setModifyDate(new Date());
//		workOutputMapper.modify(params);
		return params;
	}
	
	public void delete(CourseWorkSubOutputInfo workOutput) {
		workOutputMapper.delete(1);
	}
	
	public List<WorkOutput> getItemByCourseWorkId(int courseWorkId) {
		return workOutputMapper.getItemByCourseWorkId(courseWorkId);
	}
	
	
	
}
