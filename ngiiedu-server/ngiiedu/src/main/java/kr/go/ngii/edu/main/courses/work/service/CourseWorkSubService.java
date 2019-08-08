package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
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
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkSubService;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Service
public class CourseWorkSubService extends BaseService {

	@Autowired
	private CourseWorkSubMapper courseWorkSubMapper;
	
	@Autowired
	private CourseWorkService courseWorkService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
	@Autowired
	private ModuleWorkSubService moduleWorkSubService;
	
	@Autowired
	private UserService userService;
	
	public CourseWorkSub get(CourseWorkSub courseWorkSub) {
		return courseWorkSubMapper.get(courseWorkSub);
	}
	
	public List<CourseWorkSubInfo> list(int courseWorkId, Integer userId) {
		
		CourseWork param = new CourseWork();
		param.setIdx(courseWorkId);
		param = courseWorkService.get(param);
		
		return this.list(param, userId);
	}


	public List<CourseWorkSubInfo> list(CourseWork courseWork, Integer userId) {
		
		List<CourseWorkSubInfo> list = courseWorkSubMapper.list(courseWork);
		
		for (CourseWorkSubInfo cwsi : list) {
			//List<WorkOutput> workOutputList = cwsi.getWorkOutputList();
			
			// workoutput 조회....
			//param
			List<WorkOutput> workOutputList = cwsi.getWorkOutputList();
			
			// shared, done 체크...
			// 다시 조회...
			// 수업목록....11
			for (WorkOutput wo : workOutputList) {
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
		return list;
		
	}
	
	/**
	 * 
	 * 
	 * @param courseId
	 * @param outputType
	 * @return
	 */
	public List<CourseWorkSubInfo> listByCourseWorkIdAndOutputType(int courseId, String outputType) {
		
		CourseWork param = new CourseWork();
		param.setCourseId(courseId);
		List<CourseWork> courseWorkList = courseWorkService.list(param);
		
		List<CourseWorkSubInfo> list = new ArrayList<>();
		for (CourseWork cwParam : courseWorkList) {
			List<CourseWorkSubInfo> cwsList;
			if ("all".equals(outputType)) {
				cwsList = courseWorkSubMapper.list(cwParam);
			} else {
				cwsList = courseWorkSubMapper.listByCourseWorkIdAndOutputType(cwParam.getIdx(),
						cwParam.getModuleWorkId(), outputType);
			}
			
			for (CourseWorkSubInfo cwsi : cwsList) {
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
			if (cwsList != null && !cwsList.isEmpty()) {
				
				list.addAll(cwsList);
			}
		}
		return list;
	}
	
	public CourseWorkSub create(int courseWorkId, int moduleWorkSubId) {
		CourseWorkSub courseWorkSub = new CourseWorkSub();
		courseWorkSub.setCourseWorkId(courseWorkId);
		courseWorkSub.setModuleWorkSubId(moduleWorkSubId);
		courseWorkSubMapper.create(courseWorkSub);
		return courseWorkSub;
	}
	
	public List<CourseWorkSub> createList(CourseWork courseWork) {
		List<CourseWorkSub> CourseWorkSubList = new ArrayList<CourseWorkSub>();
		int courseWorkId = courseWork.getIdx();
		List<ModuleWorkSub> moduleWorkSubList = moduleWorkSubService.list(courseWork.getModuleWorkId());
		
		for(ModuleWorkSub item:moduleWorkSubList) {
			CourseWorkSub courseWorkSub = create(courseWorkId, item.getIdx());
			CourseWorkSubList.add(courseWorkSub);
		}
		return CourseWorkSubList;
	}
	
	
//	public WorkOutput create(int courseWorkId, int moduleWorkSubId, int userId, 
//			int teamId, String outputTypem, String layerTitle) {
//		
//		// dataset 조회
//		List<WorkOutput> workOutputList = workOutputService.getListByCourseWorkId(courseWorkId);
//		
//		// dataset의 pinogio Id
//		String datasetPinogioId = workOutputList.get(0).getPinogioOutputId();
//		
//		// pinogio create
//		RestAPIClient rc = new RestAPIClient();
//		Map<String, String> uriParams = new HashMap<String, String>();
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("project_id", LocalResourceBundle.PINOGIO_API_PROJECT_ID);
//		params.put("title", layerTitle);
//		params.put("source", "");
//		Map<String, Object> r = rc.getResponseBody(EnumRestAPIType.LAYER_CREATE, uriParams, params);
//		// course_work_sub
////		courseWorkSubMapper.create()
//		// work_output
//		return null;
//	}
	
	
	private Object requestPngoData(String pngoId, String outputType) {
		RestAPIClient rc = new RestAPIClient();
		String apiKey = "";
		try {
			User user = (User) getHttpSession().getAttribute("USER_INFO");
			apiKey = userService.getApiKey(user.getIdx());
		} catch (NullPointerException e) {
		}
		try {
			rc.setApiKey(apiKey);
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
		} catch (Exception e) {
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
