package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkInfo;

@Mapper
public interface CourseWorkMapper {

	public void create(CourseWork courseWork);
	
	public List<CourseWork> list(CourseWork courseWork);

	public CourseWork get(CourseWork courseWork);
	
	public List<CourseWorkInfo> listCourseWorkInfo(CourseWork courseWork);
	
	public CourseWorkInfo getCourseWorkInfo(CourseWork courseWork);

	public void modify(CourseWork courseWork);
	
	public void updateStatus(CourseWork courseWork);

	public void delete(CourseWork courseWork);
}
