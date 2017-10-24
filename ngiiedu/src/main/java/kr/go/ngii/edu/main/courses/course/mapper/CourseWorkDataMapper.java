package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.course.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.course.model.CourseWorkDataInfo;

@Mapper
public interface CourseWorkDataMapper {
	
	public List<CourseWorkData> list(CourseWorkData courseWorkData);

	public CourseWorkData get(CourseWorkData courseWorkData);
	
	public List<CourseWorkDataInfo> listCourseWorkDataInfo(CourseWorkData courseWorkData);
	
	public CourseWorkDataInfo getCourseWorkDataInfo(CourseWorkData courseWorkData);

	public int create(CourseWorkData courseWorkData);
	
	public void modify(CourseWorkData courseWorkData);
	
	public void delete(int idx);

}

