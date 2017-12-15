package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubInfo;

@Mapper
public interface CourseWorkSubMapper {

	public List<CourseWorkSubInfo> list(CourseWork CourseWork);
	
	public List<CourseWorkSubInfo> listByCourseWorkIdAndOutputType(@Param("courseWorkId") int courseWorkId,
			@Param("moduleWorkId") int moduleWorkId, @Param("outputType") String outputType);
	
	public CourseWorkSub get(CourseWorkSub courseWorkSub);
	
	public void create(CourseWorkSub courseWorkSub);
	
	public void modify(CourseWorkSub courseWorkSub);
	
	public void delete(CourseWorkSub courseWorkSub);
}
