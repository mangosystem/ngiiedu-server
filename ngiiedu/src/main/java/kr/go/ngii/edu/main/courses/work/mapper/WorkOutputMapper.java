package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;

@Mapper
public interface WorkOutputMapper {
	
	public CourseWorkSubOutputInfo get(CourseWorkSubOutputInfo workoutput);

	public List<CourseWorkSubOutputInfo> list(CourseWorkSubOutputInfo workoutput);

	public int create(CourseWorkSubOutputInfo workoutput);

	public void modify(CourseWorkSubOutputInfo workoutput);

	public void delete(int idx);
	
}
