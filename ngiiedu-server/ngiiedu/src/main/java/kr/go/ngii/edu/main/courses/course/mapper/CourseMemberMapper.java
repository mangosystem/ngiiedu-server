package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.CourseMember;
import kr.go.ngii.edu.main.courses.course.model.CourseMemberInfo;

@Mapper
public interface CourseMemberMapper {

	public List<CourseMember> listByCourseId(@Param("courseId") int courseId);

	public List<CourseMember> listByCourseIdAndJoinStatus(@Param("courseId") int courseId, @Param("joinStatus") String joinStatus);

	public List<CourseMemberInfo> courseMemberInfoListByCourseId(@Param("courseId") int courseId);
	
	public List<CourseMemberInfo> courseMemberInfoListByCourseIdAndJoinStatus(@Param("courseId") int courseId, @Param("joinStatus") String joinStatus);

	public CourseMember get(@Param("courseId") int courseId, @Param("userId") int userId);

	public void create(CourseMember params);

	public void modify(CourseMember params);

	public void deleteByCourseId(@Param("courseId") int courseId);

	public void deleteByCourseIdAndUserId(@Param("courseId") int courseId, @Param("userId") int userId);

	public Boolean exists(@Param("courseId") int courseId, @Param("userId") int userId);

}
