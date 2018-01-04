package kr.go.ngii.edu.main.courses.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.go.ngii.edu.common.StringUtil;
import kr.go.ngii.edu.common.enums.EnumRestAPIType;
import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.common.RestAPIClient;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMapper;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseInfo;
import kr.go.ngii.edu.main.courses.course.model.CourseTeam;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkDataService;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkSubService;
import kr.go.ngii.edu.main.courses.work.service.WorkOutputService;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkDataService;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkSubService;
import kr.go.ngii.edu.main.users.mapper.PngoAuthKeyMapper;
import kr.go.ngii.edu.main.users.model.PngoUser;
import kr.go.ngii.edu.main.users.model.User;
import kr.go.ngii.edu.main.users.service.UserService;

@Service
public class CourseService extends BaseService {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private CourseWorkService workService;

	@Autowired
	private CourseAuthkeyService courseAuthkeyService;

	@Autowired
	private CourseMemberService courseMemberService;
	
	@Autowired
	private CourseTeamService courseTeamService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseWorkSubService courseWorkSubService;
	
	@Autowired
	private WorkOutputService workOutputService;
	
	@Autowired
	private ModuleWorkSubService moduleWorkSubService;
	
	@Autowired
	private CourseWorkDataService courseWorkDataService;
	
	
	
	public Course create(String courseName, String courseMetadata, int moduleId, 
			String projectId, int userId) {
		Course param = new Course();
		param.setCourseName(courseName);
		param.setCourseMetadata(courseMetadata);
		param.setModuleId(moduleId);
		param.setProjectId(projectId);
		param.setCourseCreateId(userId);
		param.setCreateDate(new Date());
		param.setModifyDate(new Date());
		courseMapper.create(param);
		return param;
	}
	

	public Course create(int moduleId, List<Integer> moduleWorkIds, String courseName, String courseMetadata) throws Exception {

		Course param = new Course();
		try {
			param.setCourseName(courseName);
			param.setCourseMetadata(courseMetadata);
			param.setModuleId(moduleId);
			
			try {
				param.setCourseCreateId(Integer.parseInt(getHttpSession().getAttribute("USER_IDX").toString()));
			} catch (Exception e) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
			param.setCreateDate(new Date());
			param.setModifyDate(new Date());

			courseMapper.create(param);

			courseAuthkeyService.create(param.getIdx());

			// 수업과정 추가 로직 필요함
			List<CourseWork> workResult = workService.create(param.getIdx(), moduleWorkIds);
			param.setWork(workResult);

		} catch(Exception e) {
			throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
		}
		return param;
	}
	
	public Course create(User user, int moduleId, List<Integer> moduleWorkIds, String courseName, String courseMetadata, Map<String, Object> emptyTemplate) {
		
		PngoUser pngoUser = userService.getPngoUser(user.getUserid());
		String apiKey = userService.getApiKey(pngoUser.getIdx());
		
		RestAPIClient rc = new RestAPIClient();
		Map<String, String> pathParam = new HashMap<String, String>();
		Map<String, String> mParam = new HashMap<String, String>();
		mParam.put("title", courseName);
		mParam.put("description", courseMetadata);
		mParam.put("privacy", "TEAM");
		Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PROJECT_CREATE, pathParam, mParam, apiKey);
		Map<String, String> metaData = (Map<String, String>) r.get("meta");
		String statusMessage = metaData.get("status");
		
		System.out.println(statusMessage);
		if (!"CREATED".equals(statusMessage)) {
			throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
		}
		
		Map<String, Object> projectCreatedData = (Map<String, Object>) r.get("data");
		String projectId = (String) projectCreatedData.get("projectId");
		
