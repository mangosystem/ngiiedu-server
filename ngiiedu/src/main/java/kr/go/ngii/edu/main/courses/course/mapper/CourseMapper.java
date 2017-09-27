package kr.go.ngii.edu.main.courses.course.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.course.model.Course;

@Mapper
public interface CourseMapper {

	public void create(Course course);

}
