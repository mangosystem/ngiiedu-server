package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.course.model.Course;

@Mapper
public interface CourseMapper {

	public Course get(Course course);

	public List<Course> list(Course course);

	public int create(Course course);

	public void modify(Course course);

	public void delete(int idx);

}
