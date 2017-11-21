package kr.go.ngii.edu.main.courses.work.service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.common.enums.EnumWorkOutputType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Service
public class CourseWorkSubService extends BaseService {

	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;
	
	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
	public CourseWorkSub get(CourseWorkSub courseWorkSub) {
		return courseWorkSubMapper.get(courseWorkSub);
	}
	
	public List<CourseWorkSubInfo> list(int courseWorkId) {
		
		// Module Work Id 조회
		CourseWork param = new CourseWork();
		param.setIdx(courseWorkId);
		param = courseWorkService.get(param);
		
		// CourseWorkSubInfo 목록 조회
		List<CourseWorkSubInfo> list = courseWorkSubMapper.list(param);
		
		for (CourseWorkSubInfo cwsi : list) {
			List<WorkOutput> workOutputList = cwsi.getWorkOutputList();
			for (WorkOutput wo : workOutputList) {
				Object pngoData = this.requestPngoData(wo.getPinogioOutputId(), wo.getOutputType());
				wo.setPngoData(pngoData);
				try {
					String outputName = 
							((LinkedHashMap<String, String>) pngoData).get("title");
					wo.setOutputName(outputName);
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public WorkOutput create(int courseWorkId, int moduleWorkSubId, int userId, 
			int teamId, String outputTypem, String layerTitle) {
		
		// dataset 조회
		List<WorkOutput> workOutputList = workOutputService.getListByCourseWorkId(courseWorkId);
		
		// dataset의 pinogio Id
		String datasetPinogioId = workOutputList.get(0).getPinogioOutputId();
		
		// pinogio create
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> uriParams = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
		params.put("title", layerTitle);
		params.put("source", "");
		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
		// course_work_sub
//		courseWorkSubMapper.create()
		// work_output
		return null;
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
	
	public CourseWorkSub modify(int idx, int courseWorkId) {
		CourseWorkSub params = new CourseWorkSub();
		params.setIdx(idx);
		params.setCourseWorkId(courseWorkId);
		params.setModifyDate(new Date());
		courseWorkSubMapper.modify(params);
		return params;
	}
	
	public void delete(CourseWorkSub courseWorkSub) {
		courseWorkSubMapper.delete(courseWorkSub);
	}
	
}