		Course param = new Course();
		try {
			
			param.setCourseName(courseName);
			param.setCourseMetadata(courseMetadata);
			param.setModuleId(moduleId);
			param.setProjectId(projectId);
			
			try {
				param.setCourseCreateId(user.getIdx());
			} catch (Exception e) {
				throw new RuntimeException(ErrorMessage.FOBRIDDEN);
			}
			param.setCreateDate(new Date());
			param.setModifyDate(new Date());

			courseMapper.create(param);

			courseAuthkeyService.create(param.getIdx());

			// 수업과정 추가 로직 필요함
			List<CourseWork> workResult = workService.create(param.getIdx(), moduleWorkIds);
			param.setWork(workResult);
			
			for (CourseWork courseWorkItem:workResult) {
				int courseWorkId = courseWorkItem.getIdx();
//				List<CourseWorkSub> courseWorkSubList = new ArrayList<CourseWorkSub>();
				List<ModuleWorkSub> moduleWorkSubList = moduleWorkSubService.list(courseWorkItem.getModuleWorkId());
				
				for(ModuleWorkSub item:moduleWorkSubList) {
					CourseWorkSub courseWorkSub = courseWorkSubService.create(courseWorkId, item.getIdx());
//					courseWorkSubList.add(courseWorkSub);
					
					
					if (emptyTemplate != null) {
						
						/*
						 * - /api/v1/datasets/createUpldate
						 * - 파라미터
						 * 	project_id			-> 
						 * 	title				-> emptyTemplate.title
						 * 	description			-> null
						 * 	privacy				-> TEAM
						 * 	metadata				-> emptyTemplate.metadata
						 * 	geometry_type		-> emptyTemplate.geometry_type
						 * 	columns				-> emptyTemplate.columns
						 * 	is_photo				-> emptyTemplate.is_photo
						 */
						
						ObjectMapper mapper = new ObjectMapper();

						String title = "";
						if (!StringUtil.isNullOrEmpty(emptyTemplate.get("title"))) {
							title = emptyTemplate.get("title").toString();
						}

						String metadata = "";
						if (!StringUtil.isNullOrEmpty(emptyTemplate.get("metadata"))) {
							metadata = mapper.writeValueAsString(emptyTemplate.get("metadata"));
							//metadata = emptyTemplate.get("metadata").toString();
						}

						String geometryType = "";
						if (!StringUtil.isNullOrEmpty(emptyTemplate.get("geometry_type"))) {
							geometryType = emptyTemplate.get("geometry_type").toString();
						}

						String columns = "";
						if (!StringUtil.isNullOrEmpty(emptyTemplate.get("columns"))) {
							columns = mapper.writeValueAsString(emptyTemplate.get("columns"));
						}

						String isPhoto = "false";
						if (!StringUtil.isNullOrEmpty(emptyTemplate.get("is_photo"))) {
							isPhoto = emptyTemplate.get("is_photo").toString();
						}
						
						Map<String, String> ecPathParam = new HashMap<String, String>();
						Map<String, String> ecMapmParam = new HashMap<String, String>();
						ecMapmParam.put("project_id", projectId);
						ecMapmParam.put("title", title);
						ecMapmParam.put("description", null);
						ecMapmParam.put("privacy", "TEAM");
						ecMapmParam.put("metadata", metadata);
						ecMapmParam.put("geometry_type", geometryType);
						ecMapmParam.put("columns", columns);
						ecMapmParam.put("is_photo", isPhoto);
						
						Map<String, Object> emptyCreateResult = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.DATASET_EMPTY_CREATE, ecPathParam, ecMapmParam, apiKey);
						Map<String, Object> createdDatasetResult = (Map<String, Object>) emptyCreateResult.get("data");
						
						// 
						// 추가된 courseWork의 아이디 조회
						// 
						
						WorkOutput wo = workOutputService.create(param.getIdx(), courseWorkSub.getIdx(), "1", createdDatasetResult, user.getIdx(), "dataset", true, false);
//						workOutputService.create(courseWorkSubId, 1, createdDatgasetResult, param.getCourseCreateId(), "dataset", true, false);
						
						//{meta={code=200, status=OK}, data={id=47, projectId=p=qhPWP03W, datasetId=d=I8J6n9EZD7, ownerId=1, title=소음원 현장조사, description=, metadata={"wgs84Bounds":{"minY":34.44521717164801,"minX":124.14518712529437,"maxY":37.98947165211126,"maxX":131.29728673466937},"spatialType":"VECTOR","geometryType":"POINT"}, spatialType=VECTOR, privacy=TEAM, createdDate=1514962595590, updatedDate=null, ownerUsername=admin1, geometryType=POINT, geometryColumn=the_geom, srid=3857, bounds=null, rowsCount=0, totalSize=24576, commentCount=0, viewCount=0, likeCount=0, ratingScore=0, metadataMap={wgs84Bounds={minY=34.44521717164801, minX=124.14518712529437, maxY=37.98947165211126, maxX=131.29728673466937}, spatialType=VECTOR, geometryType=POINT}}}
						
					} // emptyTemplate != null if
				} //ModuleWorkSub loop
			} // CourseWork loop end
			
		} catch(Exception e) {
			throw new RuntimeException(ErrorMessage.COURSE_CREATE_FAILED);
		}
		return param;
	}
	
	public Map<String, Object> createEmptyDataset(Map<String,Object> emptyTemplate, String projectId, String apiKey) {
		
		RestAPIClient rc = new RestAPIClient();
		
		ObjectMapper mapper = new ObjectMapper();

		String title = "";
		if (!StringUtil.isNullOrEmpty(emptyTemplate.get("title"))) {
			title = emptyTemplate.get("title").toString();
		}

		String metadata = "";
		if (!StringUtil.isNullOrEmpty(emptyTemplate.get("metadata"))) {
			try {
				metadata = mapper.writeValueAsString(emptyTemplate.get("metadata"));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
			//metadata = emptyTemplate.get("metadata").toString();
		}

		String geometryType = "";
		if (!StringUtil.isNullOrEmpty(emptyTemplate.get("geometry_type"))) {
			geometryType = emptyTemplate.get("geometry_type").toString();
		}

		String columns = "";
		if (!StringUtil.isNullOrEmpty(emptyTemplate.get("columns"))) {
			try {
				columns = mapper.writeValueAsString(emptyTemplate.get("columns"));
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

		String isPhoto = "false";
		if (!StringUtil.isNullOrEmpty(emptyTemplate.get("is_photo"))) {
			isPhoto = emptyTemplate.get("is_photo").toString();
		}
		
		Map<String, String> ecPathParam = new HashMap<String, String>();
		Map<String, String> ecMapmParam = new HashMap<String, String>();
		ecMapmParam.put("project_id", projectId);
		ecMapmParam.put("title", title);
		ecMapmParam.put("description", null);
		ecMapmParam.put("privacy", "TEAM");
		ecMapmParam.put("metadata", metadata);
		ecMapmParam.put("geometry_type", geometryType);
		ecMapmParam.put("columns", columns);
		ecMapmParam.put("is_photo", isPhoto);
		
		Map<String, Object> emptyCreateResult = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.DATASET_EMPTY_CREATE, ecPathParam, ecMapmParam, apiKey);
		Map<String, Object> createdDatasetResult = (Map<String, Object>) emptyCreateResult.get("data");
		return createdDatasetResult;
	}

	public List<Course> list() {
		return courseMapper.list();
	}

	public List<Course> list(int offset, int limit) {
		return courseMapper.list(offset, limit);
	}

	public List<CourseInfo> list(int offset, int limit, String keyword ) {
		return courseMapper.list(offset, limit, keyword);
		//		return courseMapper.courseDetailList(offset, limit, keyword);
	}

	public Course get(int idx) {
		Course course = new Course();
		course.setIdx(idx);
		return courseMapper.get(course);
	}

	public List<CourseInfo> courseDetailList(int offset, int limit, String keyword) {
		return courseMapper.courseDetailList(offset, limit, keyword);
	}

	public List<CourseInfo> courseInfoListByUserId(int userId) {
		return courseMapper.courseInfoListByUserId(userId);
	}

	public List<CourseInfo> courseInfoListByUserId(int userId, int offset, int limit, String keyword) {
		return courseMapper.courseInfoListByUserId(userId, offset, limit, keyword);
	}
	
	public List<CourseInfo> courseInfoListJoin(int userId, int offset, int limit, String keyword) {
		return courseMapper.courseInfoListJoin(userId, offset, limit, keyword);
	}
	
	public List<CourseInfo> courseInfoListOwn(int userId, int offset, int limit, String keyword) {
		return courseMapper.courseInfoListOwn(userId, offset, limit, keyword);
	}
	
	public Course modify(int idx, String courseName, String courseMetadata) {
		
		Course param = new Course();
		param.setIdx(idx);
		param.setCourseName(courseName);
		param.setCourseMetadata(courseMetadata);
		param.setModifyDate(new Date());

		courseMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(idx);
		}
		
		return param;
	}
	
	public Course updateStatus(int idx, boolean status) {

		Course param = new Course();
		param.setIdx(idx);
		param.setStatus(status);
		param.setModifyDate(new Date());

		courseMapper.modify(param);

		if (param.getIdx()!=null) {
			param = get(idx);
		}
		
		return param;
	}
	
	public boolean delete(int courseId) {
		
		// password 체크
		
		// 수업결과물 삭제? 안함 과정정보 등이 지워지므로 결과물 관리 필요함.
		
		// 인증키 삭제
		courseAuthkeyService.delete(courseId);
		LOGGER.info(" >> courseId : " + courseId + " >> 인증키삭제");
		
		// 과정삭제
		CourseWork courseWorkParam = new CourseWork();
		courseWorkParam.setCourseId(courseId);
		workService.delete(courseWorkParam);
		
		// 수업 참여자,  팀원, 팀 삭제 
		courseMemberService.delete(courseId);
		LOGGER.info(" >> courses_member 의  courseId : " + courseId +" >> 전체  삭제");
		
		// 팀, 팀원삭제
		List<CourseTeam> teamList = courseTeamService.list(courseId);
		for (CourseTeam team : teamList) {
			courseTeamService.delete(courseId, team.getIdx());
		}
		
		// 수업삭제
		Course params = new Course();
		params.setIdx(courseId);
		boolean result = courseMapper.delete(params);
		return result;
	}
	
	public boolean delete(int courseId, int idx, String password) {
		User user = userService.get(idx);
		return this.delete(courseId, user, password);
	}
	
	public boolean delete(int courseId, String userId, String password) {
		User user = userService.get(userId);
		return this.delete(courseId, user, password);
	}
	
	public boolean delete(int courseId, User user, String password) {

		// password 체크
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, user.getPassword())) {
			throw new RuntimeException(ErrorMessage.PASSWORD_AUTHENTICATION_FAILED);
		}
		
		Course course = new Course();
		course.setIdx(courseId);
		course = courseMapper.get(course);
		
		if (user.getIdx() != course.getCourseCreateId() && 
				!"3".equals(user.getUserDivision().trim())) {
			// 자신이 만든  수어빙 아니면서 관리자 권한이 없을경우
			throw new RuntimeException(ErrorMessage.FOBRIDDEN);
		}
		
		// 수업결과물 삭제?
		// 인증키 삭제
		courseAuthkeyService.delete(courseId);
		
		// 수업 참여자,  팀원, 팀 삭제 
		courseMemberService.delete(courseId);
		
		// 팀, 팀원삭제
		List<CourseTeam> teamList = courseTeamService.list(courseId);
		for (CourseTeam team : teamList) {
			courseTeamService.delete(courseId, team.getIdx());
		}
		
		//pinogio project 삭제..
		try {
			
			RestAPIClient rc = new RestAPIClient();
			String projectId = course.getProjectId();
			
			PngoUser pngoUser = userService.getPngoUser(user.getUserid());
			int pngoUserId = pngoUser.getIdx();
			
			String apiKey = userService.getApiKey(pngoUserId);
			
			Map<String, String> pathParam = new HashMap<String, String>();
			pathParam.put("project_id", projectId);
			Map<String, String> param = new HashMap<String, String>();
			
			Map<String, Object> r = rc.getResponseBodyWithLinkedMap(EnumRestAPIType.PROJECT_REMOVE, pathParam, param, apiKey);
			Map<String, String> metaData = (Map<String, String>) r.get("meta");
			System.out.println(r);
			System.out.println(metaData);
			System.out.println(metaData.get("message"));
//			if (!"Deleted".equalsIgnoreCase(metaData.get("status"))) {
//				throw new RuntimeException(ErrorMessage.SERVER_ERROR);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// work data 삭제
		courseWorkDataService.delete(courseId);
		
		// Work sub 삭제
		CourseWork courseWorkParam = new CourseWork();
		courseWorkParam.setCourseId(courseId);
		List<CourseWork> CourseWorkList = workService.list(courseWorkParam);
		
		for (CourseWork courseWorkItem:CourseWorkList) {
			CourseWorkSub courseWorkSub = new CourseWorkSub();
			courseWorkSub.setCourseWorkId(courseWorkItem.getIdx());
			courseWorkSubService.delete(courseWorkSub);
		}
		
		// work 삭제 
		workService.delete(courseWorkParam);
		
		// 수업삭제
		Course params = new Course();
		params.setIdx(courseId);
		return courseMapper.delete(params);
	}


}
