package kr.go.ngii.edu.main.courses.work.mapper;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.CourseWork;

@Mapper
public interface CourseWorkMapper {

	public void create(CourseWork courseWork);

}
