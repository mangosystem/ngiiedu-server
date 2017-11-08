package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMemberInfo;
import kr.go.ngii.edu.main.courses.work.model.CourseWork;

@Service
public class CourseTeamMemberService extends BaseService {

	@Autowired
	private CourseTeamMemberMapper courseTeamMemberMapper;


	public List<CourseTeamMember> list(int courseId, int teamId) {
		return courseTeamMemberMapper.list(courseId, teamId);
	}
	
	public List<CourseTeamMemberInfo> courseTeamMemberInfoList(int courseId) {
		return courseTeamMemberMapper.courseTeamMemberInfoList(courseId);
	}

	public CourseTeamMember get(CourseTeamMember courseTeamMember) {
		return courseTeamMemberMapper.get(courseTeamMember);
	}
	
	public CourseTeamMember getByCourseIdAndMemberId(int courseId, int userId) {
		return courseTeamMemberMapper.getByCourseIdAndMemberId(courseId, userId);
	}

	public CourseTeamMember create(int courseId, int teamId, int memberId) {
		
		if (courseTeamMemberMapper.exists(courseId, teamId, memberId)) {
			return null;

		} else {
			CourseTeamMember params = new CourseTeamMember();
			params.setMemberId(memberId);
			params.setTeamId(teamId);
			params.setCreateDate(new Date());

			courseTeamMemberMapper.create(params);

			return params;
		}
	}

	public boolean delete(int courseId, int teamId, int memberId) {

		if (courseTeamMemberMapper.exists(courseId, teamId, memberId)) {
			courseTeamMemberMapper.deleteByTeamIdAndMemberId(courseId, teamId, memberId);
			return true;
		} else {
			return false;
		}
	}

}
