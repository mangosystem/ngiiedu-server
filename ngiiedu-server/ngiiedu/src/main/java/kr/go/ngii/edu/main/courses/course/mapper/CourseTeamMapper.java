package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.CourseTeam;

@Mapper
public interface CourseTeamMapper {

	public List<CourseTeam> list(@Param("courseId") int courseId);

	public CourseTeam get(@Param("courseId") int courseId, @Param("teamId") int teamId);

	public void create(CourseTeam params);

	public void modifyTeamName(@Param("courseId") int courseId, @Param("teamId") int teamId, 
			@Param("teamName") String teamName, @Param("modifyDate") Date modifyDate);

	public void delete(@Param("courseId") int courseId, @Param("teamId") int teamId);

	public Integer countByCourseId(@Param("courseId") int courseId);

	public Boolean exists(@Param("courseId") int courseId, @Param("teamId") int teamId);

}
