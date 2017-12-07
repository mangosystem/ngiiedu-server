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
	
	/**
	 * 결과물을 등록 한다.
	 * 
	 * @param workId
	 * @param moduleWorkIds
	 * @return
	 * @throws Exception
	 */
	public WorkOutput create(int courseWorkSubId, String outputDivision, Map<String, Object> createdPinogioResult,
			int userId, String outputType) {
		
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
			LOGGER.debug("Team Id 없음");
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
	
	public List<WorkOutput> getList(WorkOutput workOutput) {
		return workOutputMapper.getList(workOutput);
	}
	
	public WorkOutput modify(WorkOutput workOutput) {
		WorkOutput params = new WorkOutput();
		workOutputMapper.modify(params);
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
}
