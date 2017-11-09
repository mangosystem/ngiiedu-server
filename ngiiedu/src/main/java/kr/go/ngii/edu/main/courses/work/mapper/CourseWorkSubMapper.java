package kr.go.ngii.edu.main.courses.work.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.work.model.CourseWork;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSub;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWorkSubOutputWithModuleWorkSub;
import kr.go.ngii.edu.main.courses.work.model.WorkOutput;

@Mapper
public interface CourseWorkSubMapper {

	public List<CourseWorkSubOutputWithModuleWorkSub> list(CourseWork courseWork);

	public List<CourseWorkSubOutputWithModuleWorkSub> list(@Param("moduleWorkId") int moduleWorkId, 
			@Param("moduleWorkSubId") int moduleWorkSubId, @Param("moduleWorkId") int userId);

	public List<CourseWorkSubOutputInfo> courseWorkSubOutputInfoList(CourseWork courseWork);
	
	public void create(CourseWorkSub courseWorkSub);

	public CourseWorkSub get(CourseWorkSub courseWorkSub);
}
