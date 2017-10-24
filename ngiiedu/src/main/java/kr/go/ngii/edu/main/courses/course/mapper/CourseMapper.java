package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.Course;
import kr.go.ngii.edu.main.courses.course.model.CourseInfo;

@Mapper
public interface CourseMapper {

	public void create(Course course);
	
	public Course get(Course course);

	public List<Course> list();

	public List<Course> list(@Param("offset") int offset, @Param("limit") int limit);
	
	public List<CourseInfo> list(@Param("offset") int offset, @Param("limit") int limit,
			@Param("keyword") String keyword);

	public List<CourseInfo> courseDetailList(@Param("offset") int offset, @Param("limit") int limit,
			@Param("keyword") String keyword);

	public List<CourseInfo> courseDetailListByUserId(@Param("userId") int userId);
	
	public List<CourseInfo> courseDetailListByUserId(@Param("userId") int userId, @Param("offset") int offset,
			@Param("limit") int limit, @Param("keyword") String keyword);
	
	public void modify(Course course);

}
