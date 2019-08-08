package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.CourseWorkData;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkDataInfo;

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

