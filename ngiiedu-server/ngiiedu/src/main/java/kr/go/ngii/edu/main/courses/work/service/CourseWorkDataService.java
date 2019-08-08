package kr.go.ngii.edu.main.courses.work.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.work.mapper.CourseWorkDataMapper;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkDataInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkData;
import kr.go.ngii.edu.main.modules.course.model.ModuleWorkSub;
import kr.go.ngii.edu.main.modules.course.service.ModuleWorkDataService;

@Service
public class CourseWorkDataService extends BaseService {
	
	@Autowired
	private CourseWorkDataMapper courseWorkDataMapper;

	@Autowired
	private ModuleWorkDataService moduleWorkDataService;

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
	
	public int create(int courseId, int moduleWorkDataId) {
		CourseWorkData params = new CourseWorkData();
		params.setCourseId(courseId);
		params.setModuleWworkDataId(moduleWorkDataId);
		params.setStatus(true);
		return courseWorkDataMapper.create(params);
	}
	
	public void createList(int moduleId, int courseId) {
		
		try {
			List<ModuleWorkData> moduleWorkDataList = moduleWorkDataService.list(moduleId);
			for(ModuleWorkData item:moduleWorkDataList) {
				create(courseId, item.getIdx());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
