package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Mapper
public interface WorkOutputMapper {
	
	public CourseWorkSubOutputInfo getInfo(CourseWorkSubOutputInfo workoutput);

	public WorkOutput get(WorkOutput workOutput);
	
	public List<WorkOutput> getList(WorkOutput workOutput);

	public List<CourseWorkSubOutputInfo> list(CourseWorkSubOutputInfo workoutput);

	public boolean create(WorkOutput workoutput);

	public void modify(WorkOutput workoutput);

	public void delete(int idx);
	
	public List<WorkOutput> getItemByCourseWorkId(@Param("courseWorkId") int courseWorkId);
	
	public Boolean exists(@Param("idx") int idx);
}