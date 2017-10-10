package kr.go.ngii.edu.main.courses.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.go.ngii.edu.main.courses.course.model.CourseMember;

@Mapper
public interface CourseMemberMapper {

	public List<CourseMember> listByCourseId(@Param("courseId") int courseId);

	public List<CourseMember> listByCourseIdAndStatus(@Param("courseId") int courseId, @Param("joinStatus") String joinStatus);

	public CourseMember get(@Param("courseId") int courseId, @Param("userId") int userId);

	public void create(CourseMember params);

	public void modify(CourseMember params);

	public void delete(@Param("courseId") int courseId, @Param("userId") int userId);

}
