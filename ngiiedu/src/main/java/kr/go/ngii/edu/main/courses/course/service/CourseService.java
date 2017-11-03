package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.common.message.ErrorMessage;
import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMapper;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseInfo;
import kr.go.ngii.edu.main.courses.course.model.CourseTeam;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.service.CourseWorkService;

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
	private CourseTeamMemberService courseTeamMemberService;
	
	public Course create(int moduleId, List<Integer> moduleWorkIds, String courseName, String courseMetadata) throws Exception {

		Course param = new Course();
		try {
			param.setCourseName(courseName);
			param.setCourseMetadata(courseMetadata);
			param.setModuleId(moduleId);
			//param.setCourseCreateId( getHttpSession().getAttribute("USERID").toString() );
			param.setCourseCreateId(1);
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
	
	public void delete(int courseId, int userId, String password) {

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
	}


}
