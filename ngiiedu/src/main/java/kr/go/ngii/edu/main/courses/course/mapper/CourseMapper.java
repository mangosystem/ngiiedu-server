package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.Course;

@Mapper
public interface CourseMapper {

	public void create(Course course);
	
	public Course get(Course course);

	public List<Course> list();

	public List<Course> list(@Param("offset") int offset, @Param("limit") int limit);

}
