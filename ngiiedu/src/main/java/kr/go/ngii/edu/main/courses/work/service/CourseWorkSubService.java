package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.config.LocalResourceBundle;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Service
public class CourseWorkSubService extends BaseService {

	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;
	
	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private WorkOutputService workOutputService;

	public List<CourseWorkSubOutputWithModuleWorkSub> list(CourseWork courseWork) {
		return courseWorkSubMapper.list(courseWork);
	}
	
	public List<CourseWorkSubOutputInfo> courseWorkSubOutputInfoList(CourseWork courseWork) {
		return courseWorkSubMapper.courseWorkSubOutputInfoList(courseWork);
	}
	
	public List<CourseWorkSubOutputWithModuleWorkSub> list(int courseWorkId) {
		
		CourseWork param = new CourseWork();
		param.setIdx(courseWorkId);
		param = courseWorkService.get(param);

		List<CourseWorkSubOutputWithModuleWorkSub> qList = list(param);

		for (CourseWorkSubOutputWithModuleWorkSub qItem : qList) {

			List<CourseWorkSubOutputInfo> subList = qItem.getCourseWorkSubOutputInfoList();
			RestAPIClient rc = new RestAPIClient();

			for (CourseWorkSubOutputInfo subItem : subList) {

				Map<String, String> uriParams = new HashMap<String, String>();

				if ("layers".equals(subItem.getOutput_type())) {

					uriParams.put("layer_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				} else if ("maps".equals(subItem.getOutput_type())) {

					uriParams.put("maps_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				} else if("dataset".equals(subItem.getOutput_type())) {
					
					uriParams.put("dataset_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				}
			}
			
//			qItem.setOutputType(outputType);
		}
		return qList;
	}
	
	public WorkOutput create(int courseWorkId, int moduleWorkId, int userId, 
			int teamId, String outputTypem, String layerTitle) {
		
		// dataset 조회
		List<WorkOutput> workOutputList = workOutputService.getItemByCourseWorkId(courseWorkId);
		
		// dataset의 pinogio Id 가져옴 -2개 이상 있을경우 로직 추가 해야함
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
//		courseWorkSubMapper
		
		// work_output
		
		
		return null;
	}
}
