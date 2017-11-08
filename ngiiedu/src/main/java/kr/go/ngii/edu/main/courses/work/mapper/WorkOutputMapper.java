package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Mapper
public interface WorkOutputMapper {
	
	public CourseWorkSubOutputInfo get(CourseWorkSubOutputInfo workoutput);

	public List<CourseWorkSubOutputInfo> list(CourseWorkSubOutputInfo workoutput);

	public int create(WorkOutput workoutput);

	public void modify(WorkOutput workoutput);

	public void delete(int idx);
	
	public List<WorkOutput> getItemByCourseWorkId(@Param("courseWorkId") int courseWorkId);
	
}