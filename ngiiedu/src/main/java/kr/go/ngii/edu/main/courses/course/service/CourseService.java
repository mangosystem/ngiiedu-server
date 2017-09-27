package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseMapper;
import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.work.model.Work;
import kr.go.ngii.edu.main.courses.work.service.WorkService;

@Service
public class CourseService extends BaseService {

	@Autowired
	private CourseMapper courseMapper;

	@Autowired
	private WorkService workService;


	public Course create(int moduleId, List<Integer> moduleWorkIds, String courseName, String courseMetadata) throws Exception {

		Course param = new Course();
		param.setCourseName(courseName);
		param.setCourseMetadata(courseMetadata);
		param.setModuleId(moduleId);
		//param.setCourseCreateId( getHttpSession().getAttribute("USERID").toString() );
		param.setCreateDate(new Date());
		param.setModifyDate(new Date());

		courseMapper.create(param);

		// 수업과정 추가 로직 필요함
		List<Work> workResult = workService.create(param.getIdx(), moduleWorkIds);
		param.setWork(workResult);

		return param;
	}

}
