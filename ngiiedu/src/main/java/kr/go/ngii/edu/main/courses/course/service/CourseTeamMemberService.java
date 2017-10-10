package kr.go.ngii.edu.main.courses.course.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.go.ngii.edu.main.common.BaseService;
import kr.go.ngii.edu.main.courses.course.mapper.CourseTeamMemberMapper;
import kr.go.ngii.edu.main.courses.course.model.CourseTeamMember;

@Service
public class CourseTeamMemberService extends BaseService {

	@Autowired
	private CourseTeamMemberMapper courseTeamMemberMapper;


	public List<CourseTeamMember> list(int courseId, int teamId) {
		return courseTeamMemberMapper.list(courseId, teamId);
	}

	public CourseTeamMember create(int courseId, int teamId, int memberId) {

		if (courseTeamMemberMapper.exists(teamId, memberId)) {
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

		if (courseTeamMemberMapper.exists(teamId, memberId)) {
			courseTeamMemberMapper.deleteByTeamIdAndMemberId(courseId, teamId, memberId);
			return true;
		} else {
			return false;
		}
	}

}
