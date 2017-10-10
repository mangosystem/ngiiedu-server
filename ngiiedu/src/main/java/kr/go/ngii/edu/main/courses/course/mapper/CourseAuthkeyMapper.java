package kr.go.ngii.edu.main.courses.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.course.model.CourseAuthkey;

@Mapper
public interface CourseAuthkeyMapper {

	public String getAuthkey(int courseId);
	
	public Integer getCourseId(String authkey);

	public Boolean exists(String authkey);

	public void updateDeactive(int courseId);
	
	public void create(CourseAuthkey courseAuthkey);

}
