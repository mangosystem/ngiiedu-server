package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMemberInfo;

@Mapper
public interface CourseTeamMemberMapper {

	public List<CourseTeamMember> list(@Param("courseId") int courseId, @Param("teamId") int teamId);

	public List<CourseTeamMemberInfo> courseTeamMemberInfoList(@Param("courseId") int courseId);

	public CourseTeamMember get(CourseTeamMember courseTeamMember);
	
	public CourseTeamMember getByCourseIdAndMemberId(@Param("courseId") int courseId, @Param("userId") int userId);

	public void create(CourseTeamMember params);

	public void deleteByTeamId(@Param("courseId") int courseId, @Param("teamId") int teamId);

	public void deleteByTeamIdAndMemberId(@Param("courseId") int courseId, @Param("teamId") int teamId, @Param("memberId") int memberId);

	public Boolean exists(@Param("courseId") int courseId,@Param("teamId") int teamId, @Param("memberId") int memberId);

}
