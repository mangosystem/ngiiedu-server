package kr.go.ngii.edu.main.courses.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseWorkDataMapper;

import kr.go.ngii.edu.main.courses.course.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.course.model.CourseWorkDataInfo;

@Service
public class CourseWorkDataService extends BaseService {
	
	@Autowired
	private CourseWorkDataMapper courseWorkDataMapper;

	public List<CourseWorkData> list(CourseWorkData courseWorkData) {
		return courseWorkDataMapper.list(courseWorkData);
	}
	
	public CourseWorkData get(CourseWorkData courseWorkData) {
		return courseWorkDataMapper.get(courseWorkData);
	}
	
	public List<CourseWorkDataInfo> list(int courseId) {
		CourseWorkData params = new CourseWorkData();
		params.setCourseId(courseId);
		return courseWorkDataMapper.listCourseWorkDataInfo(params);
	}
	
	public CourseWorkDataInfo get(int courseId) {
		CourseWorkData params = new CourseWorkData();
		params.setCourseId(courseId);
		return courseWorkDataMapper.getCourseWorkDataInfo(params);
	}
	
	public int create(CourseWorkData courseWorkData) {
		return courseWorkDataMapper.create(courseWorkData);
	}
	
	public CourseWorkData modify(int idx, boolean status) {
		CourseWorkData params = new CourseWorkData();
		params.setIdx(idx);
		params.setStatus(status);
		courseWorkDataMapper.modify(params);
		return params;
	}
	
	public void delete(int idx) {
		courseWorkDataMapper.delete(idx);
	}
	

}
