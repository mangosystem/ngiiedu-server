package kr.go.ngii.edu.main.courses.work.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.common.enums.EnumWorkOutputType;
import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.service.CourseTeamMemberService;
import kr.go.ngii.edu.main.courses.work.mapper.WorkOutputMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

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
	private UserService userService;
	
	/**
	 * 결과물을 등록 한다.
	 * 
	 * @param workId
	 * @param moduleWorkIds
	 * @return
	 * @throws Exception
	 */
	public WorkOutput create(int courseWorkSubId, String outputDivision, Map<String, Object> createdPinogioResult,
			int userId, String outputType, boolean isShared, boolean isDone) {
		
		CourseWorkSub cwsParam = new CourseWorkSub();
		cwsParam.setIdx(courseWorkSubId);
		cwsParam = courseWorkSubService.get(cwsParam);
		int courseWorkId = cwsParam.getCourseWorkId();

		// course id 조회
		CourseWork cwParam = new CourseWork();
		cwParam.setIdx(courseWorkId);
		cwParam = courseWorkService.get(cwParam);
		int courseId = cwParam.getCourseId();
		
		// team id 조회
		CourseTeamMember ctm = courseTeamMemberService.getByCourseIdAndMemberId(courseId, userId);
		int temaId = 0;
		try {
			temaId = ctm.getTeamId();
		} catch (Exception e) {
		}
		
		// pinogio layer id
		Map<String, Object> resultData = (Map<String, Object>) createdPinogioResult.get("data");
		
		String pinogioIdColName = "";
		
		if ("maps".equals(outputType)) {
			pinogioIdColName = "mapsId";
		} else if ("layer".equals(outputType)) {
			pinogioIdColName = "layerId";
		} else if ("dataset".equals(outputType)) {
			pinogioIdColName = "datasetId";
		}
		
		try {
			String createdPinogioId = (String) resultData.get(pinogioIdColName).toString();
			String createdTitle = (String) resultData.get("title").toString();
			WorkOutput woParam = new WorkOutput();
			woParam.setCourseWorkSubId(courseWorkSubId);
			woParam.setOutputDivision(outputDivision);
			woParam.setPinogioOutputId(createdPinogioId);
			woParam.setOutputTeamId(temaId);
			woParam.setOutputUserid(userId);
			woParam.setOutputType(outputType);
			woParam.setPngoData(resultData);
			woParam.setOutputName(createdTitle);
			woParam.setIsShared(isShared);
			woParam.setIsDone(isDone);
			workOutputMapper.create(woParam);
//			woParam.getPinogioOutputId();
			return woParam;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}
	
	
	public WorkOutput create(int courseId, int courseWorkSubId, String outputDivision, Map<String, Object> createdPinogioResult,
			int userId, String outputType, boolean isShared, boolean isDone) {
		CourseTeamMember ctm = courseTeamMemberService.getByCourseIdAndMemberId(courseId, userId);
		int temaId = 0;
		try {
			temaId = ctm.getTeamId();
		} catch (Exception e) {
		}
		String pinogioIdColName = "";
		
		if ("maps".equals(outputType)) {
			pinogioIdColName = "mapsId";
		} else if ("layer".equals(outputType)) {
			pinogioIdColName = "layerId";
		} else if ("dataset".equals(outputType)) {
			pinogioIdColName = "datasetId";
		}
		
		try {
			String createdPinogioId = (String) createdPinogioResult.get(pinogioIdColName).toString();
			String createdTitle = (String) createdPinogioResult.get("title").toString();
			WorkOutput woParam = new WorkOutput();
			woParam.setCourseWorkSubId(courseWorkSubId);
			woParam.setOutputDivision(outputDivision);
			woParam.setPinogioOutputId(createdPinogioId);
			woParam.setOutputTeamId(temaId);
			woParam.setOutputUserid(userId);
			woParam.setOutputType(outputType);
			woParam.setPngoData(createdPinogioResult);
			woParam.setOutputName(createdTitle);
//			woParam.setShared("true".equals(isShared));
//			woParam.setDone("true".equals(isDone));
			woParam.setIsShared(isShared);
			woParam.setIsDone(isDone);
			workOutputMapper.create(woParam);
//			woParam.getPinogioOutputId();
			return woParam;
		} catch (Exception e) {
			throw new RuntimeException(ErrorMessage.SERVER_ERROR);
		}
	}
	
	public WorkOutput get(WorkOutput workOutput) {
		workOutput =  workOutputMapper.get(workOutput);
		Object pngoData = this.requestPngoData(workOutput.getPinogioOutputId(), workOutput.getOutputType());
		workOutput.setPngoData(pngoData);
		String outputName = ((LinkedHashMap<String, String>) pngoData).get("title");
		workOutput.setOutputName(outputName);
		return workOutput;
	}
	
	public WorkOutput get(int idx) {
		WorkOutput param = new WorkOutput();
		param.setIdx(idx);
		return get(param);
	}
	
	public WorkOutput getByPinogioOutputId(String pinogioOutputId) {
		WorkOutput param = new WorkOutput();
		param.setPinogioOutputId(pinogioOutputId);
		return get(param);
	}
	
	public List<WorkOutput> getList() {
		return workOutputMapper.getList(new WorkOutput());
	}
	
	public List<WorkOutput> getGalleryList(int offset, int limit) {
		return workOutputMapper.getGalleryList(offset, limit);
	}
	
	public List<WorkOutput> getList(WorkOutput workOutput) {
		return workOutputMapper.getList(workOutput);
	}
	
	public void modify(WorkOutput workOutput) {
		workOutputMapper.modify(workOutput);
	}
	
	public WorkOutput modifyShare(String pinogioId, boolean isShared) {
		WorkOutput params = new WorkOutput();
		params.setPinogioOutputId(pinogioId);
		params.setIsShared(isShared);
		workOutputMapper.modifyStatus(params);
		return params;
		
	}
	
	public WorkOutput modifyDone(String pinogioId, boolean isDone) {
		WorkOutput params = new WorkOutput();
		params.setPinogioOutputId(pinogioId);
		params.setIsDone(isDone);
		workOutputMapper.modifyStatus(params);
		return params;
	}
	
	
	public boolean delete(int idx) {
		if (workOutputMapper.exists(idx)) {
			workOutputMapper.delete(idx);
			return true;
		} else {
			return false;
		}
	}
	
	public List<WorkOutput> getListByCourseWorkId(int courseWorkId) {
		return workOutputMapper.getListByCourseWorkId(courseWorkId);
	}
	
	private Object requestPngoData(String pngoId, String outputType) {
		RestAPIClient rc = new RestAPIClient();
		String apiKey = "";
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			apiKey = userService.getApiKey(user.getIdx());
			rc.setApiKey(apiKey);
		} catch (NullPointerException e) {
		}
		if ("layer".equals(outputType)) {
			Map<String, Object> r;
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.LAYER.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams, null);
			return r.get("data");
		} else if ("maps".equals(outputType)) {
			Map<String, Object> r;
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.MAPS.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams, null);
			return r.get("data");
		} else if ("dataset".equals(outputType)) {
			Map<String, Object> r;
			Map<String, String> uriParams = new HashMap<String, String>();
			uriParams.put(EnumWorkOutputType.DATASET.idField(), pngoId);
			r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams, null);
			return r.get("data");
		}
		return null;
	}
}
