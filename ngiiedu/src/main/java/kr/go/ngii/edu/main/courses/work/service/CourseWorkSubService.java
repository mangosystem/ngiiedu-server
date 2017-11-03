package kr.go.ngii.edu.main.courses.work.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
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
		return list(param);
	}
}
