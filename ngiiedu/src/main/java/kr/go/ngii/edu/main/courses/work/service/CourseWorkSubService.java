package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkSubMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;

@Service
public class CourseWorkSubService extends BaseService {

	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;
	
	@Autowired
	private CourseWorkService courseWorkService;

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
				if("layers".equals(subItem.getOutput_type())) {
					uriParams.put("layer_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				} else if("maps".equals(subItem.getOutput_type())) {
					uriParams.put("maps_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.MAPS_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				} else if("dataset".equals(subItem.getOutput_type())) {
					uriParams.put("dataset_id", subItem.getPinogioOutputId());
					Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.DATASET_GET, uriParams);
					subItem.setPngoData(r.get("data"));
				}
			}
		}
		return qList;
	}
}
